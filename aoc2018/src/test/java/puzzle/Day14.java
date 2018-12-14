package puzzle;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;

public class Day14 {

    private List<Integer> receipes = null;
    private int elf1Idx;
    private int elf2Idx;


    @Test
    public void run() {
        test(3, 7, 9);
        test(3, 7, 5);
        test(3, 7, 18);
        test(3, 7, 2018);
        test(3, 7, 209231);
        test(3, 7, "51589");
        test(3, 7, "01245");
        test(3, 7, "92510");
        test(3, 7, "59414");
        test(3, 7, "209231");
    }

    public void test(int elf1, int elf2, int after) {
        receipes = new ArrayList<>(asList(elf1, elf2));
        elf1Idx = 0;
        elf2Idx = 1;
        log();
        while (receipes.size() < (after + 10)) {
            newReceipes();
            log();
        }
        System.out.println("After receipe " + after + ": "
                + receipes.subList(after, after + 10).stream().map(Object::toString).collect(joining("")));
    }

    public void test(int elf1, int elf2, String pattern) {
        receipes = new ArrayList<>(asList(elf1, elf2));
        elf1Idx = 0;
        elf2Idx = 1;
        log();
        String lastReceipes = "";
        while (lastReceipes.length() == 0 || !lastReceipes.contains(pattern)) {
            newReceipes();
            log();
            if (receipes.size() > pattern.length()) {
                lastReceipes = receipes.subList(receipes.size() - pattern.length() - 1, receipes.size())
                        .stream().map(Object::toString).collect(joining(""));
            }
        }
        System.out.println("Receipes: " + pattern + " start at receipe number: "
                + (receipes.size() - pattern.length() - 1 + lastReceipes.indexOf(pattern)));
    }

    private void newReceipes() {
        int elf1Receipe = receipes.get(elf1Idx);
        int elf2Receipe = receipes.get(elf2Idx);
        int firstReceipe = (elf1Receipe + elf2Receipe) / 10;
        if (firstReceipe > 0) {
            receipes.add(firstReceipe);
        }
        receipes.add((elf1Receipe + elf2Receipe) % 10);
        elf1Idx += 1 + elf1Receipe;
        elf2Idx += 1 + elf2Receipe;
        elf1Idx = elf1Idx % receipes.size();
        elf2Idx = elf2Idx % receipes.size();
    }

    private void log() {
        if (receipes.size() > 30) {
            return;
        }
        AtomicInteger i = new AtomicInteger(0);
        System.out.println(receipes.stream()
                .map(r -> {
                    int idx = i.getAndAdd(1);
                    return elf1Idx == idx ? "(" + r + ")" : elf2Idx == idx ? "[" + r + "]" : " " + r + " ";
                })
                .collect(joining(" ")));
    }

}