package bs.kirill.util;

import bs.kirill.entity.EBattle;
import bs.kirill.entity.EUserData;
import bs.kirill.service.EBattleService;
import bs.kirill.service.EUser_DataService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Gotus on 05.06.2017.
 */
@Component
public class ClassExecutingTask {

    //private Timer timer = new Timer();
    //private Date executionDate = new Date();//right now

    @Resource(name = "EBattleService")
    private EBattleService battleService;

    @Resource(name = "EUser_DataService")
    private EUser_DataService user_dataService;

    @Scheduled(fixedRate = 300000)
    public void tick() {
        // Получить дату текущую
        // убить все старые игры
        // доне и комплите.
        //System.out.println("heh");

        Date currentDateTime = new  Date();
        List<EBattle> allActiveBattles = new ArrayList<>();
        allActiveBattles = battleService.getByDateOfEnding(null);
        long milliseconds;
        //System.out.println(allActiveBattles.size());

        if (allActiveBattles.size() == 0){

            return;
        }
        for (int i = 0; i < allActiveBattles.size(); i ++ ) {

            allActiveBattles.get(i);
            System.out.println(i + " " + allActiveBattles.get(i).getDate_of_last_action());
            allActiveBattles.get(i).getDate_of_last_action().getTime();
            milliseconds = currentDateTime.getTime() - allActiveBattles.get(i).getDate_of_last_action().getTime();

            System.out.println(currentDateTime.getTime());
            System.out.println(allActiveBattles.get(i).getDate_of_last_action().getTime());
            System.out.println(milliseconds/1000);
            if (milliseconds/(60*1000) > 5){

                EUserData host = new EUserData();

                allActiveBattles.get(i).setDate_of_ending(new Date());
                battleService.addBattle(allActiveBattles.get(i));

                host = user_dataService.getByUser_ID(allActiveBattles.get(i).getHost_ID());
                host.setCurrentBattle(null);
                user_dataService.updateUser(host);
                if (allActiveBattles.get(i).getDate_of_joining() != null){

                    EUserData opponent = new EUserData();
                    opponent = user_dataService.getByUser_ID(allActiveBattles.get(i).getOpponent_ID());
                    opponent.setCurrentBattle(null);
                    user_dataService.updateUser(opponent);
                }
            }
            System.out.println("I checked");
        }
    }
    /*
    public void setBattleService(EBattleService battleService) {this.battleService = battleService;}
    public EBattleService getBattleService() {return battleService;}

    public void start() {
        timer.cancel();
        timer = new Timer("TaskName");

        long delay = 10 * 1000;
        timer.scheduleAtFixedRate(task, executionDate, delay);
    }

    private class LoopTask extends TimerTask {

        public void run() {
            Boolean isRotten = false;
            List<EBattle> allActiveBattles = new ArrayList<>();
            allActiveBattles = battleService.getByDateOfEnding(null);

            for (int i = 0; i < allActiveBattles.size(); i ++ ) {

                if (allActiveBattles.get(i).getDateTimeteOfLastAction().plusMinutes(5).isAfter(LocalDateTime.now()) ){

                    allActiveBattles.get(i).setDate_of_ending(new Date());
                }
                System.out.println("I checked");
            }
        }
    }*/
}

