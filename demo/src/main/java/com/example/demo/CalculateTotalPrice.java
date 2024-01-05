package com.example.demo;
import java.util.Arrays;

public class CalculateTotalPrice {
 
	public static void main(String[] args) {
		int[] prices= {99,30,2,80,10};
		System.out.println(calculateTotalPrice1(prices, 20));
 
	}
 
public static int calculateTotalPrice1(int[] prices,int discount) {
		System.out.println(Math.ceil(100*0.8));
		double remise = discount/(double)100;
		System.out.println(remise);
		int total1=0;
		int len=prices.length;
		Arrays.sort(prices);
		for(int i=0;i<len-1;i++) {
			total1=total1+prices[i];
		}
		int price = (int)Math.floor((double)prices[len-1]*(1-(double)discount/(100.0)));
		
		return   total1+ price;
 
	}
}