package puzzle;

import static java.lang.Math.abs;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.summingInt;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.junit.Test;

public class Day6 {

    private List<Point> prodPoints = asList(
            new Point("A1", 135, 127),
            new Point("B1", 251, 77),
            new Point("C1", 136, 244),
            new Point("D1", 123, 169),
            new Point("E1", 253, 257),
            new Point("F1", 359, 309),
            new Point("G1", 100, 247),
            new Point("H1", 191, 323),
            new Point("I1", 129, 323),
            new Point("J1", 76, 284),
            new Point("K1", 69, 56),
            new Point("L1", 229, 266),
            new Point("M1", 74, 216),
            new Point("N1", 236, 130),
            new Point("O1", 152, 126),
            new Point("P1", 174, 319),
            new Point("Q1", 315, 105),
            new Point("R1", 329, 146),
            new Point("S1", 288, 51),
            new Point("T1", 184, 344),
            new Point("U1", 173, 69),
            new Point("V1", 293, 80),
            new Point("W1", 230, 270),
            new Point("X1", 279, 84),
            new Point("Y1", 107, 163),
            new Point("Z1", 130, 176),
            new Point("A2", 347, 114),
            new Point("B2", 133, 331),
            new Point("C2", 237, 300),
            new Point("D2", 291, 283),
            new Point("E2", 246, 297),
            new Point("F2", 60, 359),
            new Point("G2", 312, 278),
            new Point("H2", 242, 76),
            new Point("I2", 81, 356),
            new Point("J2", 204, 291),
            new Point("K2", 187, 335),
            new Point("L2", 176, 98),
            new Point("M2", 103, 274),
            new Point("N2", 357, 144),
            new Point("O2", 314, 118),
            new Point("P2", 67, 196),
            new Point("Q2", 156, 265),
            new Point("R2", 254, 357),
            new Point("S2", 218, 271),
            new Point("T2", 118, 94),
            new Point("U2", 300, 189),
            new Point("V2", 290, 356),
            new Point("W2", 354, 91),
            new Point("X2", 209, 334)
    );
    private List<Point> testPoints = asList(
            new Point("A", 1, 1),
            new Point("B", 1, 6),
            new Point("C", 8, 3),
            new Point("D", 3, 4),
            new Point("E", 5, 5),
            new Point("F", 8, 9)
    );

    public static final int PROD_TRESHOLD = 10000;
    public static final int TEST_TRESHOLD = 32;

    private List<Point> points = prodPoints;
    public static final int TRESHOLD = PROD_TRESHOLD;

    @Test
    public void run() {
        String EMPTY_NAME = points.get(0).name.replaceAll(".", "\\.");
        Integer maxDistance = points.stream()
                .map(p -> points.stream()
                        .filter(target -> !p.equals(target))
                        .map(target -> distance(p, target))
                        .max(Integer::compareTo)
                        .orElse(0))
                .max(Integer::compareTo)
                .orElse(0);

        Integer maxX = points.stream().map(p -> p.x).max(Integer::compareTo).orElse(null);
        Integer maxY = points.stream().map(p -> p.y).max(Integer::compareTo).orElse(null);

        Set<Point> infinitePoints = new HashSet<>();
        Point[][] pointMap = new Point[maxY + 1][maxX + 2];
        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX + 1; x++) {
                pointMap[y][x] = nearestTo(new Point("test", x, y));
                Point point = ofNullable(pointMap[y][x]).orElse(new Point(EMPTY_NAME, -1, -1));
                if (point.x == x && point.y == y) {
                    System.out.print(point.name);
                } else {
                    System.out.print(point.name.toLowerCase());
                }
                if ((x == 0 || y == 0 || x == maxX + 1 || y == maxY) && pointMap[y][x] != null) {
                    infinitePoints.add(pointMap[y][x]);
                }
            }
            System.out.println();
        }
        System.out.println("Ignoring: " + infinitePoints);

        Point max = points.stream()
                .filter(p -> !infinitePoints.contains(p))
                .max(comparing(p -> count(p, pointMap)))
                .orElse(null);

        System.out.println("Max count " + count(max, pointMap) + " has " + max);

        Point[][] regionMap = new Point[maxY + 1][maxX + 2];
        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX + 1; x++) {
                Point regionPoint = new Point("#", x, y);

                Integer sumDistance = points.stream()
                        .map(p -> distance(p, regionPoint))
                        .collect(summingInt(Integer::intValue));

                regionMap[y][x] = sumDistance < TRESHOLD ? regionPoint : new Point(EMPTY_NAME, x, y);

                System.out.print(regionMap[y][x]);
            }
            System.out.println();
        }

        System.out.println("Total size of region is " + count(new Point("#", -1, -1), regionMap));

    }

    private long count(Point point, Point[][] pointMap) {
        return Arrays.stream(pointMap)
                .flatMap(line -> Arrays.stream(line))
                .filter(p -> p != null && p.name.equalsIgnoreCase(point.name))
                .count();
    }

    private Point nearestTo(Point target) {
        int min = points.stream().map(p -> distance(p, target)).min(Integer::compareTo).orElse(-1);
        long count = points.stream()
                .filter(p -> distance(p, target) == min)
                .count();

        if (count != 1) {
            return null;
        }
        return points.stream()
                .filter(p -> distance(p, target) == min)
                .findFirst().orElse(null);
    }

    public int distance(Point p1, Point p2) {
        return abs(p1.x - p2.x) + abs(p1.y - p2.y);
    }

    @AllArgsConstructor
    private static class Point {

        private String name;
        private int x;
        private int y;

        @Override
        public String toString() {
            return name;
        }
    }
}
