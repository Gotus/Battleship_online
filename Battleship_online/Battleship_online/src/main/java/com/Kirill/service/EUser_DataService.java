package com.kirill.service;

import com.kirill.entity.EUser_Data;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Администратор on 24.12.2016.
 */
public interface EUser_DataService
{
    EUser_Data getByUser_ID(Integer U_ID);
    EUser_Data getByMail(String Mail);
    EUser_Data addUser(EUser_Data usr);
    EUser_Data getByLogin(String login); //was added 6.5.2017 1:28
    List<EUser_Data> getAll();
}
