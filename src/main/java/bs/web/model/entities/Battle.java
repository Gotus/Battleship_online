package bs.web.model.entities;

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
    private char[] replay = new char[800];
    private Boolean hostIsReady, opponentIsReady, allPlayersIsReady;

    public Battle() {
        Battlefield firstField = new Battlefield();
        Battlefield secondField = new Battlefield();

        hostIsReady = Boolean.FALSE;
        opponentIsReady = Boolean.FALSE;

        battlefields.add(firstField);
        battlefields.add(secondField);
    }

    public Boolean getHostIsReady() {
        return hostIsReady;
    }

    public Boolean getAllPlayersIsReady() { return allPlayersIsReady; }

    public void setAllPlayersIsReady(Boolean allPlayersIsReady) { this.allPlayersIsReady = allPlayersIsReady; }

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

    public char[] getReplay() {
        return replay;
    }

    public void setBattlefields(ArrayList<Battlefield> battlefields) {
        this.battlefields = battlefields;
    }

    public void setReplay(char[] replay) {
        this.replay = replay;
    }
}
