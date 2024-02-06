package com.example.demo;

import java.util.List;
import java.util.function.Consumer;

@FunctionalInterface
interface CheckFirst {
    boolean check(Integer number);
}

public class PrimeFilter {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(2, 5, 8, 11, 13, 16, 17, 19, 23);
        filterDisplay(numbers, PrimeFilter::isPrime, n -> System.out.print(n + " "));
    }

    private static void filterDisplay(List<Integer> list, CheckFirst checker, Consumer<Integer> action) {
        list.stream().filter(checker::check).map(n -> n*n).forEach(action); // avec @FunctionalInterface
        list.stream().filter(n -> isPrime(n)).map(n -> n*n).forEach(action); // basique
    }

    private static boolean isPrime(Integer number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}

