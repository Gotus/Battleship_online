package bs.web.model.entities;

import bs.web.controller.gamelogic.achievementcheck.AchievementCheck;

import java.util.ArrayList;

/**
 * Created by stalk on 01.05.2017.
 */
public class Battle {
    private ArrayList<Battlefield> battlefields = new ArrayList<Battlefield>();
    /*turn consist of 4
      replay[0]- what players turn (1 - is host, 2 - is guest)
      replay[1]- x coordinate to shoot(in letter, like A)
      replay[2]- y coordinate to shoot(number, like 1)
      replay[3]- shot result (1 - miss, 2 - hit, 3 - dead)
     */
    //private char[] replay;
    private StringBuilder replay;
    private AchievementCheck achievements;
    private Boolean hostIsReady, opponentIsReady, gameBegun, hostTurn, gameEnded;

    public Battle() {
        Battlefield firstField = new Battlefield();
        Battlefield secondField = new Battlefield();

        hostIsReady = Boolean.FALSE;
        opponentIsReady = Boolean.FALSE;

        battlefields.add(firstField);
        battlefields.add(secondField);

        //replay = new char[800];
        replay = new StringBuilder(); //added by Gotus
        //char end = '\0';
        //replay.append(end);
        achievements = new AchievementCheck(this);
    }

    public Boolean getGameBegun() {
        return gameBegun;
    }

    public void setGameBegun(Boolean gameBegun) {
        this.gameBegun = gameBegun;
    }

    public Boolean getGameEnded() { return gameEnded;}

    public void setGameEnded(Boolean gameEnded) { this.gameEnded = gameEnded;}

    public Boolean getHostTurn() {
        return hostTurn;
    }

    public void setHostTurn(Boolean hostTurn) {
        this.hostTurn = hostTurn;
    }

    public Boolean getHostIsReady() {
        return hostIsReady;
    }

   public void setHostIsReady(Boolean hostIsReady) {
        this.hostIsReady = hostIsReady;
    }

    public Boolean getOpponentIsReady() {
        return opponentIsReady;
    }

    public void setOpponentIsReady(Boolean opponentIsReady) {
        this.opponentIsReady = opponentIsReady;
    }

    public ArrayList<Battlefield> getBattlefields() {
        return battlefields;
    }

    public StringBuilder getReplay() {
        return replay;
    }

    public void setBattlefields(ArrayList<Battlefield> battlefields) {
        this.battlefields = battlefields;
    }

    public void setReplay(StringBuilder replay) {
        this.replay = replay;
    }

    public AchievementCheck getAchievements() {
        return achievements;
    }

    public void setAchievements(AchievementCheck achievements) {
        this.achievements = achievements;
    }
}
