package com.Kirill.service;

import com.Kirill.entity.EUser_Data;

import java.util.List;

/**
 * Created by Администратор on 24.12.2016.
 */
public interface EUser_DataService
{
    EUser_Data getByUser_ID(Integer U_ID);
    EUser_Data getByMail(String Mail);
    EUser_Data addUser(EUser_Data usr);
    List<EUser_Data> getAll();
}
