package com.example.demo;

import java.util.Arrays;

class ProgrammationDynamique {

    public static void main(String args[]) {
        int[] coins = {2, 5};
        int amount = 11;

        System.out.println(change(amount, coins));
    }

    private static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 0);
        dp[0] = 1; // si 0 on ne rend pas de monnaie. Donc une seule facon de rendre O.

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }
}

