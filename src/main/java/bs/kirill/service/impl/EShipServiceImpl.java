package bs.kirill.service.impl;

import bs.kirill.entity.EShip;
import bs.kirill.repository.EShipRepository;
import bs.kirill.service.EShipService;
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
