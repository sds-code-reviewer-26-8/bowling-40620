package bowling;

public class Game {

    private static final int MAX_PINS = 10;

    private final int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int score() {
        int score = 0;
        int i = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(i)) {
                score += MAX_PINS + rolls[i + 1] + rolls[i + 2];
                i += 1;
            } else if (isSpare(i)) {
                score += MAX_PINS + rolls[i + 2];
                i += 2;
            } else {
                score += rolls[i] + rolls[i + 1];
                i += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int i) {
        return rolls[i] == MAX_PINS;
    }

    private boolean isSpare(int i) {
        return rolls[i] + rolls[i + 1] == MAX_PINS;
    }
}
