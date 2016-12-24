package com.Kirill.repository;

import com.Kirill.entity.EUser_Data;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Администратор on 23.12.2016.
 */
public interface EUser_DataRepository  extends JpaRepository<EUser_Data, Integer>
{
    EUser_Data findByUser_ID(Integer ID);
    EUser_Data findByMail(String Mail);
}
