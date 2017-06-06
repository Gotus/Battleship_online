package bs.kirill.service;

import bs.kirill.entity.EUserData;

import java.util.List;

/**
 * Created by Администратор on 24.12.2016.
 */
public interface EUser_DataService
{
    EUserData getByUser_ID(Integer U_ID);
    EUserData getByMail(String Mail);
    EUserData addUser(EUserData usr);
    EUserData getByLogin(String login); //was added 6.5.2017 1:28
    EUserData updateUser(EUserData usr);
    List<EUserData> getAll();
}
