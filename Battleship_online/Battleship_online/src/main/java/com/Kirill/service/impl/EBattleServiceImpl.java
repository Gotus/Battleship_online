package com.kirill.service.impl;

import com.kirill.entity.EBattle;
import com.kirill.repository.EBattleRepository;
import com.kirill.service.EBattleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Администратор on 24.12.2016.
 */
@Service("EBattleService")
@Transactional
public class EBattleServiceImpl implements EBattleService
{

    private EBattleRepository BRepository;
    @Override
    public EBattle addBattle(Long B_ID) {
        return null;
    }

    @Override
    public EBattle getByBattle_ID(Long B_ID)
    {
        return BRepository.findByBattleID(B_ID);
    }

    @Override
    public List<EBattle> getByDateOfCreation(Date D_of_C)
    {
        return BRepository.findByDateOfCreation(D_of_C);
    }

    @Override
    public List<EBattle> getByDateOfEnding(Date D_of_E) { return  BRepository.findByDateOfEnding(D_of_E);}

    @Override
    public List<EBattle> getByHost_ID(Integer H_ID) {
        return BRepository.findByHostID(H_ID);
    }

    @Override
    public List<EBattle> getByOpponent_ID(Integer O_ID) {

        return BRepository.findByOpponentID(O_ID);
    }

    @Override
    public List<EBattle> getAll() {

        return BRepository.findAll();
    }
}
