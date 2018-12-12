package puzzle;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.junit.Test;

public class Day7 {

    private List<Step> prodSteps = asList(
            new Step("M", "L"),
            new Step("S", "B"),
            new Step("M", "F"),
            new Step("P", "C"),
            new Step("V", "D"),
            new Step("J", "T"),
            new Step("S", "E"),
            new Step("V", "Z"),
            new Step("U", "X"),
            new Step("I", "G"),
            new Step("W", "V"),
            new Step("O", "H"),
            new Step("S", "M"),
            new Step("I", "K"),
            new Step("J", "I"),
            new Step("W", "A"),
            new Step("R", "J"),
            new Step("S", "Q"),
            new Step("R", "Y"),
            new Step("W", "O"),
            new Step("U", "R"),
            new Step("U", "P"),
            new Step("W", "S"),
            new Step("N", "U"),
            new Step("N", "W"),
            new Step("W", "P"),
            new Step("W", "J"),
            new Step("G", "F"),
            new Step("W", "U"),
            new Step("P", "Y"),
            new Step("I", "Z"),
            new Step("W", "R"),
            new Step("X", "T"),
            new Step("R", "Q"),
            new Step("P", "B"),
            new Step("U", "Z"),
            new Step("Y", "H"),
            new Step("A", "G"),
            new Step("P", "O"),
            new Step("D", "F"),
            new Step("Q", "F"),
            new Step("U", "T"),
            new Step("O", "I"),
            new Step("R", "K"),
            new Step("J", "E"),
            new Step("G", "Z"),
            new Step("W", "Y"),
            new Step("V", "L"),
            new Step("X", "E"),
            new Step("U", "E"),
            new Step("N", "A"),
            new Step("N", "G"),
            new Step("C", "B"),
            new Step("U", "M"),
            new Step("R", "G"),
            new Step("N", "R"),
            new Step("K", "M"),
            new Step("E", "C"),
            new Step("U", "B"),
            new Step("Y", "J"),
            new Step("H", "X"),
            new Step("W", "E"),
            new Step("Y", "A"),
            new Step("A", "I"),
            new Step("W", "D"),
            new Step("I", "B"),
            new Step("P", "H"),
            new Step("S", "A"),
            new Step("N", "P"),
            new Step("J", "V"),
            new Step("D", "L"),
            new Step("R", "C"),
            new Step("Y", "Z"),
            new Step("S", "F"),
            new Step("N", "O"),
            new Step("R", "X"),
            new Step("Q", "E"),
            new Step("Z", "L"),
            new Step("O", "D"),
            new Step("O", "Y"),
            new Step("N", "S"),
            new Step("X", "D"),
            new Step("A", "T"),
            new Step("Y", "Q"),
            new Step("J", "K"),
            new Step("S", "C"),
            new Step("P", "M"),
            new Step("R", "O"),
            new Step("Y", "E"),
            new Step("O", "V"),
            new Step("Q", "D"),
            new Step("J", "D"),
            new Step("R", "D"),
            new Step("P", "R"),
            new Step("O", "X"),
            new Step("K", "C"),
            new Step("R", "L"),
            new Step("U", "Q"),
            new Step("K", "G"),
            new Step("S", "I"),
            new Step("X", "L")
    );
    private List<Step> testSteps = asList(
            new Step("A", "C"),
            new Step("F", "C"),
            new Step("B", "A"),
            new Step("D", "A"),
            new Step("E", "B"),
            new Step("E", "D"),
            new Step("E", "F")
    );

    private List<Step> stepsDone = new ArrayList<>();
    private static int stepSeconds = 60;
    private List<Worker> workers = asList(new Worker(1), new Worker(2), new Worker(3), new Worker(4), new Worker(5));

    @Test
    public void run() {
        List<Step> steps = addMissingSteps(prodSteps);
        System.out.println(steps);

        steps = unifySteps(steps);
        System.out.println(steps);

        while (steps.size() > 0) {
            List<Step> available = getAvalable(steps);
            System.out.println("Available: " + available);
            Step found = available.stream()
                    .min(comparing(s -> s.name))
                    .orElse(null);

            System.out.println("Found: " + found);
            steps.remove(found);
            stepsDone.add(found);
        }

        System.out.println("Result: " + stepsDone.stream()
                .map(s -> s.name)
                .collect(joining()));

        stepsDone = new ArrayList<>();
        steps = addMissingSteps(prodSteps);
        System.out.println(steps);

        steps = unifySteps(steps);
        System.out.println(steps);

        int finalSize = steps.size();
        int second = 0;
        while (stepsDone.size() < finalSize) {
            for (Worker worker : workers) {
                if (worker.step != null && worker.step.isDone()) {
                    stepsDone.add(worker.step);
                    worker.step = null;
                }
            }

            List<Worker> freeWorkers = workers.stream().filter(w -> w.step == null).collect(toList());

            for (Worker freeWorker : freeWorkers) {
                List<Step> available = getAvalable(steps);
                if (available.size() > 0) {
                    Step found = available.stream()
                            .min(comparing(s -> s.name))
                            .orElse(null);
                    steps.remove(found);
                    freeWorker.step = found;
                }
            }

            for (Worker worker : workers) {
                if (worker.step != null) {
                    worker.step.time++;
                }
            }

            String done = stepsDone.stream().map(s -> s.name).collect(joining());
            System.out.println(second++ + "\t" + workers + "\t" + done);
        }
    }

    private List<Step> getAvalable(List<Step> steps) {
        Set<String> done = stepsDone.stream().map(s -> s.name).collect(toSet());
        return steps.stream()
                .filter(s -> s.previous.isEmpty() || s.previous.stream().allMatch(done::contains))
                .sorted(comparing(s -> s.name))
                .collect(toList());
    }


    private List<Step> addMissingSteps(List<Step> steps) {
        List<Step> allSteps = new ArrayList<>(steps);

        List<String> stepNames = steps.stream().map(s -> s.name).collect(toList());

        steps.stream()
                .flatMap(s -> s.previous.stream())
                .filter(s -> !stepNames.contains(s))
                .forEach(s -> allSteps.add(new Step(s, null)));

        return allSteps;
    }

    private List<Step> unifySteps(List<Step> steps) {
        List<Step> unifiedSteps = new ArrayList<>();

        for (Step step : steps) {
            Step unifiedStep = unifiedSteps.stream()
                    .filter(s -> s.name.equals(step.name))
                    .findFirst()
                    .orElse(null);

            if (unifiedStep == null) {
                unifiedSteps.add(step);
            } else {
                unifiedStep.previous.addAll(step.previous);
            }
        }
        return unifiedSteps;
    }

    private static class Step {

        private String name;
        private List<String> previous = new ArrayList<>();
        private int time = 0;

        public boolean isDone() {
            int start = 'A' - 1;
            return time == stepSeconds + name.charAt(0) - start;
        }

        public Step(String name, String previous) {
            this.name = name;
            if (previous != null) {
                this.previous.add(previous);
            }
        }

        @Override
        public String toString() {
            return name + " " + previous;
        }
    }


    @RequiredArgsConstructor
    private static class Worker {

        private final int id;
        private Step step;

        @Override
        public String toString() {
            return step != null ? step.name : ".";
        }
    }
}
