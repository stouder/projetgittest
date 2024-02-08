package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MultiplesFinder {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Entrez un nombre : ");
		int n = scanner.nextInt();
		List<Integer> multiples = new ArrayList<>();

		IntStream.rangeClosed(1, n).filter(i -> i % 3 == 0 || i % 5 == 0 || i % 7 == 0).forEach(multiples::add);

		multiples.forEach(System.out::println);

		scanner.close();
	}
}
