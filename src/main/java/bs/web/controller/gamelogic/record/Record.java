package bs.web.controller.gamelogic.record;

import bs.web.model.entities.Battle;
import bs.web.model.entities.Coordinate;

import static bs.web.util.Convert.convertBack;

/**
 * Created by stalk on 05.05.2017.
 */
public class Record {
    public static void record(Battle battle, int player, Coordinate coordinate, int result) {
        StringBuilder replay = battle.getReplay();

        replay.append(player);
        replay.append(coordinate.getXx());
        replay.append(coordinate.getYy());
        replay.append(result);

        System.out.println(replay.length());

    }
}
