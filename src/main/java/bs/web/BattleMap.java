package bs.web;

import bs.web.model.entities.Battle;

import java.util.HashMap;

/**
 * Created by Gotus on 05.06.2017.
 */
public class BattleMap {


    public static HashMap<Integer, Battle> battleHashMap = new HashMap<Integer, Battle>();

    public void setBattleHashMap(HashMap<Integer, Battle> battleHashMap) {

        this.battleHashMap = battleHashMap;
    }

    public HashMap<Integer, Battle> getBattleHashMap() {

        return battleHashMap;
    }
}
