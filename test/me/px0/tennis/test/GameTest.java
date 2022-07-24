package me.px0.tennis.test;

import me.px0.tennis.Game;
import me.px0.tennis.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    Player p1;
    Player p2;
    Game game;

    @BeforeEach
    void setup() {
        p1 = new Player("Bob");
        p2 = new Player("Alice");
        game = new Game(p1, p2);
    }

    @Test
    void testDeuce() {
        for (int i = 0; i < 4; i++) {
            game.score(p1);
            game.score(p2);
        }
        assertTrue(game.isDeuce());
    }

    @Test
    void testWinner() {
        for (int i = 0; i < 5; i++) {
            game.score(p1);
        }
        assertSame(game.getWinner(), p1);
    }

    @Test
    void testWinnerWithDeuce() {
        for (int i = 0; i < 4; i++) {
            game.score(p1);
            game.score(p2);
        }
        game.score(p1);
        assertSame(game.getAdvantage(), p1);
        game.score(p1);
        assertSame(game.getWinner(), p1);
    }
}
