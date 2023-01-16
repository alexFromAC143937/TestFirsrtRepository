package HW_2023014;

import java.util.Random;

enum Action {
    STONE,
    SCISSORS,
    PAPER;

    protected static int PRE_RANDOM_ITERATION = 100;
    protected static int RANGE_NUMBER = 3;

    public static Action generateRandomAction() {
        Random random = new Random();
        for (int z = 0; z < PRE_RANDOM_ITERATION; z++) random.nextInt(RANGE_NUMBER);
        int j = random.nextInt(RANGE_NUMBER);

        return Action.values()[j];
    }
}