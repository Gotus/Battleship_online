package bs.kirill.service.impl;

import bs.kirill.entity.EAchievement;
import bs.kirill.repository.EAchievementRepository;
import bs.kirill.service.EAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Администратор on 23.12.2016.
 */
@Service("EAchievementService")
public class EAchievementServiceImpl implements EAchievementService
{
    @Autowired
    private EAchievementRepository ARepository;

    @Override
    public EAchievement getByID(Integer ID) {
        return ARepository.findByAchievementID(ID);
    }

    @Override
    public EAchievement getByName(String Nm)
    {
        return ARepository.findByName(Nm);
    }

    @Override
    public List<EAchievement> getAll() {
        return (List<EAchievement>) ARepository.findAll();
    }

    @Override
    public EAchievement addAchievement(EAchievement eAchievement) {

        EAchievement EBattle = ARepository.saveAndFlush(eAchievement);
        return EBattle;
    }
}
