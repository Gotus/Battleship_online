package bs.web.controller.gamelogic.achievementcheck;

import bs.web.model.entities.Achievement;
import bs.web.model.entities.Battle;

import java.util.ArrayList;

/**
 * Created by stalk on 05.05.2017.
 */
class AchievementCheckStreak extends AchievementCheck {
    private Battle battle;
    private ArrayList<Achievement> player1gotAchievement;
    private ArrayList<Achievement> player2gotAchievement;

    AchievementCheckStreak(Battle battle, ArrayList<Achievement> player1gotAchievement,
                           ArrayList<Achievement> player2gotAchievement) {
        super(battle);
        this.battle = battle;
        this.player1gotAchievement = player1gotAchievement;
        this.player2gotAchievement = player2gotAchievement;
    }

    void killStreak() {
        char[] replay = battle.getReplay();
        int player1max = 0;
        int player2max = 0;
        int buffer1 = 0;
        int buffer2 = 0;

        for (int i = 0; i < replay.length; i += 4) {
            if (replay[i] == 1) {
                switch (replay[i + 3]) {
                    case 1: buffer1 = 0; continue;
                    case 3: buffer1 += 1;
                }
                if (buffer1 > player1max) {
                    player1max = buffer1;
                }
            }
            if (replay[i] == 2) {
                switch (replay[i + 3]) {
                    case 1: buffer2 = 0; continue;
                    case 3: buffer2 += 1;
                }
                if (buffer2 > player2max) {
                    player2max = buffer2;
                }
            }
            if (replay[i] == '\0' || replay[i] == '\u0000'){
                break;
            }
        }

        boolean player1hasdoubletrouble = false;
        boolean player1hastrippleripple = false;
        boolean player1hasfourthwall = false;
        boolean player1hasrampage = false;
        boolean player1hassixthsense = false;
        boolean player1haswrathof7seas = false;
        boolean player1hasoctawaofwar = false;
        boolean player1hasnein = false;
        boolean player1haswhenismyturn = false;

        boolean player2hasdoubletrouble = false;
        boolean player2hastrippleripple = false;
        boolean player2hasfourthwall = false;
        boolean player2hasrampage = false;
        boolean player2hassixthsense = false;
        boolean player2haswrathof7seas = false;
        boolean player2hasoctawaofwar = false;
        boolean player2hasnein = false;
        boolean player2haswhenismyturn = false;

        for (Achievement a : player1gotAchievement) {
            int id = a.getId();

            switch (id) {
                case 3: player1hasdoubletrouble = true; break;
                case 4: player1hastrippleripple = true; break;
                case 5: player1hasfourthwall = true; break;
                case 6: player1hasrampage = true; break;
                case 7: player1hassixthsense = true; break;
                case 8: player1haswrathof7seas = true; break;
                case 9: player1hasoctawaofwar = true; break;
                case 10: player1hasnein = true; break;
                case 11: player1haswhenismyturn = true; break;
            }
        }

        for (Achievement a : player2gotAchievement) {
            int id = a.getId();

            switch (id) {
                case 3: player2hasdoubletrouble = true; break;
                case 4: player2hastrippleripple = true; break;
                case 5: player2hasfourthwall = true; break;
                case 6: player2hasrampage = true; break;
                case 7: player2hassixthsense = true; break;
                case 8: player2haswrathof7seas = true; break;
                case 9: player2hasoctawaofwar = true; break;
                case 10: player2hasnein = true; break;
                case 11: player2haswhenismyturn = true; break;
            }

        }

        if ((!player1hasdoubletrouble && player1max >= 2) || (!player2hasdoubletrouble && player2max >= 2)) {

            Achievement doubleTrouble = new Achievement();
            doubleTrouble.setId(3);
            doubleTrouble.setName("Double Trouble");
            doubleTrouble.setDescription("Kill 2 enemy ships in 1 turn");

            if (!player1hasdoubletrouble && player1max >= 2) {
                player1gotAchievement.add(doubleTrouble);
            }
            if (!player2hasdoubletrouble && player2max >= 2) {
                player2gotAchievement.add(doubleTrouble);
            }

        }

        if ((!player1hastrippleripple && player1max >= 3) || (!player2hastrippleripple && player2max >= 3)) {

            Achievement trippleripple = new Achievement();
            trippleripple.setId(4);
            trippleripple.setName("tRIPple RIPple");
            trippleripple.setDescription("Kill 3 enemy ships in 1 turn");

            if (!player1hastrippleripple && player1max >= 3) {
                player1gotAchievement.add(trippleripple);
            }
            if (!player2hastrippleripple && player2max >= 3) {
                player2gotAchievement.add(trippleripple);
            }
        }

        if ((!player1hasfourthwall && player1max >= 4) || (!player2hasfourthwall && player2max >= 4)) {

            Achievement fourthwall = new Achievement();
            fourthwall.setId(5);
            fourthwall.setName("Fourth Wall");
            fourthwall.setDescription("Kill 4 enemy ships in 1 turn");

            if (!player1hasfourthwall && player1max >= 4) {
                player1gotAchievement.add(fourthwall);
            }
            if (!player2hasfourthwall && player2max >= 4) {
                player2gotAchievement.add(fourthwall);
            }
        }

        if ((!player1hasrampage && player1max >= 5) || (!player2hasrampage && player2max >= 5)) {

            Achievement rampage = new Achievement();
            rampage.setId(6);
            rampage.setName("RAMPAGE!!!");
            rampage.setDescription("Kill 5 enemy ships in 1 turn");

            if (!player1hasrampage && player1max >= 5) {
                player1gotAchievement.add(rampage);
            }
            if (!player2hasrampage && player2max >= 5) {
                player2gotAchievement.add(rampage);
            }
        }

        if ((!player1hassixthsense && player1max >= 6) || (!player2hassixthsense && player2max >= 6)) {

            Achievement sixthsense = new Achievement();
            sixthsense.setId(7);
            sixthsense.setName("Sixth Sense");
            sixthsense.setDescription("Kill 6 enemy ships in 1 turn");

            if (!player1hassixthsense && player1max >= 6) {
                player1gotAchievement.add(sixthsense);
            }
            if (!player2hassixthsense && player2max >= 6) {
                player2gotAchievement.add(sixthsense);
            }
        }

        if ((!player1haswrathof7seas && player1max >= 7) || (!player2haswrathof7seas && player2max >= 7)) {

            Achievement wrathof7seas = new Achievement();
            wrathof7seas.setId(8);
            wrathof7seas.setName("Wrath of the 7 seas");
            wrathof7seas.setDescription("Kill 7 enemy ships in 1 turn");

            if (!player1haswrathof7seas && player1max >= 7) {
                player1gotAchievement.add(wrathof7seas);
            }
            if (!player2haswrathof7seas && player2max >= 7) {
                player2gotAchievement.add(wrathof7seas);
            }
        }

        if ((!player1hasoctawaofwar && player1max >= 8) || (!player2hasoctawaofwar && player2max >= 8)) {
            Achievement octawaofwar = new Achievement();
            octawaofwar.setId(9);
            octawaofwar.setName("Octawa of war");
            octawaofwar.setDescription("Kill 8 enemy ships in 1 turn");

            if (!player1hasoctawaofwar && player1max >= 8) {
                player1gotAchievement.add(octawaofwar);
            }
            if (!player2hasoctawaofwar && player2max >= 8) {
                player2gotAchievement.add(octawaofwar);
            }
        }

        if ((!player1hasnein && player1max >= 9) || (!player2hasnein && player2max >= 9)) {
            Achievement nein = new Achievement();
            nein.setId(10);
            nein.setName("!NEIN!");
            nein.setDescription("Kill 9 enemy ships in 1 turn");

            if (!player1hasnein && player1max >= 9) {
                player1gotAchievement.add(nein);
            }
            if (!player2hasnein && player2max >= 9) {
                player2gotAchievement.add(nein);
            }
        }

        if ((!player1haswhenismyturn && player1max == 10) || (!player2haswhenismyturn && player2max == 10)) {
            Achievement whenismyturn = new Achievement();
            whenismyturn.setId(11);
            whenismyturn.setName("When is my turn?");
            whenismyturn.setDescription("Kill all enemy ships in 1 turn. Win in 1 turn");

            if (!player1haswhenismyturn && player1max == 10) {
                player1gotAchievement.add(whenismyturn);
            }
            if (!player2haswhenismyturn && player2max == 10) {
                player2gotAchievement.add(whenismyturn);
            }
        }
    }

    void missStreak() {
        char[] replay = battle.getReplay();
        int player1max = 0;
        int player2max = 0;
        int buffer1 = 0;
        int buffer2 = 0;

        for (int i = 0; i < replay.length; i += 4) {
            if (replay[i] == 1) {
                switch (replay[i + 3]) {
                    case 1: buffer1 += 1; continue;
                    case 2: buffer1 = 0; break;
                    case 3: buffer1 = 0; break;
                }
                if (buffer1 > player1max) {
                    player1max = buffer1;
                }
            }
            if (replay[i] == 2) {
                switch (replay[i + 3]) {
                    case 1: buffer2 += 1; continue;
                    case 2: buffer2 = 0; break;
                    case 3: buffer2 = 0; break;
                }
                if (buffer2 > player2max) {
                    player2max = buffer2;
                }
            }
            if (replay[i] == '\0' || replay[i] == '\u0000'){
                break;
            }
        }

        boolean player1hasmissstreak = false;
        boolean player1hasunluckyday = false;
        boolean player1hasthisffeelling = false;
        boolean player1haspain = false;
        boolean player1hasmisserable = false;

        boolean player2hasmissstreak = false;
        boolean player2hasunluckyday = false;
        boolean player2hasthisfeelling = false;
        boolean player2haspain = false;
        boolean player2hasmisserable = false;

        for (Achievement a : player1gotAchievement) {
            int id = a.getId();

            switch (id) {
                case 12: player1hasmissstreak = true; break;
                case 13: player1hasunluckyday = true; break;
                case 14: player1hasthisffeelling = true; break;
                case 15: player1haspain = true; break;
                case 16: player1hasmisserable = true; break;
            }
        }

        for (Achievement a : player2gotAchievement) {
            int id = a.getId();

            switch (id) {
                case 12: player2hasmissstreak = true; break;
                case 13: player2hasunluckyday = true; break;
                case 14: player2hasthisfeelling = true; break;
                case 15: player2haspain = true; break;
                case 16: player2hasmisserable = true; break;
            }
        }


        if ((!player1hasmissstreak && player1max >= 10) || (!player2hasmissstreak && player2max >= 10)) {

            Achievement missstreak = new Achievement();
            missstreak.setId(12);
            missstreak.setName("miSSStreak");
            missstreak.setDescription("miss 10 times in a raw");

            if (!player1hasmissstreak) {
                player1gotAchievement.add(missstreak);
            }
            if (!player2hasmissstreak) {
                player2gotAchievement.add(missstreak);
            }

        }

        if ((!player1hasunluckyday && player1max >= 20) || (!player2hasunluckyday && player2max >= 20)) {

            Achievement unluckyday = new Achievement();
            unluckyday.setId(13);
            unluckyday.setName("Unlucky day");
            unluckyday.setDescription("miss 20 times in a raw");

            if (!player1hasunluckyday) {
                player1gotAchievement.add(unluckyday);
            }
            if (!player2hasunluckyday) {
                player2gotAchievement.add(unluckyday);
            }

        }

        if ((!player1hasthisffeelling && player1max >= 30) || (!player2hasthisfeelling && player2max >= 30)) {

            Achievement iknowthisfeeling = new Achievement();
            iknowthisfeeling.setId(14);
            iknowthisfeeling.setName("I know this feeling, bro");
            iknowthisfeeling.setDescription("miss 30 times in a raw");

            if (!player1hasthisffeelling) {
                player1gotAchievement.add(iknowthisfeeling);
            }
            if (!player2hasthisfeelling) {
                player2gotAchievement.add(iknowthisfeeling);
            }

        }

        if ((!player1haspain && player1max >= 40) || (!player2haspain && player2max >= 40)) {

            Achievement lifeispain = new Achievement();
            lifeispain.setId(15);
            lifeispain.setName("Life is pain");
            lifeispain.setDescription("miss 40 times in a raw");

            if (!player1haspain) {
                player1gotAchievement.add(lifeispain);
            }
            if (!player2haspain) {
                player2gotAchievement.add(lifeispain);
            }

        }

        if ((!player1hasmisserable && player1max >= 50) || (!player2hasmisserable && player2max >= 50)) {

            Achievement lifeispain = new Achievement();
            lifeispain.setId(16);
            lifeispain.setName("MISSerable");
            lifeispain.setDescription("miss 50 times in a raw");

            if (!player1hasmisserable) {
                player1gotAchievement.add(lifeispain);
            }
            if (!player2hasmisserable) {
                player2gotAchievement.add(lifeispain);
            }

        }

    }
}
