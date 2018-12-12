package puzzle;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Test;

public class Day10 {

    private List<Point> prodPoints = asList(
            new Point(52672, 52690, -5, -5),
            new Point(-20892, 10646, 2, -1),
            new Point(-10378, -52423, 1, 5),
            new Point(21199, -20892, -2, 2),
            new Point(21187, 42176, -2, -4),
            new Point(-31392, 10644, 3, -1),
            new Point(21152, 31665, -2, -3),
            new Point(-20880, -41905, 2, 4),
            new Point(21200, -10373, -2, 1),
            new Point(31652, 42178, -3, -4),
            new Point(-20873, 31671, 2, -3),
            new Point(52717, 42181, -5, -4),
            new Point(-10350, 10650, 1, -1),
            new Point(31691, -10372, -3, 1),
            new Point(-52390, 21157, 5, -2),
            new Point(-10354, 52690, 1, -5),
            new Point(52696, -41906, -5, 4),
            new Point(-10354, 42182, 1, -4),
            new Point(-20879, -20883, 2, 2),
            new Point(-52382, 52688, 5, -5),
            new Point(-41906, -20891, 4, 2),
            new Point(-10394, -31401, 1, 3),
            new Point(-20873, 31670, 2, -3),
            new Point(52700, 31663, -5, -3),
            new Point(-52414, -20888, 5, 2),
            new Point(-52438, 42182, 5, -4),
            new Point(42205, 10641, -4, -1),
            new Point(42169, 10646, -4, -1),
            new Point(-52406, 10647, 5, -1),
            new Point(42178, 52689, -4, -5),
            new Point(52725, -31399, -5, 3),
            new Point(-31372, -41905, 3, 4),
            new Point(10657, 31670, -1, -3),
            new Point(-31363, -20890, 3, 2),
            new Point(-10352, 52689, 1, -5),
            new Point(-41884, 42178, 4, -4),
            new Point(52720, -20883, -5, 2),
            new Point(21143, -31399, -2, 3),
            new Point(-31381, 52685, 3, -5),
            new Point(-20863, -52425, 2, 5),
            new Point(-20885, 21158, 2, -2),
            new Point(-31376, 52687, 3, -5),
            new Point(-20873, -31399, 2, 3),
            new Point(21155, 10648, -2, -1),
            new Point(42214, 52690, -4, -5),
            new Point(10636, -41906, -1, 4),
            new Point(-10365, -52416, 1, 5),
            new Point(42161, 21154, -4, -2),
            new Point(10644, -10380, -1, 1),
            new Point(-41867, 10641, 4, -1),
            new Point(-31390, 10650, 3, -1),
            new Point(31698, -20884, -3, 2),
            new Point(52709, -31402, -5, 3),
            new Point(10630, -52425, -1, 5),
            new Point(42186, 31663, -4, -3),
            new Point(-41887, -31399, 4, 3),
            new Point(42217, -52425, -4, 5),
            new Point(-52386, -52421, 5, 5),
            new Point(-20892, 42182, 2, -4),
            new Point(10657, -10376, -1, 1),
            new Point(-20892, -41908, 2, 4),
            new Point(21168, 42175, -2, -4),
            new Point(-31403, -20883, 3, 2),
            new Point(31695, 10649, -3, -1),
            new Point(31687, 31664, -3, -3),
            new Point(-41914, -10380, 4, 1),
            new Point(-10351, -20883, 1, 2),
            new Point(10678, 31667, -1, -3),
            new Point(52701, -31395, -5, 3),
            new Point(31686, -41914, -3, 4),
            new Point(-52398, -31399, 5, 3),
            new Point(-41887, 10642, 4, -1),
            new Point(42209, -52419, -4, 5),
            new Point(-52422, 52693, 5, -5),
            new Point(-20881, 52690, 2, -5),
            new Point(31671, -31400, -3, 3),
            new Point(-41924, -41910, 4, 4),
            new Point(-20852, 52689, 2, -5),
            new Point(42201, -52422, -4, 5),
            new Point(31691, 21161, -3, -2),
            new Point(-41870, -31399, 4, 3),
            new Point(-20889, -52423, 2, 5),
            new Point(42169, 21153, -4, -2),
            new Point(52672, -52420, -5, 5),
            new Point(52691, -10381, -5, 1),
            new Point(-41882, 52690, 4, -5),
            new Point(-31403, 10649, 3, -1),
            new Point(-41871, -20885, 4, 2),
            new Point(-52393, 21158, 5, -2),
            new Point(-41867, 31667, 4, -3),
            new Point(31686, -52421, -3, 5),
            new Point(-52385, -20887, 5, 2),
            new Point(-31412, -52425, 3, 5),
            new Point(-31395, 10643, 3, -1),
            new Point(52706, 52685, -5, -5),
            new Point(31670, 10645, -3, -1),
            new Point(-10374, -31397, 1, 3),
            new Point(-41922, -31403, 4, 3),
            new Point(-20862, -41905, 2, 4),
            new Point(52672, 21160, -5, -2),
            new Point(31693, -20883, -3, 2),
            new Point(-20889, -10375, 2, 1),
            new Point(-31360, 10642, 3, -1),
            new Point(10662, 21156, -1, -2),
            new Point(21158, -10377, -2, 1),
            new Point(-31384, 52691, 3, -5),
            new Point(10684, 10642, -1, -1),
            new Point(-20846, -31399, 2, 3),
            new Point(-52382, -20885, 5, 2),
            new Point(31710, -41908, -3, 4),
            new Point(-10349, -20890, 1, 2),
            new Point(52672, 10647, -5, -1),
            new Point(10644, -10380, -1, 1),
            new Point(-41882, 52686, 4, -5),
            new Point(-31356, 31670, 3, -3),
            new Point(-10346, -20885, 1, 2),
            new Point(-31384, 42183, 3, -4),
            new Point(-41895, -52416, 4, 5),
            new Point(52683, 10647, -5, -1),
            new Point(-10375, 21157, 1, -2),
            new Point(42205, 42174, -4, -4),
            new Point(-20889, -41905, 2, 4),
            new Point(10636, -52418, -1, 5),
            new Point(-20901, -52421, 2, 5),
            new Point(10681, -10373, -1, 1),
            new Point(-20889, -20888, 2, 2),
            new Point(-31387, -20891, 3, 2),
            new Point(-31371, 10646, 3, -1),
            new Point(-41879, -10372, 4, 1),
            new Point(31671, 31665, -3, -3),
            new Point(10689, -20883, -1, 2),
            new Point(10641, 52691, -1, -5),
            new Point(-41874, 10643, 4, -1),
            new Point(-52405, 10645, 5, -1),
            new Point(-20852, 10641, 2, -1),
            new Point(-41914, -20891, 4, 2),
            new Point(52699, 42183, -5, -4),
            new Point(10630, -10381, -1, 1),
            new Point(21147, -31400, -2, 3),
            new Point(42222, 52693, -4, -5),
            new Point(31695, -41913, -3, 4),
            new Point(31706, 21161, -3, -2),
            new Point(-31363, 31665, 3, -3),
            new Point(42193, -41914, -4, 4),
            new Point(52672, 31670, -5, -3),
            new Point(-10384, -20889, 1, 2),
            new Point(10684, 21156, -1, -2),
            new Point(-10341, -52421, 1, 5),
            new Point(-41876, 21156, 4, -2),
            new Point(52728, 42182, -5, -4),
            new Point(31677, -41909, -3, 4),
            new Point(-31395, 52693, 3, -5),
            new Point(52689, 10641, -5, -1),
            new Point(-31399, 10641, 3, -1),
            new Point(52717, 21155, -5, -2),
            new Point(-52398, -10375, 5, 1),
            new Point(-10375, 10641, 1, -1),
            new Point(42161, 21155, -4, -2),
            new Point(21139, -31402, -2, 3),
            new Point(-52425, -41905, 5, 4),
            new Point(-52406, -41911, 5, 4),
            new Point(-31389, 21157, 3, -2),
            new Point(-31388, 21157, 3, -2),
            new Point(-52425, -20885, 5, 2),
            new Point(-10394, -10372, 1, 1),
            new Point(-31384, -20891, 3, 2),
            new Point(42206, 31665, -4, -3),
            new Point(-52385, -31398, 5, 3),
            new Point(-10341, -31400, 1, 3),
            new Point(-10386, -52423, 1, 5),
            new Point(-10346, 52690, 1, -5),
            new Point(21164, -20883, -2, 2),
            new Point(42177, 10645, -4, -1),
            new Point(52725, 10644, -5, -1),
            new Point(42217, 52685, -4, -5),
            new Point(31650, -41911, -3, 4),
            new Point(-31379, -41912, 3, 4),
            new Point(-41887, 42181, 4, -4),
            new Point(-52422, -31396, 5, 3),
            new Point(-31376, -20889, 3, 2),
            new Point(-41878, 21156, 4, -2),
            new Point(10684, 31669, -1, -3),
            new Point(31677, -41905, -3, 4),
            new Point(-31376, -41905, 3, 4),
            new Point(-52427, -10376, 5, 1),
            new Point(21142, 42174, -2, -4),
            new Point(-20863, -52421, 2, 5),
            new Point(-52409, -41908, 5, 4),
            new Point(21192, 21158, -2, -2),
            new Point(-41895, -31400, 4, 3),
            new Point(52731, 52685, -5, -5),
            new Point(-52398, 10646, 5, -1),
            new Point(-31408, -20883, 3, 2),
            new Point(21197, 31667, -2, -3),
            new Point(-31389, 52685, 3, -5),
            new Point(-41874, 42177, 4, -4),
            new Point(-52382, 31664, 5, -3),
            new Point(-52385, -31396, 5, 3),
            new Point(42181, -31399, -4, 3),
            new Point(-20904, 10645, 2, -1),
            new Point(-10370, 42176, 1, -4),
            new Point(-52437, -31403, 5, 3),
            new Point(31659, -31402, -3, 3),
            new Point(10656, -41906, -1, 4),
            new Point(-41914, 42179, 4, -4),
            new Point(-20865, 21155, 2, -2),
            new Point(-10374, -31396, 1, 3),
            new Point(-20870, 42178, 2, -4),
            new Point(21195, 31670, -2, -3),
            new Point(21139, -31401, -2, 3),
            new Point(-31375, -31399, 3, 3),
            new Point(42174, -41905, -4, 4),
            new Point(31687, 31666, -3, -3),
            new Point(-31375, -10372, 3, 1),
            new Point(-10333, -52416, 1, 5),
            new Point(21167, 52693, -2, -5),
            new Point(42185, 52686, -4, -5),
            new Point(-10366, 52685, 1, -5),
            new Point(42185, -10373, -4, 1),
            new Point(52691, -52425, -5, 5),
            new Point(-41887, 52692, 4, -5),
            new Point(31663, 31666, -3, -3),
            new Point(42201, 31665, -4, -3),
            new Point(-41900, 42174, 4, -4),
            new Point(31710, 42181, -3, -4),
            new Point(-52382, -20890, 5, 2),
            new Point(42214, -41914, -4, 4),
            new Point(10647, -10377, -1, 1),
            new Point(31703, -20883, -3, 2),
            new Point(31690, -20883, -3, 2),
            new Point(10644, -20891, -1, 2),
            new Point(21152, 52691, -2, -5),
            new Point(-20873, 10648, 2, -1),
            new Point(-31408, -52423, 3, 5),
            new Point(31658, -41910, -3, 4),
            new Point(-10337, -41910, 1, 4),
            new Point(21196, -10381, -2, 1),
            new Point(42205, -10381, -4, 1),
            new Point(-10383, 52691, 1, -5),
            new Point(-10362, 52688, 1, -5),
            new Point(-52380, -41914, 5, 4),
            new Point(52704, 31665, -5, -3),
            new Point(52700, -31398, -5, 3),
            new Point(52714, 10650, -5, -1),
            new Point(42201, -52417, -4, 5),
            new Point(-20849, 21161, 2, -2),
            new Point(-20902, 42174, 2, -4),
            new Point(31692, -41905, -3, 4),
            new Point(10631, -20888, -1, 2),
            new Point(42197, 10641, -4, -1),
            new Point(-10351, 42174, 1, -4),
            new Point(10677, -20888, -1, 2),
            new Point(31701, 10645, -3, -1),
            new Point(-31400, 31670, 3, -3),
            new Point(21181, 21152, -2, -2),
            new Point(10673, 21154, -1, -2),
            new Point(52733, -10372, -5, 1),
            new Point(10644, -41908, -1, 4),
            new Point(42190, 10650, -4, -1),
            new Point(-20905, 52688, 2, -5),
            new Point(-10362, -10376, 1, 1),
            new Point(31711, 21155, -3, -2),
            new Point(10652, 52692, -1, -5),
            new Point(-41891, -31399, 4, 3),
            new Point(21171, -41908, -2, 4),
            new Point(31682, -10377, -3, 1),
            new Point(52705, 42174, -5, -4),
            new Point(52681, -41913, -5, 4),
            new Point(42164, -41914, -4, 4),
            new Point(-10346, -31397, 1, 3),
            new Point(-31363, 42175, 3, -4),
            new Point(42189, 10641, -4, -1),
            new Point(-20892, 42181, 2, -4),
            new Point(21155, -10381, -2, 1),
            new Point(10636, -52420, -1, 5),
            new Point(-41871, 52693, 4, -5),
            new Point(42212, 42178, -4, -4),
            new Point(31707, 42174, -3, -4),
            new Point(52728, 31668, -5, -3),
            new Point(31703, -20886, -3, 2),
            new Point(-52438, -10375, 5, 1),
            new Point(-20892, 21152, 2, -2),
            new Point(-31384, -41913, 3, 4),
            new Point(52677, -52425, -5, 5),
            new Point(-52438, 31668, 5, -3),
            new Point(-20885, 52685, 2, -5),
            new Point(-31384, -41914, 3, 4),
            new Point(31698, -10381, -3, 1),
            new Point(-52398, -10373, 5, 1),
            new Point(31709, -10381, -3, 1),
            new Point(10685, -31399, -1, 3),
            new Point(-41868, -10381, 4, 1),
            new Point(52688, -52417, -5, 5),
            new Point(10669, -31399, -1, 3),
            new Point(10628, -52424, -1, 5),
            new Point(31698, -31397, -3, 3),
            new Point(-52379, -10376, 5, 1),
            new Point(21187, 52687, -2, -5),
            new Point(-41914, 10649, 4, -1),
            new Point(42201, 10648, -4, -1),
            new Point(-41866, -31402, 4, 3),
            new Point(31650, 31663, -3, -3),
            new Point(-20901, -52421, 2, 5),
            new Point(42209, 21160, -4, -2),
            new Point(21150, -31398, -2, 3),
            new Point(31671, -20891, -3, 2),
            new Point(-20871, -52421, 2, 5),
            new Point(-10378, 52691, 1, -5),
            new Point(-10338, -20886, 1, 2),
            new Point(-52420, -20888, 5, 2),
            new Point(10676, 52690, -1, -5),
            new Point(-10394, -41914, 1, 4),
            new Point(-41869, -41910, 4, 4),
            new Point(52693, 52688, -5, -5),
            new Point(10629, 10641, -1, -1),
            new Point(-20848, -10381, 2, 1),
            new Point(-31400, 31668, 3, -3),
            new Point(-31375, 42174, 3, -4),
            new Point(-10378, -41909, 1, 4),
            new Point(21181, 31672, -2, -3),
            new Point(-41882, 21160, 4, -2),
            new Point(52688, 42179, -5, -4),
            new Point(-41879, -31399, 4, 3),
            new Point(-41914, -41907, 4, 4),
            new Point(-10358, 21156, 1, -2),
            new Point(21164, -20892, -2, 2),
            new Point(-20849, -31394, 2, 3),
            new Point(21179, -10381, -2, 1),
            new Point(-20897, -41910, 2, 4),
            new Point(31711, -31401, -3, 3),
            new Point(42177, -41911, -4, 4),
            new Point(42169, -31401, -4, 3),
            new Point(-31368, 31666, 3, -3),
            new Point(-10338, 42178, 1, -4),
            new Point(10681, -31394, -1, 3),
            new Point(-52401, -10378, 5, 1),
            new Point(52672, 31667, -5, -3),
            new Point(10647, -31399, -1, 3),
            new Point(10672, 42183, -1, -4),
            new Point(-52398, -10375, 5, 1),
            new Point(-31398, -31399, 3, 3),
            new Point(31675, -52416, -3, 5),
            new Point(-31389, -10381, 3, 1),
            new Point(42170, 10643, -4, -1),
            new Point(-52389, -10377, 5, 1),
            new Point(-31371, -41911, 3, 4),
            new Point(52672, 52692, -5, -5),
            new Point(31677, -41905, -3, 4),
            new Point(21155, -31395, -2, 3),
            new Point(10686, 52689, -1, -5),
            new Point(21165, 31663, -2, -3),
            new Point(-10373, 42176, 1, -4),
            new Point(31662, -10374, -3, 1),
            new Point(31671, -31395, -3, 3),
            new Point(-10386, -52418, 1, 5),
            new Point(-41898, 10649, 4, -1),
            new Point(-31357, -52421, 3, 5),
            new Point(-20849, 52688, 2, -5),
            new Point(-52428, 10645, 5, -1),
            new Point(-52396, -41914, 5, 4),
            new Point(21187, 52686, -2, -5),
            new Point(10689, 42175, -1, -4),
            new Point(21147, 21152, -2, -2),
            new Point(52713, -52421, -5, 5),
            new Point(-41906, -52416, 4, 5),
            new Point(42188, -31398, -4, 3),
            new Point(-52394, -20888, 5, 2),
            new Point(-41924, -52421, 4, 5),
            new Point(31703, -31395, -3, 3),
            new Point(-10378, -52421, 1, 5),
            new Point(-20881, 10642, 2, -1),
            new Point(31682, 21154, -3, -2),
            new Point(31710, 21158, -3, -2),
            new Point(-41911, -20883, 4, 2),
            new Point(10652, 31666, -1, -3),
            new Point(42173, -41906, -4, 4),
            new Point(-10382, -41907, 1, 4),
            new Point(-20888, -52421, 2, 5),
            new Point(-10333, -31400, 1, 3),
            new Point(21148, -52424, -2, 5),
            new Point(-31398, -10381, 3, 1),
            new Point(42185, 42180, -4, -4),
            new Point(21187, -52424, -2, 5),
            new Point(-20864, 10641, 2, -1),
            new Point(31684, -31403, -3, 3),
            new Point(21152, -41910, -2, 4),
            new Point(42174, -20888, -4, 2),
            new Point(42169, 52691, -4, -5),
            new Point(-10394, -20884, 1, 2),
            new Point(-41919, 31671, 4, -3),
            new Point(-52388, 10645, 5, -1),
            new Point(21165, -31394, -2, 3),
            new Point(-41898, 10646, 4, -1),
            new Point(-52385, 10647, 5, -1));

    private List<Point> testPoints = asList(
            new Point(9, 1, 0, 2),
            new Point(7, 0, -1, 0),
            new Point(3, -2, -1, 1),
            new Point(6, 10, -2, -1),
            new Point(2, -4, 2, 2),
            new Point(-6, 10, 2, -2),
            new Point(1, 8, 1, -1),
            new Point(1, 7, 1, 0),
            new Point(-3, 11, 1, -2),
            new Point(7, 6, -1, -1),
            new Point(-2, 3, 1, 0),
            new Point(-4, 3, 2, 0),
            new Point(10, -3, -1, 1),
            new Point(5, 11, 1, -2),
            new Point(4, 7, 0, -1),
            new Point(8, -2, 0, 1),
            new Point(15, 0, -2, 0),
            new Point(1, 6, 1, 0),
            new Point(8, 9, 0, -1),
            new Point(3, 3, -1, 1),
            new Point(0, 5, 0, -1),
            new Point(-2, 2, 2, 0),
            new Point(5, -2, 1, 2),
            new Point(1, 4, 2, 1),
            new Point(-2, 7, 2, -2),
            new Point(3, 6, -1, -1),
            new Point(5, 0, 1, 0),
            new Point(-6, 0, 2, 0),
            new Point(5, 9, 1, -2),
            new Point(14, 7, -2, 0),
            new Point(-3, 6, 2, -1));

    private List<Point> points = prodPoints;
    private int maxWidth = 100;

    @Test
    public void run() {
        int sec = 0;
        System.out.println("Shortening to width " + maxWidth);
        while (points.stream().max(comparing(p -> p.px)).map(Point::getPx).orElse(null)
                - points.stream().min(comparing(p -> p.px)).map(Point::getPx).orElse(null)
                > maxWidth) {
            points.forEach(Point::move);
            sec++;
        }
        System.out.println("Searching");
        int perc = calculate();
        while (perc < 100) {
            if (perc > 90) {
                draw();
            }
            points.forEach(Point::move);
            sec++;
            perc = calculate();
        }
        draw();
        System.out.println("Need to wait " + sec + "s");
    }

    private void draw() {
        int minX = points.stream().min(comparing(p -> p.px)).map(Point::getPx).orElse(null);
        int minY = points.stream().min(comparing(p -> p.py)).map(Point::getPy).orElse(null);
        int maxX = points.stream().max(comparing(p -> p.px)).map(Point::getPx).orElse(null);
        int maxY = points.stream().max(comparing(p -> p.py)).map(Point::getPy).orElse(null);

        boolean[][] map = new boolean[maxX - minX + 1][maxY - minY + 1];
        points.forEach(p -> map[p.px - minX][p.py - minY] = true);

        for (int y = 0; y < maxY - minY + 1; y++) {
            for (int x = 0; x < maxX - minX + 1; x++) {
                System.out.print(map[x][y] ? "#" : ".");
            }
            System.out.println();
        }
    }

    private int calculate() {
        int minX = points.stream().min(comparing(p -> p.px)).map(Point::getPx).orElse(null);
        int minY = points.stream().min(comparing(p -> p.py)).map(Point::getPy).orElse(null);
        int maxX = points.stream().max(comparing(p -> p.px)).map(Point::getPx).orElse(null);
        int maxY = points.stream().max(comparing(p -> p.py)).map(Point::getPy).orElse(null);

        System.out.println("Rectangle size is " + (maxX - minX + 1) + "x" + (maxY - minY + 1));

        boolean[][] map = new boolean[maxX - minX + 1][maxY - minY + 1];
        points.forEach(p -> map[p.px - minX][p.py - minY] = true);

        List<Integer> sums = Arrays.stream(map)
                .map(bArray -> {
                    int sum = 0;
                    for (boolean b : bArray) {
                        sum += b ? 1 : 0;
                    }
                    return sum;
                })
                .collect(Collectors.toList());
        int perc = sums.stream().max(Integer::compareTo).orElse(0) / (maxY - minY + 1) * 100;

        System.out.println("Max vertical fill is " + perc + "%\n");

        return perc;
    }


    @AllArgsConstructor
    @Getter
    private class Point {

        private int px, py, vx, vy;

        void move() {
            px += vx;
            py += vy;
        }

    }
}