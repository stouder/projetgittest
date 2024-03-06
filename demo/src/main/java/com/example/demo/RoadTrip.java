package com.example.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RoadTrip {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		double C = (double) in.nextInt();
		int P = in.nextInt();

		List<Friend> friends = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int budget = in.nextInt();
			int joy = in.nextInt();
			friends.add(new Friend((double) budget, joy));

		}

		int maxJoy = 0;

		for (int i = 1; i <= N; i++) {
			double costPerPerson = (C + P * i) / i;
			int tmpJoy = 0;

			List<Friend> friendsOk = friends.stream().filter(friend -> friend.getBudget() >= costPerPerson)
					.sorted(Comparator.comparingInt(Friend::getJoy).reversed()).collect(Collectors.toList());

			if (friendsOk.size() >= i - 1) {
				for (int j = 0; j < i - 1; j++) {
					tmpJoy += friendsOk.get(j).getJoy();
				}
			}

			maxJoy = Math.max(tmpJoy, maxJoy);
		}

		System.out.println(maxJoy);
	}

	public static class Friend {
		double budget;
		int joy;

		public Friend(double budget, int joy) {
			this.budget = budget;
			this.joy = joy;
		}

		double getBudget() {
			return budget;
		}

		int getJoy() {
			return joy;
		}
	}

}
