package bs.web.controller.gamelogic.record;

import bs.web.model.entities.Battle;
import bs.web.model.entities.Coordinate;

import static bs.web.util.Convert.convertBack;

/**
 * Created by stalk on 05.05.2017.
 */
public class Record {
    private char[] replay;
    public Record(Battle battle){
        this.replay=battle.getReplay();
    }
    public void record(Battle battle, int player, Coordinate coordinate, int result) {
        char[] coordinateback = convertBack(coordinate);

        for (int i = 0; i < replay.length; i += 4) {
            if (replay[i] == '\0' || replay[i] == '\u0000') {
                replay[i] = (char) player;
                replay[i + 1] = coordinateback[0];
                replay[i + 2] = coordinateback[1];
                replay[i + 3] = (char) result;
                break;
            }
        }
    }

    public char[] getReplay() {
        return replay;
    }

    public void setReplay(char[] replay) {
        this.replay = replay;
    }
}
