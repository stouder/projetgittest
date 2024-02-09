package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Solution2 {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Change {
		long bill10;
		long bill5;
		long coin2;
	}

	static Change optimal(long s) {
		Change change = new Change();
		int[] coins = { 10, 5, 2 };

		for (int coin : coins) {
			while (s >= coin) {
				s = s - coin;
				if (coin == 10) {
					change.bill10 += 1;
				}
				if (coin == 5) {
					change.bill5 += 1;
				}
				if (coin == 2) {
					change.coin2 += 1;
				}
			}
		}

		if (s != 0) {
			return null;
		}

		return change;
	}

	public static Change optimal1(int amount, int pos, Change change) {
		int[] coins = { 10, 5, 2 };

		if (amount == 0) {
			return change;
		}

		if (pos < coins.length && amount >= coins[pos]) {
			amount -= coins[pos];

			if (coins[pos] == 10) {
				change.bill10 += 1;
			} else if (coins[pos] == 5) {
				change.bill5 += 1;
			} else if (coins[pos] == 2) {
				change.coin2 += 1;
			}

			return optimal1(amount, pos, change);
		} else {
			if (pos < coins.length - 1) {
				return optimal1(amount, pos + 1, change);
			} else {
				return null;
			}
		}
	}

	public static void main(String[] args) {

		int pos = 0;
		Change change = new Change();

		int av = 15;

		Change c = optimal1(av, pos, change);
		if (c != null) {
			System.out.println(c.bill10 + " - " + c.bill5 + " - " + c.coin2);
		} else {
			System.out.println("PAs de rendu de monaie possible");
		}
	}
}
