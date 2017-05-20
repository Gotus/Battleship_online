package com.kirill.service;

import com.kirill.entity.EBattle;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

/**
 * Created by Администратор on 24.12.2016.
 */
public interface EBattleService {
    EBattle addBattle(EBattle eBattle);
    EBattle getByBattle_ID(Long B_ID);
    List<EBattle> getByDateOfCreation(Date D_of_C);
    List<EBattle> getByDateOfEnding(Date D_of_E);
    List<EBattle> getByHost_ID(Integer H_ID);
    List<EBattle> getByOpponent_ID(Integer O_ID);
    List<EBattle> getAll();
}
