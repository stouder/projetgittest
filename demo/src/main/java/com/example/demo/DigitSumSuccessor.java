package com.example.demo;

import java.util.Scanner;

public class DigitSumSuccessor {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		long N = in.nextLong();

		int sommeChiffres = sumDigit(N);
		N++;
		while (sommeChiffres != sumDigit(N)) {
			N++;
		}

		System.out.println("sommeChiffres : " + sommeChiffres);
		System.out.println("Next Number : " + N);
	}

	private static int sumDigit(Long l) {
		return String.valueOf(l).chars().map(Character::getNumericValue).sum();
	}
}
