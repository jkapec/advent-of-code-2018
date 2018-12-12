package puzzle;

import static java.util.Collections.singletonList;
import static java.util.Comparator.comparing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Test;

public class Day9 {

    private int maxMarble = 71223 * 100;
    private int players = 455;

    private int player = 0;
    private int idx = 0;
    private int marbleNumber = 0;
    private List<Integer> line = new ArrayList<>(singletonList(0));
    private Map<Integer, Long> playerScore = new HashMap<>();

    @Test
    public void run() {
        LocalDateTime last = LocalDateTime.now();
        for (int i = 0; i < maxMarble; i++) {
            addMarble();
            if (marbleNumber % (maxMarble / 100) == 0) {
                String perc = marbleNumber * 100 / maxMarble + "%";
                Entry<Integer, Long> winner = playerScore.entrySet().stream()
                        .max(comparing(e -> e.getValue()))
                        .orElse(null);
                long sec = Duration.between(last, LocalDateTime.now()).getSeconds();
                System.out.println(
                        "After " + perc + " (next " + sec + "s) higher score have player " + winner.getKey() + " with score "
                                + winner.getValue());
                last = LocalDateTime.now();
            }
        }

        Entry<Integer, Long> winner = playerScore.entrySet().stream()
                .max(comparing(e -> e.getValue()))
                .orElse(null);
        System.out.println("Winner is player " + winner.getKey() + " with score " + winner.getValue());
    }

    private void addMarble() {
        nextPlayer();

        ++marbleNumber;

        if (marbleNumber % 23 == 0) {
            idx -= 7;
            if (idx < 0) {
                idx = line.size() + idx;
            }
            score();
            line.remove(idx);
        } else {
            idx += 2;
            if (idx > line.size()) {
                idx = idx - line.size();
            }
            line.add(idx, marbleNumber);
        }

    }

    private void score() {
        if (playerScore.get(player) == null) {
            playerScore.put(player, 0L);
        }
        int score = marbleNumber;
        score += line.get(idx);
        playerScore.put(player, playerScore.get(player) + score);
    }

    private void nextPlayer() {
        if (++player > players) {
            player = 1;
        }
    }

    private void log() {
        String status = "";
        for (int i = 0; i < line.size(); i++) {
            if (i == idx) {
                status += "\t\t(" + line.get(i) + ")";
            } else {
                status += "\t\t " + line.get(i) + " ";
            }
        }
        System.out.println("[" + (player == 0 ? "-" : player) + "]" + status);
    }
}