package bs.kirill.service.impl;

import bs.kirill.entity.EShip;
import bs.kirill.repository.EShipRepository;
import bs.kirill.service.EShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Администратор on 24.12.2016.
 */
@Service("EShipService")
public class EShipServiceImpl implements EShipService{

    @Autowired
    private EShipRepository SRepository;

    @Override
    public EShip addShip(EShip eShip) {
        EShip EShip = SRepository.saveAndFlush(eShip);
        return EShip;
    }
}
