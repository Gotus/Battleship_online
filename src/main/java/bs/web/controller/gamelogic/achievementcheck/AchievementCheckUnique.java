package bs.web.controller.gamelogic.achievementcheck;

import bs.web.model.entities.Battle;
import bs.web.model.entities.Achievement;

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
        StringBuilder replay = battle.getReplay();
        System.out.println("size " + replay.length());

        if (replay.length() == 0) {
            return;
        }

        System.out.println("kill");
        for (Achievement a : player1gotAchievement) {
            if (a.getId() == 1) {
                return;
            }
        }
        System.out.println("me");
        for (Achievement a : player2gotAchievement) {
            if (a.getId() == 1) {
                return;
            }
        }

        if ((replay.charAt(3) == '3' && replay.charAt(0) == '1')) {

            Achievement intuition = new Achievement();
            intuition.setId(1);
            intuition.setName("Intuition");
            intuition.setDescription("Destroy 1-sized ship with your first shot");
            System.out.println("You are lucky!");
            player1gotAchievement.add(intuition);
        } else {

            if ((replay.length() == 8) && (replay.charAt(7) == '3' && replay.charAt(4) == '2')) {

                Achievement intuition = new Achievement();
                intuition.setId(1);
                intuition.setName("Intuition");
                intuition.setDescription("Destroy 1-sized ship with your first shot");

                player2gotAchievement.add(intuition);
            }
        }

        if ((replay.charAt(3) == '3' && replay.charAt(0) == '2')) {

            Achievement intuition = new Achievement();
            intuition.setId(1);
            intuition.setName("Intuition");
            intuition.setDescription("Destroy 1-sized ship with your first shot");

            player2gotAchievement.add(intuition);
        } else {

            if((replay.length() == 8) && (replay.charAt(7) == '3' && replay.charAt(4) == '1')) {

                Achievement intuition = new Achievement();
                intuition.setId(1);
                intuition.setName("Intuition");
                intuition.setDescription("Destroy 1-sized ship with your first shot");

                player1gotAchievement.add(intuition);
            }
        }
    }

    void firstBlood() {
        StringBuilder replay = battle.getReplay();

        if (replay.length() == 0) {
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

        for (int i = 3; i < replay.length(); i += 4) {

            if (replay.charAt(i) == '3') {

                Achievement firstBlood = new Achievement();
                firstBlood.setId(2);
                firstBlood.setName("First Blood!");
                firstBlood.setDescription("Destroy ship of enemy, before your ship will be destroyed");

                if (replay.charAt(i - 3) == '1') {
                    player1gotAchievement.add(firstBlood);
                } else {
                    player2gotAchievement.add(firstBlood);
                }
            }
        }
    }
}