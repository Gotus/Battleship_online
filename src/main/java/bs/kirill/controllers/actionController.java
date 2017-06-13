package bs.kirill.controllers;

import bs.kirill.entity.EAchievement;
import bs.kirill.entity.EBattle;
import bs.kirill.entity.EShip;
import bs.kirill.entity.EUserData;
import bs.kirill.service.EBattleService;
import bs.kirill.service.EShipService;
import bs.kirill.service.EUser_DataService;
import bs.kirill.util.ClassExecutingTask;
import bs.web.BattleMap;
import bs.web.controller.gamelogic.mainlogic.Fire;
import bs.web.controller.gamelogic.mainlogic.Place;
import bs.web.model.entities.Battle;
import bs.web.model.entities.Battlefield;
import bs.web.model.entities.Coordinate;
import bs.web.model.entities.Ship;
import bs.web.view.ConsoleOutput;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.http.MediaType;
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

    @Resource(name = "EShipService")
    private EShipService shipService;

    public ClassExecutingTask executingTask = new ClassExecutingTask();

    // executingTask.start();

    public EBattleService getBattleService() {

        return this.battleService;
    }

    public void destroyBarrle(Date currentDate, Long battleID) {

        EBattle battle = new EBattle();
        battle = battleService.getByBattle_ID(battleID);
        battle.setDate_of_ending(currentDate);
    }


    /*
     * Works correct
     * Method puts ship to selected location
     * Gets number of ship in fleet, coordinates of its location and battlefield
     * returns battlefield with located ship
     */
    @RequestMapping(value = "/location", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Object[] putShip(@RequestBody FullBattleData fullBattleData) {

        List<EBattle> battle = new ArrayList<>();
        Boolean playerIsHost = false;
        //Get battle in which player is and define his role: opponent or host
        //try {

        if (battleService.getByHostIDAndDateOfEnding(user_dataService.
                        getByLogin(fullBattleData.getLogin()).getUser_ID(),
                null).isEmpty()) {

            //player is opponent
            System.out.println("i am opponent");
            battle = battleService.getByOpponentIDAndDateOfEnding(user_dataService
                    .getByLogin(fullBattleData.getLogin()).getUser_ID(), null);
            playerIsHost = false;
        } else {

            //player is host
            battle = battleService.getByHostIDAndDateOfEnding(user_dataService
                    .getByLogin(fullBattleData.getLogin()).getUser_ID(), null);
            System.out.println("i am host");
            playerIsHost = true;
        }

        Battle currentBattle = new Battle();

        currentBattle = BattleMap.battleHashMap.get(battle.get(0).getBattle_ID().intValue());
        System.out.println(battle.get(0).getBattle_ID());

        if (playerIsHost) {

            System.out.println(currentBattle);
            System.out.println(currentBattle.getBattlefields());
            System.out.println(currentBattle.getBattlefields().get(0));
            Battlefield hostBattlefield = currentBattle.getBattlefields().get(0);
            Place.place(hostBattlefield.getBattlefield(),
                    hostBattlefield.getFleet().get(fullBattleData.getNumberInFleet()),
                    new Coordinate(fullBattleData.getXx(), fullBattleData.getYy()),
                    fullBattleData.getOrientation());

            battle.get(0).setDate_of_last_action(new Date());
            battleService.addBattle(battle.get(0));
            return hostBattlefield.getFleet().toArray();
        } else {

            Battlefield opponentBattlefield = currentBattle.getBattlefields().get(1);
            Place.place(opponentBattlefield.getBattlefield(),
                    opponentBattlefield.getFleet().get(fullBattleData.getNumberInFleet()),
                    new Coordinate(fullBattleData.getXx(), fullBattleData.getYy()),
                    fullBattleData.getOrientation());

            battle.get(0).setDate_of_last_action(new Date());
            battleService.addBattle(battle.get(0));
            return opponentBattlefield.getFleet().toArray();
        }
        /*} catch (NullPointerException exception) {

            EUserData slowUser = new EUserData();
            slowUser = user_dataService.getByLogin(fullBattleData.getLogin());
            slowUser.setCurrentBattle(null);
            user_dataService.updateUser(slowUser);

            Object [] error = null;
            return error;
        }*/
    }


    /*
    * Method marks user as ready, if all ships were located
    *
    */
    @RequestMapping(value = "/readytofight", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Result markAsReady(@RequestBody ReadyToFightContainer readyToFightContainer) {

        EBattle currentEBattle = new EBattle();
        EUserData userData = new EUserData();
        Battle currentBattle = new Battle();
        Result result = new Result();
        ArrayList<Ship> fleet = new ArrayList<Ship>(10);
        //ArrayList<EShip> eFleet = new ArrayList<EShip>(10);

        userData = user_dataService.getByLogin(readyToFightContainer.getLogin());
        Boolean isHost = false;
        result.setIsSuccess(false);
        if (battleService.getByHostIDAndDateOfEnding(userData.getUser_ID(), null).isEmpty()) {

            //user is opponent
            currentEBattle = battleService.getByOpponentIDAndDateOfEnding(userData.getUser_ID(), null).get(0);
            if (currentEBattle.getDate_of_ending() != null) {

                return result;
            }

            currentBattle = BattleMap.battleHashMap.get(currentEBattle.getBattle_ID().intValue());
            currentBattle.setOpponentIsReady(true);
            fleet = currentBattle.getBattlefields().get(1).getFleet();
            Integer fleetSize = fleet.size();

            //Puttting data of user's fleet in database
            for (int i = 0; i < fleetSize; i++) {

                EShip currentShip = new EShip();
                currentShip.setBattleID(currentEBattle.getBattle_ID());
                currentShip.setUserID(userData.getUser_ID());
                currentShip.setLength(fleet.get(i).getSize());
                currentShip.setProwXX(fleet.get(i).getPrown().getXx());
                currentShip.setProwYY(fleet.get(i).getPrown().getYy());
                currentShip.setSternXX(fleet.get(i).getStern().getXx());
                currentShip.setSternYY(fleet.get(i).getStern().getYy());

                shipService.addShip(currentShip);
            }


            if ((currentBattle.getHostIsReady() == true) && (currentBattle.getOpponentIsReady() == true)) {

                currentBattle.setGameBegun(true);
            } else {

                currentBattle.setGameBegun(false);
                currentBattle.setHostTurn(false);
            }

            result.setIsSuccess(true);
            return result;

        } else {

            //user is host

            currentEBattle = battleService.getByHostIDAndDateOfEnding(userData.getUser_ID(), null).get(0);
            if (currentEBattle.getDate_of_ending() != null) {

                return result;
            }

            currentBattle = BattleMap.battleHashMap.get(currentEBattle.getBattle_ID().intValue());
            currentBattle.setHostIsReady(true);
            fleet = currentBattle.getBattlefields().get(0).getFleet();
            Integer fleetSize = fleet.size();

            //Puttting data of user's fleet in database
            for (int i = 0; i < fleetSize; i++) {

                EShip currentShip = new EShip();
                currentShip.setBattleID(currentEBattle.getBattle_ID());
                currentShip.setUserID(userData.getUser_ID());
                currentShip.setLength(fleet.get(i).getSize());
                currentShip.setProwXX(fleet.get(i).getPrown().getXx());
                currentShip.setProwYY(fleet.get(i).getPrown().getYy());
                currentShip.setSternXX(fleet.get(i).getStern().getXx());
                currentShip.setSternYY(fleet.get(i).getStern().getYy());

                shipService.addShip(currentShip);
            }

            if ((currentBattle.getHostIsReady() == true) && (currentBattle.getOpponentIsReady() == true)) {

                currentBattle.setGameBegun(true);
            } else {

                currentBattle.setGameBegun(false);
                currentBattle.setHostTurn(true);
            }

            result.setIsSuccess(true);
            return result;
        }
    }

    /*
     * Method refresh order of user's turn
     *
     */
    @RequestMapping(value = "/isready", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    String refreshOrderTurn(@RequestBody LoginContainer loginContainer) {

        EUserData userData = new EUserData();
        EBattle battle = new EBattle();
        userData = user_dataService.getByLogin(loginContainer.getLogin());
        if (battleService.getByHostIDAndDateOfEnding(userData.getUser_ID(), null).isEmpty()) {


            if (battleService.getByOpponentIDAndDateOfEnding(userData.getUser_ID(), null).isEmpty()) {

                return "";
            }
            //player is opponent
            battle = battleService.getByOpponentIDAndDateOfEnding(userData.getUser_ID(), null).get(0);

            if (!BattleMap.battleHashMap.containsKey(battle.getBattle_ID().intValue())) {

                return "";
            }

            if (BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getBattlefields().get(1).getFleet().isEmpty()){

                //opponent has not fleet and looses
                return "gameover2";
            }

            if (BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getBattlefields().get(0).getFleet().isEmpty()){

                //host has not fleet and looses, opponent win
                return "gameover1";
            }

            if (BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getHostTurn()) {

                return "failure";

            } else {

                return "myturn";
            }
        } else {

            if (battleService.getByHostIDAndDateOfEnding(userData.getUser_ID(), null).isEmpty()) {

                return "";
            }
            //player is host
            battle = battleService.getByHostIDAndDateOfEnding(userData.getUser_ID(), null).get(0);

            if (!BattleMap.battleHashMap.containsKey(battle.getBattle_ID().intValue())) {

                return "";
            }

            if (BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getBattlefields().get(1).getFleet().isEmpty()){

                //opponent has not fleet and looses
                return "gameover1";
            }

            if (BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getBattlefields().get(0).getFleet().isEmpty()){

                //host has not fleet and looses, opponent win
                return "gameover2";
            }

            if (BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getHostTurn()) {

                return "myturn";
            } else {

                return "failure";
            }
        }
    }

    /*
     * Method refreshes battlefield of user after shot
     *
     */
    @RequestMapping(value = "/getmyfield", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    int[][] refreshField(@RequestBody LoginContainer loginContainer) {

        EBattle battle = new EBattle();
        EUserData userData = new EUserData();
        userData = user_dataService.getByLogin(loginContainer.getLogin());
        if (battleService.getByHostIDAndDateOfEnding(userData.getUser_ID(), null).isEmpty()) {

            //player is opponent
            battle = battleService.getByOpponentIDAndDateOfEnding(userData.getUser_ID(), null).get(0);

            return Transporator.transporateMatrix(BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getBattlefields().get(1).getBattlefield());

        } else {

            //player is host
            battle = battleService.getByHostIDAndDateOfEnding(userData.getUser_ID(), null).get(0);

            return Transporator.transporateMatrix(BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getBattlefields().get(0).getBattlefield());
        }
    }

    /*
     * Method allow user to shot to opponent
     */
    @RequestMapping(value = "/fire", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    int[][] fireToEnemy(@RequestBody FireData fireData) {

        EBattle battle = new EBattle();
        EUserData userData = new EUserData();
        userData = user_dataService.getByLogin(fireData.getLogin());
        int result = -1;
        if (battleService.getByHostIDAndDateOfEnding(userData.getUser_ID(), null).isEmpty()) {

            //player is opponent
            battle = battleService.getByOpponentIDAndDateOfEnding(userData.getUser_ID(), null).get(0);

            result = Fire.fire(BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getBattlefields().get(0),
                    new Coordinate(fireData.getXx(), fireData.getYy()));

            if (result == 1) {

                BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).setHostTurn(true);
            }


            BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).setGameEnded(BattleMap.battleHashMap.get(battle.
                    getBattle_ID().intValue()).getBattlefields().
                    get(0).getFleet().isEmpty());
            //return field of host
            return Transporator.transporateMatrix(BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getBattlefields().get(0).getBattlefield());

        } else {

            //player is host
            battle = battleService.getByHostIDAndDateOfEnding(userData.getUser_ID(), null).get(0);

            result = Fire.fire(BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getBattlefields().get(1),
                    new Coordinate(fireData.getXx(), fireData.getYy()));

            if (result == 1) {

                BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).setHostTurn(false);
            }

            BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).setGameEnded(BattleMap.battleHashMap.get(battle.
                    getBattle_ID().intValue()).getBattlefields().
                    get(1).getFleet().isEmpty());
            //return field of opponent
            return Transporator.transporateMatrix(BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getBattlefields().get(1).getBattlefield());

        }
    }


    //works correct
    //Method shows all available lobbies
    @RequestMapping(value = "/lobby", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    DataContainer[] getAllLobby() {

        ArrayList<EBattle> battleArrayList = new ArrayList<EBattle>(battleService.getByDateOfEnding(null));
        DataContainer[] allData = new DataContainer[battleArrayList.size()];

        for (int i = 0; i < allData.length; i++) {

            allData[i] = new DataContainer();
        }

        for (int i = 0; i < battleArrayList.size(); i++) {

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
    public
    @ResponseBody
    Result createLobby(@RequestBody LoginContainer hostLoginContainer) {

        EBattle newBattle = new EBattle();
        newBattle.setHost_ID(user_dataService.getByLogin(hostLoginContainer.getLogin()).getUser_ID());
        Date currentDate = new Date();
        newBattle.setDate_of_creation(currentDate);
        newBattle.setDate_of_last_action(currentDate);
        Result result = new Result();
        EUserData userData = new EUserData();

        if (user_dataService.getByLogin(hostLoginContainer.getLogin()).getCurrentBattle() == null) {

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
    @RequestMapping(value = "/join", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    Result addOppponent(@RequestBody OpponentData opponentData) {

        EBattle selectedBattle = new EBattle();
        EUserData opponent = new EUserData();
        Result result = new Result();
        Date currentMoment = new Date();
        result.setIsSuccess(false);
        selectedBattle = battleService.getByBattle_ID(opponentData.getBattleID());
        opponent = user_dataService.getByLogin(opponentData.getLogin());
        if (selectedBattle.getDate_of_ending() != null) {

            return result;
        }
        if (opponent.getCurrentBattle() != null) {

            return result;
        }
        if (selectedBattle.getOpponent_ID() != null) {

            return result;
        }

        selectedBattle.setOpponent_ID(opponent.getUser_ID());
        selectedBattle.setDate_of_joining(currentMoment);
        selectedBattle.setDate_of_last_action(currentMoment);

        opponent.setCurrentBattle(selectedBattle.getBattle_ID());

        battleService.addBattle(selectedBattle);
        user_dataService.updateUser(opponent);
        result.setIsSuccess(true);
        return result;
    }


    @RequestMapping(value = "/getmyopponent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    EnemyContainer getEnemy(@RequestBody LoginContainer myLogin) {

        EUserData me = new EUserData();
        EBattle battle = new EBattle();
        EnemyContainer enemy = new EnemyContainer();

        me = user_dataService.getByLogin(myLogin.getLogin());

        if (!battleService.getByHostIDAndDateOfEnding(me.getUser_ID(), null).isEmpty()) {

            //i am host

            battle = battleService.getByHostIDAndDateOfEnding(me.getUser_ID(), null).get(0);
            if (battle.getOpponent_ID() != null){

                enemy.setEnemyLogin(user_dataService.getByUser_ID(battle.getOpponent_ID()).getLogin());
                enemy.setSuccess(true);
                return enemy;
            } else {

                enemy.setSuccess(true);
                enemy.setEnemyLogin(null);
                return enemy;
            }

        }

        if (!battleService.getByOpponentIDAndDateOfEnding(me.getUser_ID(), null).isEmpty()) {

            //i am opponent, enemy always exists

            battle = battleService.getByOpponentIDAndDateOfEnding(me.getUser_ID(), null).get(0);

            enemy.setEnemyLogin(user_dataService.getByUser_ID(battle.getHost_ID()).getLogin());
            enemy.setSuccess(true);
            return enemy;

        }

        System.out.println("my opponent login");
        enemy.setSuccess(false);
        return enemy;
    }

    @RequestMapping(value = "/getready", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ReadyChecker checkPlayersReady(@RequestBody LoginContainer loginContainer) {

        ReadyChecker checker = new ReadyChecker();
        EUserData userData = new EUserData();
        EBattle battle = new EBattle();

        userData = user_dataService.getByLogin(loginContainer.getLogin());

        if (!battleService.getByHostIDAndDateOfEnding(userData.getUser_ID(), null).isEmpty()) {

            //player is host


            battle = battleService.getByHostIDAndDateOfEnding(userData.getUser_ID(), null).get(0);
            if (BattleMap.battleHashMap.containsKey(battle.getBattle_ID().intValue())){

                checker.setMeReady(BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getHostIsReady());
                checker.setEnemyReady(BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getOpponentIsReady());
                System.out.println("returned success 1");
                checker.setIsSuccess(true);
                System.out.println(checker.getIsSuccess());
                return checker;
            } else {

                System.out.println("ready false");
                checker.setIsSuccess(false);
                return checker;
            }

        }

        if (!battleService.getByOpponentIDAndDateOfEnding(userData.getUser_ID(), null).isEmpty()) {

            //player is opponent

            battle = battleService.getByOpponentIDAndDateOfEnding(userData.getUser_ID(), null).get(0);
            if (BattleMap.battleHashMap.containsKey(battle.getBattle_ID().intValue())){

                checker.setMeReady(BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getOpponentIsReady());
                checker.setEnemyReady(BattleMap.battleHashMap.get(battle.getBattle_ID().intValue()).getHostIsReady());
                System.out.println("returned success 2");
                checker.setIsSuccess(true);
                return checker;
            } else {

                System.out.println("ready 2 false");
                checker.setIsSuccess(false);
                return checker;
            }

        }

        System.out.println("ready 3 false");
        checker.setIsSuccess(false);
        return checker;

    }

    @RequestMapping(value = "/witness", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody WitnessContainer showFields(@RequestBody BattleFinder battleFinder) {

        WitnessContainer result = new WitnessContainer();
        Battle battle = new Battle();

        System.out.println("BattleID " + battleFinder.getBattleID());
        System.out.println("int BattleID " + battleFinder.getBattleID().intValue());

        battle = BattleMap.battleHashMap.get(battleFinder.getBattleID().intValue());

        System.out.println(battle);
            if(battle != null) {

                result.setHostBattleField(battle.getBattlefields().get(0).getBattlefield());
                result.setOpponentBattleField(battle.getBattlefields().get(1).getBattlefield());
                result.setIsSuccess(true);
                System.out.println("I see you");
                System.out.println(result);
                System.out.println(result.getIsSuccess());
                return result;

            } else {
                result.setIsSuccess(false);
                System.out.println("You invisible");
                return result;
            }
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

class ReadyToFightContainer {

    private String login;
    private Boolean readyToFight;

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public Boolean getReadyToFight() {

        return readyToFight;
    }

    public void setReadyToFight(Boolean readyToFight) {

        this.readyToFight = readyToFight;
    }
}

class OpponentData {

    private Long battleID;
    private String login;

    public void setBattleID(Long battleID) {
        this.battleID = battleID;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getBattleID() {
        return battleID;
    }

    public String getLogin() {
        return login;
    }

}

class Result {
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

class FireData {

    public String login;
    public Integer xx;
    public Integer yy;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getXx() {
        return xx;
    }

    public void setXx(Integer xx) {
        this.xx = xx;
    }

    public Integer getYy() {
        return yy;
    }

    public void setYy(Integer yy) {
        this.yy = yy;
    }
}

/*
 *  class transporates square matrix
 *  He wants you square matrix
 */
class Transporator {

    private static int [][] resultMatrix;

    public static int[][] transporateMatrix(int [][] startMatrix) {

        resultMatrix = new int[startMatrix.length][startMatrix.length];

        for (int i = 0; i < startMatrix.length; i++) {

            for (int j = 0; j < startMatrix[i].length; j++) {

                resultMatrix[i][j] = startMatrix[j][i];

            }
        }

        return resultMatrix;
    }
}

class EnemyContainer {

    public String enemyLogin;
    public Boolean isSuccess;

    public String getEnemyLogin() {
        return enemyLogin;
    }

    public void setEnemyLogin(String enemyLogin) {
        this.enemyLogin = enemyLogin;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }
}

class ReadyChecker {

    private Boolean meReady;
    private Boolean enemyReady;
    private Boolean isSuccess;

    public Boolean getMeReady() {
        return meReady;
    }

    public void setMeReady(Boolean meReady) {
        this.meReady = meReady;
    }

    public Boolean getEnemyReady() {
        return enemyReady;
    }

    public void setEnemyReady(Boolean enemyReady) {
        this.enemyReady = enemyReady;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean success) {
        isSuccess = success;
    }
}

class WitnessContainer {

    private int [][] hostBattleField, opponentBattleField;
    Boolean isSuccess;

    public int[][] getHostBattleField() {

        return hostBattleField;
    }

    public void setHostBattleField(int[][] hostBattleField) {

        this.hostBattleField = new int[hostBattleField.length][hostBattleField.length];

        for (int i = 0; i < hostBattleField.length; i++) {

            for (int j = 0; j < hostBattleField[i].length; j++) {

                this.hostBattleField[i][j] = hostBattleField[i][j];

            }
        }
    }

    public int[][] getOpponentBattleField() {

        return opponentBattleField;
    }

    public void setOpponentBattleField(int[][] opponentBattleField) {

        this.opponentBattleField = new int[opponentBattleField.length][opponentBattleField.length];

        for (int i = 0; i < opponentBattleField.length; i++) {

            for (int j = 0; j < opponentBattleField[i].length; j++) {

                this.getOpponentBattleField()[i][j] = opponentBattleField[i][j];

            }
        }
    }

    public Boolean getIsSuccess() {

        return isSuccess;
    }

    public void setIsSuccess(Boolean success) {

        isSuccess = success;
    }
}


class BattleFinder{

    private Long battleID;

    public Long getBattleID() {

        return battleID;
    }

    public void setBattleID(Long battleID) {

        this.battleID = battleID;
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

