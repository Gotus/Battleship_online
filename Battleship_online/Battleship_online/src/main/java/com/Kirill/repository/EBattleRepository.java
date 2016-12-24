package com.Kirill.repository;

import com.Kirill.entity.EBattle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Created by Администратор on 23.12.2016.
 */
public interface EBattleRepository extends JpaRepository<EBattle, Long>
{
    EBattle findByBattleID(Long ID);
    EBattle findByDate_of_creation(Date D_of_C);
    EBattle findByHost_ID(Integer H_ID);
    EBattle findByOpponent_ID(Integer O_ID);
}
