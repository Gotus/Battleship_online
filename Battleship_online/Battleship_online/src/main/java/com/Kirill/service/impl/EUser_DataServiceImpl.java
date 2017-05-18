package com.kirill.service.impl;

import com.kirill.entity.EUserData;
import com.kirill.repository.EUser_DataRepository;
import com.kirill.service.EUser_DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Администратор on 24.12.2016.
 */

@Service("EUser_DataService")
public class EUser_DataServiceImpl implements EUser_DataService
{
    @Autowired
    EUser_DataRepository URepository;

    @Override
    public EUserData getByUser_ID(Integer U_ID)
    {
        return URepository.findByUserID(U_ID);
    }

    @Override
    public EUserData getByMail(String Mail)
    {
        return URepository.findByMail(Mail);
    }

    @Override
    public EUserData addUser(EUserData usr)
    {
        EUserData EUserData = URepository.saveAndFlush(usr);
        return EUserData;
    }

    @Override
    public EUserData getByLogin(String login)
    {
        return URepository.findByLogin(login);
    }

    @Override
    public List<EUserData> getAll()
    {
        return (List<EUserData>)URepository.findAll();
    }
}
