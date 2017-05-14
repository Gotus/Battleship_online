package com.kirill.repository;

import com.kirill.entity.EUser_Data;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Администратор on 23.12.2016.
 */
public interface EUser_DataRepository  extends JpaRepository<EUser_Data, Integer>
{
    EUser_Data findByUserID(Integer ID);
    EUser_Data findByMail(String Mail);
    EUser_Data findByLogin(String login);
}
