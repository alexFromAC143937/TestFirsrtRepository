package HW_2023014;

import java.util.HashSet;
import java.util.concurrent.Exchanger;

public class Player extends Thread {
    public static Exchanger<Player> exchanger = new Exchanger<>();
    private String playerName;
    private double score;
    private Action action;
    private SingleGameResult singleGameResult;
    private HashSet<String> listPlayedPlayers = new HashSet<>();

    public Player(String playerName) {
        this.playerName = playerName;
      //  this.start();
    }

    public void  setListPlayedPlayers(HashSet<String> listPlayedPlayers){
        this.listPlayedPlayers = listPlayedPlayers;
    }
    public String getPlayerName(){
        return playerName;
    }
    public double getScore(){
        return score;
    }
    public Action getAction(){
        return action;
    }

     public void setScore(double score){
        this.score = score;
     }
    private SingleGameResult whoWins(Action myAction, Action contestantAction) {
        if (myAction == Action.STONE && contestantAction == Action.SCISSORS
                || myAction == Action.SCISSORS && contestantAction == Action.PAPER
                || myAction == Action.PAPER && contestantAction == Action.STONE) {
            return SingleGameResult.WIN;
        } else if (myAction == contestantAction) {
            return SingleGameResult.DRAW;
        } else {
            return SingleGameResult.LOSE;
        }
    }

    @Override
    public void run() {
        while(listPlayedPlayers.size()+1 < Main.number_of_players) {
            action = Action.generateRandomAction();
            Player contestant;
            try {
                contestant = Player.exchanger.exchange(this);

                if(!listPlayedPlayers.contains(contestant.getPlayerName())) {
                    singleGameResult = whoWins(action, contestant.getAction());
                    updatePlayerScore();
                    listPlayedPlayers.add(contestant.getPlayerName());
                    resultMessage(contestant.getPlayerName());
                    sleep(1000);
                }else{
                    warningMessage(contestant.getPlayerName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endSessionMessage();
    }
    private void updatePlayerScore(){
        score += singleGameResult.result;
    }

    private void resultMessage(String contestantName){
        System.out.printf("\nThe player: %s - played with a Contestant: %s. The result - %s (%s). Total score - %s ",
                playerName, contestantName, singleGameResult.toString(), singleGameResult.result, score);
    }

    private void endSessionMessage(){
        System.out.printf("\n --- FINISH ------ The player: %s - Finished. Played %s games. Total score - %s",
                playerName, (Main.number_of_players-1), score);
    }

    private void warningMessage(String contestantName){
        System.out.printf("\nWarning!!! Player: %s , played with the Contestant: %s",
                playerName, contestantName);
    }
}