package com.kirill.controllers;

import com.kirill.entity.EAchievement;
import com.kirill.entity.EBattle;
import com.kirill.entity.EUserData;
import com.kirill.service.EBattleService;
import com.kirill.service.EUser_DataService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.validation.constraints.Null;
import java.util.Collections;
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
    public List<EBattle> getAllLobby() {

        return battleService.getByDateOfEnding(null);
    }

    //not tested
    //Method creates a new lobby
    @RequestMapping(value = "/lobby/battle/{battleID}/{hostID}")
    public EBattle createLobby(@PathVariable("hostID") Integer hostID) {

        EBattle newBattle = new EBattle();
        newBattle.setHost_ID(hostID);
        newBattle.setDate_of_creation(new Date());
        return newBattle;
    }

    //not tested
    //Method adds opponent to lobby
    @RequestMapping(value = "/lobby/battle/{battleID}/{opponentID}")
    public EBattle addOppponent(@PathVariable("battleID") Long battleID, @RequestParam(value = "opponentID") Integer opponentID) {

        EBattle selectedBattle = new EBattle();
        selectedBattle = battleService.getByBattle_ID(battleID);
        selectedBattle.setOpponent_ID(opponentID);
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
