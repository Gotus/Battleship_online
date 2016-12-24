package com.Kirill.service.impl;

import com.Kirill.entity.EAchievement;
import com.Kirill.repository.EAchievementRepository;
import com.Kirill.service.EAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Администратор on 23.12.2016.
 */
@Service("EAchievementService")
@Transactional
public class EAchievementServiceImpl implements EAchievementService
{
    @Autowired
    private EAchievementRepository ARepository;

    @Override
    public EAchievement getByID(Integer ID) {
        return null;
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
}
