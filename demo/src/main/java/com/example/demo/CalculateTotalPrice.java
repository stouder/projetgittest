package com.example.demo;
import java.util.Arrays;

public class CalculateTotalPrice {
 
	public static void main(String[] args) {
		int[] prices= {129,30,2,80,10};
		System.out.println(calculateTotalPrice1(prices, 20));
 
	}
 
public static int calculateTotalPrice1(int[] prices,int discount) {

		int total = 0;
		int len = prices.length;
		Arrays.sort(prices);
		
		for(int i=0;i<len-1;i++) {
			total = total + prices[i];
		}

		int  price = (int)(prices[len-1]*(1 - discount/(100f)));
		
		return total + price;
 
	}
}