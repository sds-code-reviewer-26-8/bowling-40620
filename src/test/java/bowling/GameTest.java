package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    @DisplayName("거터 게임이면 0점이다")
    void gutterGame() {
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }
        assertEquals(0, game.score());
    }

    @Test
    @DisplayName("매 투구 1핀씩 쓰러뜨리면 20점이다")
    void allOnes() {
        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }
        assertEquals(20, game.score());
    }

    @Test
    @DisplayName("스페어 다음 투구가 보너스로 더해진다")
    void spare() {
        game.roll(5);
        game.roll(5);
        game.roll(3);
        for (int i = 0; i < 17; i++) {
            game.roll(0);
        }
        assertEquals(16, game.score());
    }

    @Test
    @DisplayName("스트라이크 다음 두 투구가 보너스로 더해진다")
    void strike() {
        game.roll(10);
        game.roll(3);
        game.roll(4);
        for (int i = 0; i < 16; i++) {
            game.roll(0);
        }
        assertEquals(24, game.score());
    }

    @Test
    @DisplayName("퍼펙트 게임이면 300점이다")
    void perfectGame() {
        for (int i = 0; i < 12; i++) {
            game.roll(10);
        }
        assertEquals(300, game.score());
    }

    @Test
    @DisplayName("10번째 프레임 스페어는 추가 투구 한 번이 보너스다")
    void tenthFrameSpare() {
        for (int i = 0; i < 18; i++) {
            game.roll(0);
        }
        game.roll(5);
        game.roll(5);
        game.roll(7);
        assertEquals(17, game.score());
    }

    @Test
    @DisplayName("10번째 프레임 스트라이크는 추가 투구 두 번이 보너스다")
    void tenthFrameStrike() {
        for (int i = 0; i < 18; i++) {
            game.roll(0);
        }
        game.roll(10);
        game.roll(7);
        game.roll(2);
        assertEquals(19, game.score());
    }

    @Test
    @DisplayName("스페어와 스트라이크가 섞인 일반 게임의 총점을 계산한다")
    void mixedGame() {
        int[] pins = {10, 7, 3, 9, 0, 10, 0, 8, 8, 2, 0, 6, 10, 10, 10, 8, 1};
        for (int p : pins) {
            game.roll(p);
        }
        assertEquals(167, game.score());
    }
}
