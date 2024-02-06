package com.example.demo;

class Coin {

	static int minCoins(int V) {

		if (V == 0)
			return 0;

		int[] coins = { 9, 6, 5, 3 };

		int res = Integer.MAX_VALUE;

		for (int c : coins) {

			if (c <= V) {
				int sub_res = minCoins(V - c);

				if (sub_res != Integer.MAX_VALUE && sub_res + 1 < res)
					res = sub_res + 1;
			}

		}
		return res;
	}

	public static int count(int n) {

		int[] table = new int[n + 1];

		int[] coins = { 9, 6, 5, 3 };
		int m = coins.length;

		table[0] = 1;

		for (int i = 0; i < m; i++)
			for (int j = coins[i]; j <= n; j++)
				table[j] += table[j - coins[i]];

		return table[n];
	}

	public static void main(String args[]) {
		int V = 12;
		System.out.println("Minimum coins required is " + count(V));

	}
}