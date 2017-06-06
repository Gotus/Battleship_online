package bs.kirill.controllers;

import bs.kirill.entity.EAchievement;
import bs.kirill.entity.EUserData;
import bs.kirill.service.EBattleService;
import bs.kirill.service.EUser_DataService;
import bs.kirill.entity.EBattle;
import bs.kirill.util.ClassExecutingTask;
import bs.web.BattleMap;
import bs.web.controller.gamelogic.mainlogic.Place;
import bs.web.model.entities.Battle;
import bs.web.model.entities.Battlefield;
import bs.web.model.entities.Coordinate;
import bs.web.model.entities.Ship;
import bs.web.view.ConsoleOutput;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

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

    public ClassExecutingTask executingTask = new ClassExecutingTask();

   // executingTask.start();

    public EBattleService getBattleService(){

        return this.battleService;
    }

    public void destroyBarrle(Date currentDate, Long battleID) {

        EBattle battle = new EBattle();
        battle = battleService.getByBattle_ID(battleID);
        battle.setDate_of_ending(currentDate);
    }



    /*
     * Not tested
     * Method puts ship to selected location
     * Gets number of ship in fleet, coordinates of its location and battlefield
     * returns battlefield with located ship
     */
    @RequestMapping(value = "/location", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody Object[] putShip(@RequestBody FullBattleData fullBattleData) {

        List<EBattle> battle = new ArrayList<>();
        Boolean playerIsHost = false;

        //Get battle in which player is and define his role: opponent or host

        if (battleService.getByHostIDAndDateOfEnding(user_dataService.
                getByLogin(fullBattleData.getLogin()).getUser_ID(),
                null) == null)
        {

            //player is opponent
            battle = battleService.getByOpponentIDAndDateOfEnding(user_dataService
                    .getByLogin(fullBattleData.getLogin()).getUser_ID(), null);
            playerIsHost = false;
        } else {

            //player is host
            battle = battleService.getByHostIDAndDateOfEnding(user_dataService
                    .getByLogin(fullBattleData.getLogin()).getUser_ID(), null);
            playerIsHost = true;
        }

        Battle currentBattle = new Battle();

        System.out.println(battleService.getAll().size() - 1);
        System.out.println(battle.get(0));
        System.out.println("id " + BattleMap.battleHashMap.get(battle.get(0).getBattle_ID().intValue()));

        currentBattle = BattleMap.battleHashMap.get(battle.get(0).getBattle_ID().intValue());
        if (playerIsHost) {

            Battlefield hostBattlefield = currentBattle.getBattlefields().get(0);
            Place.place(hostBattlefield.getBattlefield(),
                    hostBattlefield.getFleet().get(fullBattleData.getNumberInFleet()),
                    new Coordinate(fullBattleData.getXx(), fullBattleData.getYy()),
                    fullBattleData.getOrientation());

            return hostBattlefield.getFleet().toArray();
        } else {

            Battlefield opponentBattlefield = currentBattle.getBattlefields().get(1);
            Place.place(opponentBattlefield.getBattlefield(),
                    opponentBattlefield.getFleet().get(fullBattleData.getNumberInFleet()),
                    new Coordinate(fullBattleData.getXx(), fullBattleData.getYy()),
                    fullBattleData.getOrientation());
            return opponentBattlefield.getFleet().toArray();
        }
    }

    //works correct
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

        EBattle newBattle = new EBattle();
        newBattle.setHost_ID(user_dataService.getByLogin(hostLoginContainer.getLogin()).getUser_ID());
        Date currentDate = new Date();
        newBattle.setDate_of_creation(currentDate);
        newBattle.setDate_of_last_action(currentDate);
        CreatingLobbyResult result = new CreatingLobbyResult();
        EUserData userData = new EUserData();

        if ( user_dataService.getByLogin(hostLoginContainer.getLogin()).getCurrentBattle() == null){

            battleService.addBattle(newBattle);

            userData = user_dataService.getByLogin(hostLoginContainer.getLogin());
            userData.setCurrentBattle(newBattle.getBattle_ID());
            user_dataService.updateUser(userData);

            Long id = battleService.getByHostIDAndDateOfEnding(user_dataService.
                    getByLogin(hostLoginContainer.getLogin()).
                    getUser_ID(), null).
                    get(0).
                    getBattle_ID();
            Battle battle = new Battle();
            BattleMap.battleHashMap.put(id.intValue(), battle);

            System.out.println(id.intValue());
            System.out.println(BattleMap.battleHashMap.get(id.intValue()));
            System.out.println(BattleMap.battleHashMap.get(id.intValue()).getBattlefields());
            System.out.println(BattleMap.battleHashMap.get(id.intValue()).getBattlefields().get(0));
            System.out.println(BattleMap.battleHashMap.get(id.intValue()).getBattlefields().get(0).getBattlefield());
            ConsoleOutput.consoleprint(BattleMap.battleHashMap.get(id.intValue()).getBattlefields().get(0).getBattlefield());

            result.setIsSuccess(true);

            return result;

        } else {

            result.setIsSuccess(false);
            return result;
        }
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

class FullBattleData {

    public String login;
    public Integer numberinfleet;
    public Integer xx;
    public Integer yy;
    public Boolean orientation;

    public void setLogin(String login) {

        this.login = login;
    }

    public void setNumberInFleet(Integer numberinfleet) {

        this.numberinfleet = numberinfleet;
    }

    public void setXx(Integer xx) {

        this.xx = xx;
    }

    public void setYy(Integer yy) {

        this.yy = yy;
    }

    public void setOrientation(Boolean orient) {

        this.orientation = orient;
    }

    public String getLogin() {

        return login;
    }

    public Integer getNumberInFleet() {

        return numberinfleet;
    }

    public Integer getXx() {

        return xx;
    }

    public Integer getYy() {

        return yy;
    }

    public Boolean getOrientation() {

        return orientation;
    }
}

/*
 *
 * Class contains information about user's battlefield
 * and fleet
 */

/*
class UserBattleData {

    public Battlefield userBattlefield;
    public ArrayList<Ship> fleet;

    public void setUserBattlefield(Battlefield battlefield) {

        this.userBattlefield = battlefield;
    }

    public void setFleet(ArrayList<Ship> fleet) {

        this.fleet = fleet;
    }

    public Battlefield getUserBattlefield() {

        return userBattlefield;
    }

    public ArrayList<Ship> getFleet() {

        return fleet;
    }
}*/

