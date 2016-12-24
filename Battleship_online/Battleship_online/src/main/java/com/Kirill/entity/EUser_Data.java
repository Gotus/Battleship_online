package com.Kirill.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Администратор on 22.12.2016.
 */
@Entity
@Table(name = "User_data", schema = "private")
public class EUser_Data
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private Integer User_ID;

    @Column(name = "Mail")
    private String Mail;

    @Column(name = "Login")
    private String Login;

    @Column(name = "Password")
    private String Password;

    @Column(name = "Date_of_registration")
    private Date Date_of_registration;

    public Integer getUser_ID(){return User_ID;}
    public  void setUser_ID(Integer U_ID){User_ID = U_ID;}
    public  String getMail(){return Mail;}
    public void setMail(String M){Mail = M;}
    public String getLogin(){return Login;}
    public void setLogin(String U_Log){Login = U_Log;}
    public String getPassword(){return Password;}
    public void setPassword(String U_Pwd){Password = U_Pwd;}
    public Date getDateOfRegistration(){return Date_of_registration;}
    public void setDate_of_registration(Date D_of_R){Date_of_registration = D_of_R;}
}
