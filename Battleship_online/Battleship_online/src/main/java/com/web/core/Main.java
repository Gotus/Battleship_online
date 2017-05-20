package com.web.core;

import com.web.controller.gamelogic.achievementcheck.AchievementCheck;
import com.web.controller.gamelogic.mainlogic.Fire;
import com.web.controller.gamelogic.mainlogic.Place;
import com.web.controller.gamelogic.record.Record;
import com.web.view.ConsoleOutput;

import static com.web.model.entities.preload.WhyNot.*;

/**
 * Created by stalk on 23.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        Fire fireclass = new Fire();
        Place placeclass = new Place();
        Record record = new Record(battle);
        AchievementCheck achievementCheck = new AchievementCheck(battle);
        ConsoleOutput consoleOutput = new ConsoleOutput();
        int fireresult;

        placeclass.place(player1battlefield, player1one1, C3, vertical);
        placeclass.place(player1battlefield, player1one2, E5, vertical);

        fireresult = fireclass.fire(player1battlefieldclass, C3);
        record.record(battle,2,C3,fireresult);
        achievementCheck.check();

        fireresult = fireclass.fire(player1battlefieldclass, E5);
        record.record(battle,2,E5,fireresult);
        achievementCheck.check();

        consoleOutput.consoleprint(player1battlefield);

    }
}
