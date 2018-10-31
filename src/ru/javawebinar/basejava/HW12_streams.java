package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class HW12_streams {
    public static void main(String[] args) {
        int[] mas = {1, 2, 3, 3, 2, 3, 9, 7};
        System.out.println("minValue result: " + minValue(mas));
        System.out.println("oddOrEven result: " + oddOrEven(Arrays.stream(mas)
                .boxed()
                .collect(Collectors.toList())));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (acc, x) -> acc * 10 + x);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        AtomicInteger sum = new AtomicInteger();
        return integers.stream()
                .peek(sum::addAndGet)
                .collect(Collectors.partitioningBy(integer -> integer % 2 == 0))
                .get(sum.get() % 2 != 0);
    }
}
