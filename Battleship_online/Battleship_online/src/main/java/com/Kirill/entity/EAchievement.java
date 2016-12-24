package com.Kirill.entity;



import javax.persistence.*;

/**
 * Created by Администратор on 22.12.2016.
 */
@Entity
@Table(name = "Achievement", schema = "private")
public class EAchievement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AchievementID")
    private Integer AchievementID;

    @Column(name = "Description")
    private String Description;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Column")
    private Integer Column;

    @Column(name = "Type")
    private Integer Type;

    @Column(name = "Image_link")
    private String Image_link;

    public Integer getAchievementID(){return AchievementID;}
    public void setAchievementID(Integer A_ID){AchievementID = A_ID;}
    public String getDescriptiom(){return Description;}
    public void setDescription(String Descr){Description = Descr;}
    public String getName(){return Name;}
    public void setName(String A_name){Name = A_name;}
    public Integer getColumn(){return Column;}
    public void setColumn(Integer Clmn){Column = Clmn;}
    public Integer getType(){return Type;}
    public void setType(Integer Typ){Type = Typ;}
    public String getImage_link(){return Image_link;}
    public void setImage_link(String Link){Image_link = Link;}
}
