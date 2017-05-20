package com.kirill.repository;

import com.kirill.entity.EAchievement;
import com.kirill.entity.EUserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by Администратор on 23.12.2016.
 */
public interface EAchievementRepository extends JpaRepository<EAchievement, Integer>
{
    EAchievement findByAchievementID(Integer A_ID);
    EAchievement findByName(String Nm);
    List<EAchievement> findAll();
}
