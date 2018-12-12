package puzzle;

import static java.util.Arrays.asList;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class Day12 {

    /**
     * 20.0% is done (took 0s, 3s total). Generation: 1000, Length: 1101, Sum: 25991, Plants: 25
     */
    private String prodState = "#.#.#..##.#....#.#.##..##.##..#..#...##....###..#......###.#..#.....#.###.#...#####.####...#####.#.#";
    private String testState = "#..#.#..##......###...###";
    private List<Rule> prodRules = asList(
            new Rule("..#..", "."),
            new Rule("#...#", "."),
            new Rule(".#...", "#"),
            new Rule("#.##.", "."),
            new Rule("..#.#", "#"),
            new Rule("#.#.#", "."),
            new Rule("###..", "#"),
            new Rule("###.#", "#"),
            new Rule(".....", "."),
            new Rule("....#", "."),
            new Rule(".##..", "#"),
            new Rule("#####", "."),
            new Rule("####.", "."),
            new Rule("..##.", "."),
            new Rule("##.#.", "#"),
            new Rule(".#..#", "#"),
            new Rule("##..#", "."),
            new Rule(".##.#", "."),
            new Rule(".####", "#"),
            new Rule("..###", "."),
            new Rule("...##", "#"),
            new Rule("#..##", "#"),
            new Rule("#....", "."),
            new Rule("##.##", "."),
            new Rule("#.#..", "."),
            new Rule("##...", "."),
            new Rule(".#.##", "#"),
            new Rule(".###.", "#"),
            new Rule("...#.", "."),
            new Rule("#.###", "."),
            new Rule("#..#.", "#"),
            new Rule(".#.#.", "."));
    private List<Rule> testRules = asList(
            new Rule("...##", "#"),
            new Rule("..#..", "#"),
            new Rule(".#...", "#"),
            new Rule(".#.#.", "#"),
            new Rule(".#.##", "#"),
            new Rule(".##..", "#"),
            new Rule(".####", "#"),
            new Rule("#.#.#", "#"),
            new Rule("#.###", "#"),
            new Rule("##.#.", "#"),
            new Rule("##.##", "#"),
            new Rule("###..", "#"),
            new Rule("###.#", "#"),
            new Rule("####.", "#"));


    private int shift = 0;
    private int generations = 20;
    private long generations2 = 200L;
    private long generations3 = 50000000000L;
    private String state = prodState.replaceAll("\\.", "i");
    private List<Rule> rules = prodRules;

    @Test
    public void run() {
        log(0);
        for (int i = 0; i < generations; i++) {
            nextGeneration();
            log(i + 1);
        }
        System.out.println("Pots sum after " + generations + " generations are: " + sum());

        state = prodState.replaceAll("\\.", "i");
        shift = 0;
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime last = start;
        for (int i = 0; i < generations2; i++) {
            nextGeneration();
            if ((i + 1) % 20 == 0) {
                System.out.println((100. * (i + 1) / (generations2)) + "% is done (took "
                        + Duration.between(last, LocalDateTime.now()).getSeconds() + "s, "
                        + Duration.between(start, LocalDateTime.now()).getSeconds() + "s total). Generation: " + (i + 1)
                        + ", Length: " + state.length() + ", Sum: " + sum() + ", Plants: "
                        + Arrays.stream(state.split("")).filter("#"::equals).count());
                last = LocalDateTime.now();
            }
        }
        long count = Arrays.stream(state.split("")).filter("#"::equals).count();
        System.out.println("Plants after " + generations2 + " generations are: " + count);
        long sum = sum();
        System.out.println("Pots sum after " + generations2 + " generations are: " + sum);

        //Every 20 generations from generation 100, sum is +500
        long steps = (generations3 - generations2) / 20;
        long result = sum + steps * 500;
        System.out.println("Pots sum after " + generations3 + " generations are: " + result);

    }

    private long sum() {
        long sum = 0;
        for (int idx = 0; idx < state.split("").length; idx++) {
            if (state.charAt(idx) == '#') {
                sum += idx - shift;
            }
        }
        return sum;
    }

    private void nextGeneration() {
        String newState = "iii" + state.replaceAll("#", "i") + "iii";
        String stateToCheck = "iii" + state + "iii";

        for (Rule rule : rules) {
            int i = -1;
            int lastIdx = -1;
            while ((i = stateToCheck.indexOf(rule.pattern, lastIdx + 1)) != -1) {
                newState = newState.substring(0, i + 2) + rule.result + newState.substring(i + 3);
                lastIdx = i;
            }

//            for (int idx = 0; idx < stateToCheck.length() - 4; idx++) {
//                if (stateToCheck.substring(idx, idx + 5).equals(rule.pattern)) {
//                    newState = newState.substring(0, idx + 2) + rule.result + newState.substring(idx + 3);
//                }
//            }
        }
        state = clear(newState);
    }

    private String clear(String newState) {
        if (newState.charAt(0) == 'i') {
            newState = newState.substring(1);
        } else {
            shift++;
        }
        if (newState.charAt(0) == 'i') {
            newState = newState.substring(1);
        } else {
            shift++;
        }
        if (newState.charAt(0) == 'i') {
            newState = newState.substring(1);
        } else {
            shift++;
        }
        if (newState.charAt(newState.length() - 1) == 'i') {
            newState = newState.substring(0, newState.length() - 1);
        }
        if (newState.charAt(newState.length() - 1) == 'i') {
            newState = newState.substring(0, newState.length() - 1);
        }
        if (newState.charAt(newState.length() - 1) == 'i') {
            newState = newState.substring(0, newState.length() - 1);
        }
        return newState;
    }

    private void log(int gen) {
        System.out.println(gen + ": " + state.replaceAll("i", "."));
    }

    private class Rule {

        private String pattern;
        private String result;

        public Rule(String pattern, String result) {
            this.pattern = pattern.replaceAll("\\.", "i");
            this.result = result.replaceAll("\\.", "i");
        }
    }
}