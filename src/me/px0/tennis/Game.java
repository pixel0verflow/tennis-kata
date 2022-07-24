package me.px0.tennis;

import java.util.HashMap;
import java.util.Map;

public class Game {

    public enum Score {
        LOVE,P15,P30,P40;
        private static final Score[] vals = values();
        public Score next() {
            return vals[(this.ordinal()+1) % vals.length];
        }
    }

    private final Player p1;
    private final Player p2;
    private final Map<Player, Score> scores;
    private Player advantage;

    private Player winner;

    public Game(String p1Name, String p2Name) {
        this.p1 = new Player(p1Name);
        this.p2 = new Player(p2Name);
        this.scores = new HashMap<>(Map.of(p1, Score.LOVE, p2, Score.LOVE));
    }

    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.scores = new HashMap<>(Map.of(p1, Score.LOVE, p2, Score.LOVE));
    }

    public void score(Player player) {
        if (winner != null) return;
        Player otherPlayer = p1 == player ? p2 : p1;
        if (isDeuce() && advantage == player) winner = player;
        else if (isDeuce() && advantage == null) advantage = player;
        else if (isDeuce() && advantage == otherPlayer) advantage = null;
        else if (scores.get(player) == Score.P40) winner = player;
        else scores.put(player, scores.get(player).next());
    }

    public boolean isDeuce() {
        return scores.get(p1) == scores.get(p2) && scores.get(p1) == Score.P40;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getAdvantage() {
        return advantage;
    }
}

