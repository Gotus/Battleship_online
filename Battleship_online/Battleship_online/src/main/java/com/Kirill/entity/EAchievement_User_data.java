package com.Kirill.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Администратор on 22.12.2016.
 */




@Entity
@Table(name = "Achievement_User_data", schema = "private")
public class EAchievement_User_data {

    @Column(name = "Achievement_ID")
    private Integer Achievement_ID;

    @Column(name = "User_ID")
    private Integer User_ID;

    public Integer getAchievement_ID(){return Achievement_ID;}
    public void setAchievement_ID(Integer A_ID){Achievement_ID = A_ID;}
    public Integer getUser_ID(){return User_ID;}
    public void setUser_ID(Integer U_ID){User_ID = U_ID;}
}
