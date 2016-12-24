package com.Kirill.service.impl;

import com.Kirill.entity.EBattle;
import com.Kirill.repository.EBattleRepository;
import com.Kirill.service.EBattleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    public EBattle getByCreation(Date D_of_C)
    {
        return BRepository.findByDate_of_creation(D_of_C);
    }

    @Override
    public EBattle getByHost_ID(Integer H_ID) {
        return BRepository.findByHost_ID(H_ID);
    }

    @Override
    public EBattle getByOpponent_ID(Integer O_ID) {
        return BRepository.findByOpponent_ID(O_ID);
    }
}
