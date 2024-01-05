package com.example.demo;
import java.util.ArrayList;
import java.util.List;

public class Coins {
  static final int[] DENOMINATIONS = {10,5,2};

  public static void change(int amount) {
    change(amount, new ArrayList<Integer>(), 0);
  }

  private static void change(int remaining, List<Integer> coins, int pos) {
    if (remaining == 0) {
      System.out.println(coins);
      
    } else {
      if (remaining >= DENOMINATIONS[pos]) {
        coins.add(DENOMINATIONS[pos]);
        change(remaining - DENOMINATIONS[pos], coins, pos);
        //coins.remove(coins.size() - 1);
      }
      else if(pos + 1 < DENOMINATIONS.length) {
        change(remaining, coins, pos + 1);
      }
    }
      
     
  }
  
  public static void main(String args[]) {
		int V = 23;
		change(V);
		
	//	System.out.println("Minimum coins required is " + minCoins(V));
	}
}