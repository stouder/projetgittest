package com.example.demo;
import java.util.Arrays;

public class Solution2 {
	
	static Change optimal(long s) {
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

	static Change optimal0(long s) {

		Change change = new Change();

		if (s % 10 > 5 || s % 10 % 5 == 0 || s % 10 % 2 == 0) {
			change.bill10 = (long) s / 10;
			s = s - change.bill10 * 10;
		} else {
			change.bill10 = (long) s / 10 - 1;
			s = s - change.bill10 * 10;
		}

		if (s % 5 % 2 == 0) {
			change.bill5 = (long) s / 5;
			s = s - change.bill5 * 5;
		} else {
			change.bill5 = (long) s / 5 - 1;
			s = s - change.bill5 * 5;
		}

		change.coin2 = (long) s / 2;
		s = s - change.coin2 * 2;

		if (s != 0) {
			return null;
		}

		return change;
	}

	static Change optimal1(long s) {
		int[] coins = { 10, 5, 2 };

		Change change = new Change();
		for (int c : coins) {
			if (s > c) {
				optimal1(s - c);
			}
		}

		return change;
	}

	static int pos = 0;
	static Change change = new Change();

	public static Change optimal3(int amount) {
		int[] coins = { 10, 5, 2 };

		if (amount != 0) {
			if (amount >= coins[pos]) {
				amount -= coins[pos];

				if (coins[pos] == 10) {
					change.bill10 += 1;
				}

				if (coins[pos] == 5) {
					change.bill5 += 1;
				}

				if (coins[pos] == 2) {
					change.coin2 += 1;
				}

				optimal3(amount);
			} else {

				if (pos < coins.length - 1) {
					pos++;
					optimal3(amount);

				}
			}

		}

		if (amount != 0)
			return null;
		else
			return change;
	}

	public static void main(String args[]) {
		int av = 15;

		/*
		 * Change change = optimal(V, c, 0);
		 */
		Change c = optimal3(av);
		// if( change != null)
		if (c != null)
			System.out.println(c.bill10 + " - " + c.bill5 + " - " + c.coin2);
		// else
		// System.out.print("Null");
	}
}
