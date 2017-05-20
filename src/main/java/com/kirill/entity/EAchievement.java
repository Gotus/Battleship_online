package com.kirill.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Администратор on 22.12.2016.
 */
@Entity
@Table(name = "achievement", schema = "public")
public class EAchievement
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    private Integer achievementID;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "column")
    private Integer column;

    @Column(name = "type")
    private Integer type;

    @Column(name = "image_link")
    private String imageLink;

    @ManyToMany(mappedBy = "achievementsOfUser")
    private Set<EUserData> users;

    public Integer getAchievementID(){return achievementID;}
    public void setAchievementID(Integer A_ID){achievementID = A_ID;}
    public String getDescription(){return description;}
    public void setDescription(String Descr){description = Descr;}
    public String getName(){return name;}
    public void setName(String A_name){name = A_name;}
    public Integer getColumn(){return column;}
    public void setColumn(Integer Clmn){column = Clmn;}
    public Integer getType(){return type;}
    public void setType(Integer Typ){type = Typ;}
    public String getImageLink(){return imageLink;}
    public void setImageLink(String Link){imageLink = Link;}
}
