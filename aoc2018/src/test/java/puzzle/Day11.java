package puzzle;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import org.junit.Test;

public class Day11 {

    private int serialNumber = 7689;
    private int gridSize = 300;
    private int[][] powerLevels = new int[gridSize][gridSize];

    @Test
    public void run() {
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                powerLevels[x][y] = calculatePowerLevel(x, y);
            }
        }

        Result result = calculateTotalPowerBySquare(3);
        System.out.println("The largest total 3x3 square has a top-left corner of " + result.x + "," + result.y
                + " (with a total power of " + result.powerLevel + ")");

        Entry<Integer, Result> maxResult = IntStream.range(1, gridSize + 1).boxed()
                .collect(toMap(square -> square, this::calculateTotalPowerBySquare))
                .entrySet().stream()
                .max(comparing(e -> e.getValue().powerLevel))
                .orElse(null);

        System.out.println("The largest total square (with a total power of " + maxResult.getValue().powerLevel + ") is " +
                maxResult.getKey() + "x" + maxResult.getKey() + " and has a top-left corner of " + maxResult.getValue().x + "," +
                maxResult.getValue().y + ", so its identifier is " + maxResult.getValue().x + "," + maxResult.getValue().y + ","
                + maxResult.getKey() + ".");
    }

    private Result calculateTotalPowerBySquare(int square) {
        System.out.println("Counting for square of " + square + "x" + square);
        int[][] totalPowerLevels = new int[gridSize - square + 1][gridSize - square + 1];
        for (int y = 0; y < gridSize - square + 1; y++) {
            for (int x = 0; x < gridSize - square + 1; x++) {
                for (int sqY = 0; sqY < square; sqY++) {
                    for (int sqX = 0; sqX < square; sqX++) {
                        totalPowerLevels[x][y] += powerLevels[x + sqX][y + sqY];
                    }
                }
            }
        }

        Integer maxPowerLevel = Arrays.stream(totalPowerLevels)
                .flatMap(line -> Arrays.stream(line).boxed())
                .max(Integer::compareTo)
                .orElse(null);
        int xMax = 0;
        int yMax = 0;
        for (int y = 0; y < gridSize - square + 1; y++) {
            for (int x = 0; x < gridSize - square + 1; x++) {
                if (totalPowerLevels[x][y] == maxPowerLevel) {
                    xMax = x;
                    yMax = y;
                }
            }
        }
        return new Result(xMax, yMax, maxPowerLevel);
    }

    @AllArgsConstructor
    private class Result {

        private int x, y, powerLevel;

    }

    private int calculatePowerLevel(int x, int y) {
        int rackId = x + 10;
        return ((rackId * y + serialNumber) * rackId) / 100 % 10 - 5;
    }

}