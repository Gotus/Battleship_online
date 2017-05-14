package com.kirill.controllers;

import com.kirill.entity.EAchievement;
import com.kirill.entity.EBattle;
import com.kirill.service.EBattleService;
import com.kirill.service.EUser_DataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.validation.constraints.Null;
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

    @RequestMapping(value = "/lobby")
    public List<EBattle> getAllLobby() {

        return battleService.getByDateOfEnding(null);
    }

    /*@RequestMapping(value = "/achievements")
    public List<EAchievement> getUserAchievement() {


    }*/
}
