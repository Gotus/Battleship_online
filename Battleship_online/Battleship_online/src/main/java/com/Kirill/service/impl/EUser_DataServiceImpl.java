package com.kirill.service.impl;

import com.kirill.entity.EUser_Data;
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
    public EUser_Data getByUser_ID(Integer U_ID)
    {
        return URepository.findByUserID(U_ID);
    }

    @Override
    public EUser_Data getByMail(String Mail)
    {
        return URepository.findByMail(Mail);
    }

    @Override
    public EUser_Data addUser(EUser_Data usr)
    {
        EUser_Data EUserData = URepository.saveAndFlush(usr);
        return EUserData;
    }

    @Override
    public EUser_Data getByLogin(String login)
    {
        return URepository.findByLogin(login);
    }

    @Override
    public List<EUser_Data> getAll()
    {
        return (List<EUser_Data>)URepository.findAll();
    }
}
