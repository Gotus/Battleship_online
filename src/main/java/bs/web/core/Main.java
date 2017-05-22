package bs.web.core;

import bs.web.controller.gamelogic.achievementcheck.AchievementCheck;
import bs.web.controller.gamelogic.mainlogic.Fire;
import bs.web.controller.gamelogic.mainlogic.Place;
import bs.web.controller.gamelogic.record.Record;
import bs.web.model.entities.preload.WhyNot;
import bs.web.view.ConsoleOutput;

/**
 * Created by stalk on 23.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        Fire fireclass = new Fire();
        Place placeclass = new Place();
        Record record = new Record(WhyNot.battle);
        AchievementCheck achievementCheck = new AchievementCheck(WhyNot.battle);
        ConsoleOutput consoleOutput = new ConsoleOutput();
        int fireresult;

        placeclass.place(WhyNot.player1battlefield, WhyNot.player1one1, WhyNot.C3, WhyNot.vertical);
        placeclass.place(WhyNot.player1battlefield, WhyNot.player1one2, WhyNot.E5, WhyNot.vertical);

        fireresult = fireclass.fire(WhyNot.player1battlefieldclass, WhyNot.C3);
        record.record(WhyNot.battle,2, WhyNot.C3,fireresult);
        achievementCheck.check();

        fireresult = fireclass.fire(WhyNot.player1battlefieldclass, WhyNot.E5);
        record.record(WhyNot.battle,2, WhyNot.E5,fireresult);
        achievementCheck.check();

        consoleOutput.consoleprint(WhyNot.player1battlefield);

    }
}
