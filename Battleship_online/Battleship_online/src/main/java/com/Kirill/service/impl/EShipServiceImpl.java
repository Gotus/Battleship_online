package com.Kirill.service.impl;

import com.Kirill.entity.EShip;
import com.Kirill.repository.EShipRepository;
import com.Kirill.service.EShipService;
import org.springframework.stereotype.Service;

/**
 * Created by Администратор on 24.12.2016.
 */
@Service(" EShipService")
public class EShipServiceImpl implements EShipService{
    EShipRepository SRepository;
    @Override
    public EShip addShip(EShip Ship) {
        EShip EShip = SRepository.saveAndFlush(Ship);
        return EShip;
    }
}
