package bs.kirill.repository;

import bs.kirill.entity.EAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Администратор on 23.12.2016.
 */
public interface EAchievementRepository extends JpaRepository<EAchievement, Integer>
{
    EAchievement findByAchievementID(Integer A_ID);
    EAchievement findByName(String Nm);
    List<EAchievement> findAll();
}
