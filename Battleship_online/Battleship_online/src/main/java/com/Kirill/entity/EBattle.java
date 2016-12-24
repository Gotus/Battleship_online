package com.Kirill.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * Created by Администратор on 22.12.2016.
 */

@Entity
@Table(name = "Battle", schema = "private")
public class EBattle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Battle_ID")
    private Long Battle_ID;

    @Column(name = "Host_ID")
    private Integer Host_ID;

    @Column(name = "Opponent_ID")
    private Integer Opponent_ID;

    @Column(name = "Replay")
    private String Replay;

    @Column(name = "Date_of_creation")
    private Date Date_of_creation;

    @Column(name = "Date_of_joining")
    private Date Date_of_joining;

    @Column(name = "Date_of_start")
    private Date Date_of_start;

    @Column(name = "Date_of_ending")
    private Date Date_of_ending;

    public Long getBattle_ID(){return Battle_ID;}
    public void setBattle_ID(Long B_ID){Battle_ID = B_ID;}
    public Integer getHost_ID(){return Host_ID;}
    public void setHost_ID(Integer H_ID){Host_ID = H_ID;}
    public Integer getOpponent_ID(){return Opponent_ID;}
    public void setOpponent_ID(Integer O_ID){Opponent_ID = O_ID;}
    public String getReplay(){return Replay;}
    public void setReplay(String Rep){Replay = Rep;}
    public Date getDate_of_creation(){return Date_of_creation;}
    public void setDate_of_creation(Date D_of_C){Date_of_creation = D_of_C;}
    public Date getDate_of_joining(){return Date_of_joining;}
    public void setDate_of_joining(Date D_of_J){Date_of_joining = D_of_J;}
    public Date getDate_of_start(){return Date_of_start;}
    public void setDate_of_start(Date D_of_S){Date_of_start = D_of_S;}
    public Date getDate_of_ending(){return Date_of_ending;}
    public void setDate_of_ending(Date D_of_E){Date_of_ending = D_of_E;}
}
