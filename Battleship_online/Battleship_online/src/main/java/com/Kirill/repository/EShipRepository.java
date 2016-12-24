package com.Kirill.repository;

import com.Kirill.entity.EShip;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Администратор on 23.12.2016.
 */
public interface EShipRepository extends JpaRepository<EShip, Long>{
    EShipRepository findByBattle_ID(Long S_ID);
    EShipRepository findByUser_ID(Integer U_ID);
}
