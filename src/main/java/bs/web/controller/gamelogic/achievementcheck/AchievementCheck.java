package bs.web.controller.gamelogic.achievementcheck;

import bs.web.model.entities.Achievement;
import bs.web.model.entities.Battle;

import java.util.ArrayList;

/**
 * Created by stalk on 05.05.2017.
 */
public class AchievementCheck {
    private ArrayList<Achievement> player1gotAchievement = new ArrayList<Achievement>();
    private ArrayList<Achievement> player2gotAchievement = new ArrayList<Achievement>();
    private Battle battle;

    private AchievementCheck() {}

    public AchievementCheck(Battle battle) {
        this.battle = battle;
    }

    ArrayList<Achievement> getPlayer1gotAchievement() {
        return player1gotAchievement;
    }

    ArrayList<Achievement> getPlayer2gotAchievement() {
        return player2gotAchievement;
    }

    Battle getBattle() {
        return battle;
    }

    public void setPlayer1gotAchievement(ArrayList<Achievement> player1gotAchievement) {
        this.player1gotAchievement = player1gotAchievement;
    }

    public void setPlayer2gotAchievement(ArrayList<Achievement> player2gotAchievement) {
        this.player2gotAchievement = player2gotAchievement;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public void check(){
        AchievementCheckUnique checkUnique = new AchievementCheckUnique(battle,player1gotAchievement,player2gotAchievement);
        checkUnique.intuition();
        checkUnique.firstBlood();

        AchievementCheckStreak checkStreak = new AchievementCheckStreak(battle,player1gotAchievement,player2gotAchievement);
        checkStreak.killStreak();
        checkStreak.missStreak();
    }
}
