package bs.kirill.service;

import bs.kirill.entity.EAchievement;

import java.util.List;

/**
 * Created by Администратор on 23.12.2016.
 */
public interface EAchievementService {
    EAchievement getByID(Integer ID);
    EAchievement getByName(String Nm);
    EAchievement addAchievement(EAchievement eAchievement);
    List<EAchievement> getAll();
}
