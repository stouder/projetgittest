package com.example.demo;

import java.util.*;

class Solution {

	public static void main(String args[]) {
		List<Integer> coins = new ArrayList<>();
		coins.add(1);
		coins.add(5);

		System.out.println(change(10, coins));
	}

	private static int change(int amount, List<Integer> coins) {
		Map<String, Integer> memo = new HashMap<>();
		return changeRecursive(amount, coins, memo);
	}

	private static int changeRecursive(int amount, List<Integer> coins, Map<String, Integer> memo) {
		if (amount < 0) {
			return 0;
		} else if (amount == 0) {
			return 1;
		} else if (memo.containsKey(amount + "-" + coins.size())) {
			return memo.get(amount + "-" + coins.size());
		} else {
			int nb = 0;
			for (int i = 0; i < coins.size(); i++) {
				int coin = coins.get(i);
				if (amount >= coin) {
					nb += changeRecursive(amount - coin, coins.subList(i, coins.size()), memo);
				}
			}
			
			memo.put(amount + "-" + coins.size(), nb);
			return nb;
		}
	}
}
