package com.kirill.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Администратор on 22.12.2016.
 */
@Entity
@Table(name = "user_data", schema = "public")
public class EUser_Data
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "mail")
    private String mail;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "date_of_registration")
    private Date dateOfRegistration;

    public Integer getUser_ID(){return userID;}
    public  void setUser_ID(Integer U_ID){userID = U_ID;}
    public  String getMail(){return mail;}
    public void setMail(String M){mail = M;}
    public String getLogin(){return login;}
    public void setLogin(String U_Log){login = U_Log;}
    public String getPassword(){return password;}
    public void setPassword(String U_Pwd){password = U_Pwd;}
    public Date getDateOfRegistration(){return dateOfRegistration;}
    public void setDate_of_registration(Date D_of_R){dateOfRegistration = D_of_R;}
}