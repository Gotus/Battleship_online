package com.Kirill.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.StringJoiner;

/**
 * Created by Администратор on 22.12.2016.
 */
@Entity
@Table(name = "Ship", schema = "private")
public class EShip
{
    @Column(name = "Battle_ID")
    private Long Battle_ID;

    @Column(name = "User_ID")
    private Integer User_ID;

    @Column(name = "Prow")
    private  String Prow;

    @Column(name = "Stern")
    private String Stern;

    @Column(name = "Length")
    private Integer Length;

    public Long getBattle_ID(){return Battle_ID;}
    public void setBattle_ID(Long B_ID){Battle_ID = B_ID;}
    public Integer getUser_ID(){return User_ID;}
    public void setUser_ID(Integer U_ID){User_ID = U_ID;}
    public String getProw(){return Prow;}
    public void setProw(String P){Prow = P;}
    public String getStern(){return Stern;}
    public void setStern(String Strn){Stern = Strn;}
    public Integer getLength(){return Length;}
    public void setLength(Integer Len){Length = Len;}
}
