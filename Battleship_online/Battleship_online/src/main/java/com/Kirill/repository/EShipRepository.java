package com.kirill.repository;

import com.kirill.entity.EShip;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Администратор on 23.12.2016.
 */
public interface EShipRepository extends JpaRepository<EShip, Long>{
    EShipRepository findByBattleID(Long S_ID);
    EShipRepository findByUserID(Integer U_ID);
}
