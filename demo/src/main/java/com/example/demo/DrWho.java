package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class DrWho {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int C = in.nextInt();
		List<Integer> budgets = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			budgets.add(in.nextInt());
		}

		in.close();

		int totalBudget = budgets.stream().reduce(0, Integer::sum);
		if (totalBudget < C) {
			System.out.println("IMPOSSIBLE");
			return;
		}

		List<Integer> contributions = new ArrayList<>();
		Collections.sort(budgets);

		Iterator<Integer> iterator = budgets.iterator();
		while (iterator.hasNext()) {
			int current = iterator.next();
			int mean = C / budgets.size();
			int value = Math.min(current, mean);
			contributions.add(value);
			C -= value;
			iterator.remove();
		}

		for (int contribution : contributions) {
			System.out.println(contribution);
		}
	}

}
