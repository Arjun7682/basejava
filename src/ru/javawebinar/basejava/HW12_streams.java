package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HW12_streams {
    public static void main(String[] args) {
        int[] mas = {1, 2, 3, 3, 2, 3, 9, 7};
        System.out.println("minValue result: " + minValue(mas));
        System.out.println("oddOrEven result: " + oddOrEven(Arrays.stream(mas)
                .boxed()
                .collect(Collectors.toList())));
    }

    private static int minValue(int[] values) {
        List<Integer> integers = Arrays.stream(values)
                .distinct()
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        return IntStream.range(0, integers.size())
                .map(i -> ((int) Math.pow(10, i)) * integers.get(i))
                .sum();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream()
                .filter(integer -> (integers.stream()
                        .mapToInt(a -> a)
                        .sum() % 2 == 0) == (integer % 2 != 0))
                .collect(Collectors.toList());
    }
}
