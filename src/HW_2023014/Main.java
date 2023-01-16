package HW_2023014;

import java.util.ArrayList;


/**
 * 1. 10 игроков
 * 2. Каждый играет с каждым
 * 3. Видно кто с кем играет/победитель/проигравший
 * 4. При одинаковых очках лидеров играют повторно.
 * 5. Выбор Action -> Random
 * 6. Main() только один метод который принимает список игроков
 * 7. ничья 0,5 / победа 1 / поражение 0
 * 8. Использовать Semaphore
 */
public class Main {
    public static int number_of_players = 10;
    public static ArrayList<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        NextLoop nextLoop = new NextLoop();
        nextLoop.addPlayers();
        nextLoop.CheckTheWinner();
    }
}