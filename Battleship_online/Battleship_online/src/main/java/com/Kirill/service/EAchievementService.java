package com.Kirill.service;

import com.Kirill.entity.EAchievement;

import java.util.List;

/**
 * Created by Администратор on 23.12.2016.
 */
public interface EAchievementService {
    EAchievement getByID(Integer ID);
    EAchievement getByName(String Nm);
    List<EAchievement> getAll();
}
