package com.web.controller.gamelogic.achievementcheck;

import com.web.controller.gamelogic.achievementcheck.AchievementCheck;
import com.web.model.entities.Achievement;
import com.web.model.entities.Battle;

import java.util.ArrayList;

/**
 * Created by stalk on 05.05.2017.
 */
class AchievementCheckUnique extends AchievementCheck {
    private Battle battle;
    private ArrayList<Achievement> player1gotAchievement;
    private ArrayList<Achievement> player2gotAchievement;

    AchievementCheckUnique(Battle battle, ArrayList<Achievement> player1gotAchievement,
                           ArrayList<Achievement> player2gotAchievement) {
        super(battle);
        this.battle = battle;
        this.player1gotAchievement = player1gotAchievement;
        this.player2gotAchievement = player2gotAchievement;
    }


    //TODO forever alone

    void intuition() {
        char[] replay = battle.getReplay();

        if (replay[0] == '\0') {
            return;
        }
        for (Achievement a : player1gotAchievement) {
            if (a.getId() == 1) {
                return;
            }
        }
        for (Achievement a : player2gotAchievement) {
            if (a.getId() == 1) {
                return;
            }
        }

        if ((replay[3] == 3 && replay[0] == 1) || (replay[7] == 3 && replay[4] == 1)) {

            Achievement intuition = new Achievement();
            intuition.setId(1);
            intuition.setName("Intuition");
            intuition.setDescription("Kill 1-sized ship with your first shot");

            player1gotAchievement.add(intuition);
        }

        if ((replay[3] == 3 && replay[0] == 2) || (replay[7] == 3 && replay[4] == 2)) {

            Achievement intuition = new Achievement();
            intuition.setId(1);
            intuition.setName("Intuition");
            intuition.setDescription("Kill 1-sized ship with your first shot");

            player2gotAchievement.add(intuition);
        }
    }

    void firstBlood() {
        char[] replay = battle.getReplay();

        if (replay[0] == '\0') {
            return;
        }

        for (Achievement a : player1gotAchievement) {
            if (a.getId() == 2) {
                return;
            }
        }
        for (Achievement a : player2gotAchievement) {
            if (a.getId() == 2) {
                return;
            }
        }

        for (int i = 3; i < replay.length; i += 4) {
            if (replay[i] == '\0') {
                break;
            }
            if (replay[i] == 3) {

                Achievement firstBlood = new Achievement();
                firstBlood.setId(2);
                firstBlood.setName("First Blood!");
                firstBlood.setDescription("Kill ship of enemy, before he kills your ship");

                if (replay[i - 3] == 1) {
                    player1gotAchievement.add(firstBlood);
                } else {
                    player2gotAchievement.add(firstBlood);
                }
            }
        }
    }

}

