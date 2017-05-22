package bs.kirill.repository;

import bs.kirill.entity.EUserData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Администратор on 23.12.2016.
 */
public interface EUser_DataRepository  extends JpaRepository<EUserData, Integer>
{
    EUserData findByUserID(Integer ID);
    EUserData findByMail(String Mail);
    EUserData findByLogin(String login);
}
