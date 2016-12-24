package com.Kirill.service;

import com.Kirill.entity.EBattle;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * Created by Администратор on 24.12.2016.
 */
public interface EBattleService {
    EBattle addBattle(Long B_ID);
    EBattle getByBattle_ID(Long B_ID);
    EBattle getByCreation(Date D_of_C);
    EBattle getByHost_ID(Integer H_ID);
    EBattle getByOpponent_ID(Integer O_ID);
}
