package com.kirill.repository;

import com.kirill.entity.EBattle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Администратор on 23.12.2016.
 */
public interface EBattleRepository extends JpaRepository<EBattle, Long>
{
    EBattle findByBattleID(Long ID);
    List<EBattle> findByDateOfCreation(Date D_of_C);
    List<EBattle> findByDateOfEnding(Date D_of_E);
    List<EBattle> findByHostID(Integer H_ID);
    List<EBattle> findByOpponentID(Integer O_ID);
    List<EBattle> findAll();


}
