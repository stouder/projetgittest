package com.example.demo;

import java.util.Scanner;

public class SuiteConway {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int R = in.nextInt();
		int L = in.nextInt();

		String term = String.valueOf(R);

		for (int i = 1; i < L; i++) {
			term = generateNextTerm(term);
		}

		char[] charArray = term.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			System.out.print(charArray[i]);
			if (i != charArray.length - 1) {
				System.out.print(" ");
			}

		}

	}

	public static String generateNextTerm(String term) {
		StringBuilder nextTerm = new StringBuilder();
		int count = 1;
		for (int i = 0; i < term.length(); i++) {
			if (term.charAt(i) == term.charAt(i + 1)) {
				count++;
			} else {
				nextTerm.append(count).append(term.charAt(i));
				count = 1;
			}
		}
		return nextTerm.toString();

	}
}
