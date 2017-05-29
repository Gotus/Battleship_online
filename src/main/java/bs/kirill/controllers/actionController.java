package bs.kirill.controllers;

import bs.kirill.entity.EAchievement;
import bs.kirill.entity.EUserData;
import bs.kirill.service.EBattleService;
import bs.kirill.service.EUser_DataService;
import bs.kirill.entity.EBattle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/lobby")
    public DataContainer[] getAllLobby() {

        ArrayList<EBattle> battleArrayList = new ArrayList<EBattle>(battleService.getByDateOfEnding(null));
        ArrayList<DataContainer> allData = new ArrayList<DataContainer>(battleArrayList.size());
        for(int i = 0; i < battleArrayList.size(); i++) {

            allData.get(i).setBattleID(battleArrayList.get(i).getBattle_ID());
            allData.get(i).setHostLogin(user_dataService.getByUser_ID(battleArrayList.get(i).getHost_ID()).getLogin());
            if (battleArrayList.get(i).getDate_of_joining() == null) {

                allData.get(i).setOpponentConnected(false);
            } else {

                allData.get(i).setOpponentConnected(true);
            }
        }

        DataContainer[] dataArray = new DataContainer[allData.size()];
        for (int i = 0; i < allData.size(); i++) {

            dataArray[i] = allData.get(i);
        }
        return dataArray;
    }

    //not tested
    //Method creates a new lobby
    @RequestMapping(value = "/lobby/battle/{hostLogin}")
    public EBattle createLobby(@PathVariable("hostLogin") String hostLogin) {

        EBattle newBattle = new EBattle();
        newBattle.setHost_ID(user_dataService.getByLogin(hostLogin).getUser_ID());
        newBattle.setDate_of_creation(new Date());
        return newBattle;
    }

    //not tested
    //Method adds opponent to lobby
    @RequestMapping(value = "/lobby/battle/{battleID}/{opponentLogin}")
    public EBattle addOppponent(@PathVariable("battleID") Long battleID, @RequestParam(value = "opponentLogin") String opponentLogin) {

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