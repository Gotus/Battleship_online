package com.Kirill.service.impl;

import com.Kirill.entity.EUser_Data;
import com.Kirill.repository.EUser_DataRepository;
import com.Kirill.service.EUser_DataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Администратор on 24.12.2016.
 */

@Service("EUser_DataService")
public class EUser_DataServiceImpl implements EUser_DataService
{
    EUser_DataRepository URepository;

    @Override
    public EUser_Data getByUser_ID(Integer U_ID)
    {
        return URepository.findByUser_ID(U_ID);
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
    public List<EUser_Data> getAll()
    {
        return (List<EUser_Data>)URepository.findAll();
    }
}
