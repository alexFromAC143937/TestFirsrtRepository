package HW_2023014;

import java.util.Comparator;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NextLoop {
    ExecutorService executorService;
    int max_init_player_number = Main.number_of_players;

    public void addPlayers() {
        for (int i = 0; i < Main.number_of_players; i++) {
            Main.players.add(new Player("Player" + i));
        }
    }
    public void CheckTheWinner() {
        double maxScore = getMaxScore();
        long countMaxScore = getCountMaxScore(maxScore);

        while (countMaxScore > 1) {

            Main.number_of_players = (int) countMaxScore;
            messageNextLoop();
            startNextLoop(maxScore);
            executorService.shutdown();
           try {
                executorService.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            maxScore = getMaxScore();
            countMaxScore = getCountMaxScore(maxScore);
            messageResultNextLoop(maxScore, countMaxScore);
            waitNextRound(3000);
        }
    }

    private void messageNextLoop() {
        System.out.printf("\n!!! ==== Next Loop ===== Number of Players: %s", Main.number_of_players);
    }

    private void messageResultNextLoop(double maxScore, long countMaxScore) {
        System.out.printf("\n============== Max score: %s", maxScore);
        System.out.printf("\n============== Number next players: %s", countMaxScore);
    }

    private double getMaxScore() {
        return Main.players.stream()
                .max(Comparator.comparing(Player::getScore))
                .get()
                .getScore();
    }

    private long getCountMaxScore(double maxScore) {
        return Main.players.stream()
                .filter(player -> (maxScore == player.getScore()))
                .count();
    }

    private void startNextLoop(double maxScore) {
        executorService = Executors.newFixedThreadPool(Main.number_of_players);

//        Main.players.stream()
//                .filter(player -> (maxScore == player.getScore()))
//                .map(player -> {
//                   // executorService.execute(player);
//                    player.start();
//                    return player;
          //        });

        for(int i=0;i<max_init_player_number;i++){
            if(Main.players.get(i).getScore() == maxScore){
                Main.players.get(i).setListPlayedPlayers(new HashSet<>());
                executorService.execute(Main.players.get(i));
            }
        }
    }
    private void waitNextRound(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}