package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
6
3 2 4 2 1 5

6
5 3 4 2 3 1
*/
public class PerteBourse {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		if (n <= 0 || n >= 100000) {
			System.out.println("Invalid input. The number of values (n) must be between 1 and 99999.");
			return;
		}

		List<Integer> valeurs = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			int v = in.nextInt();
			if (v >= 0 || v <= (int) Math.pow(2, 31)) {
				valeurs.add(v);
			}

		}

		int high = 0;
		int perte = 0;

		for (int v : valeurs) {
			if (v > high) {
				high = v;
			} else if ((v - high) < perte) {
				perte = v - high;
			}
		}

		System.out.println("answer" + perte);
	}

}
