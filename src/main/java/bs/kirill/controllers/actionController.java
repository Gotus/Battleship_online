package bs.kirill.controllers;

import bs.kirill.entity.EAchievement;
import bs.kirill.entity.EUserData;
import bs.kirill.service.EBattleService;
import bs.kirill.service.EUser_DataService;
import bs.kirill.entity.EBattle;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gotus on 12.05.2017.
 */

@RestController
@RequestMapping(value = "/game")
public class actionController {

    @Resource(name = "EUser_DataService")
    private EUser_DataService user_dataService;

    @Resource(name = "EBattleService")
    private EBattleService battleService;

    //not tested
    //Method shows all available lobbies
    @RequestMapping(value = "/lobby", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody DataContainer[] getAllLobby() {

        ArrayList<EBattle> battleArrayList = new ArrayList<EBattle>(battleService.getByDateOfEnding(null));
        DataContainer[] allData = new DataContainer[battleArrayList.size()];

        for (int i = 0; i < allData.length; i++){

            allData[i] = new DataContainer();
        }

        for(int i = 0; i < battleArrayList.size(); i++) {

            allData[i].setBattleID(battleArrayList.get(i).getBattle_ID());
            allData[i].setHostLogin(user_dataService.getByUser_ID(battleArrayList.get(i).getHost_ID()).getLogin());
            if (battleArrayList.get(i).getDate_of_joining() == null) {

                allData[i].setOpponentConnected(false);

            } else {

                allData[i].setOpponentConnected(true);
            }
        }


        return allData;
    }

    //works correct
    //Method creates a new lobby
    @RequestMapping(value = "/battle/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody CreatingLobbyResult createLobby(@RequestBody LoginContainer hostLoginContainer) {

        //System.out.println(hostLoginContainer.getLogin());
        //inputed login is correct
        EBattle newBattle = new EBattle();
        newBattle.setHost_ID(user_dataService.getByLogin(hostLoginContainer.getLogin()).getUser_ID());
        newBattle.setDate_of_creation(new Date());

        /*System.out.println(newBattle.getBattle_ID());
          System.out.println(newBattle.getDate_of_creation());
          System.out.println(newBattle.getHost_ID());
        */

        battleService.addBattle(newBattle);
        CreatingLobbyResult result = new CreatingLobbyResult();
        result.setIsSuccess(true);
        return result;
    }

    //not tested
    //Method adds opponent to lobby
    @RequestMapping(value = "/lobby/battle", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public EBattle addOppponent(@RequestParam("battleID") Long battleID, @RequestParam(value = "opponentLogin") String opponentLogin) {

        EBattle selectedBattle = new EBattle();
        selectedBattle = battleService.getByBattle_ID(battleID);
        selectedBattle.setOpponent_ID(user_dataService.getByLogin(opponentLogin).getUser_ID());
        selectedBattle.setDate_of_joining(new Date());
        battleService.addBattle(selectedBattle);
        return selectedBattle;
    }

    //not tested
    //Method allow user to locate his ship in battlefield(лшдд ьуб здуфыуб ш вщтэе цфте штеупкфеу ершы ырше штещ ызкштп :( )
    @RequestMapping(value = "/lobby/battle/{battleID}/{userID}")
    public EBattle locateShip(@PathVariable("battleID") Long battleID, @PathVariable("userID") Integer userID) {

        return null;
    }

    //not tested
    //Method shows achievements of user
    @RequestMapping(value = "/achievements/{id}")
    public List<EAchievement> getUserAchievement(@PathVariable("id") Integer userID) {

        EUserData userData;
        userData = user_dataService.getByUser_ID(userID);
        List<EAchievement> achievementArrayList = new ArrayList<EAchievement>(userData.getAchievementsOfUser());
        return achievementArrayList;
    }
}

class DataContainer {

    private Long battleID;
    private String hostLogin;
    private Boolean opponentConnected;

    public void setBattleID(Long battleID) {

        this.battleID = battleID;
    }

    public void setHostLogin(String hostLogin) {

        this.hostLogin = hostLogin;
    }

    public void setOpponentConnected(Boolean opponentConnected) {

        this.opponentConnected = opponentConnected;
    }

    public Long getBattleID() {

        return battleID;
    }

    public String getHostLogin() {

        return hostLogin;
    }

    public Boolean getOpponentConnected() {

        return opponentConnected;
    }
}

class CreatingLobbyResult {
    private Boolean isSuccess;

    public Boolean getIsSuccess() {

        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {

        this.isSuccess = isSuccess;
    }
}

class LoginContainer {

    public String login;

    public void setLogin(String login) {

        this.login = login;
    }

    public String getLogin() {

        return login;
    }
}