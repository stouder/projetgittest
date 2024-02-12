package com.example.demo;

import java.util.Scanner;

public class DuoDigit {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Entrez un nombre : ");
		int n = scanner.nextInt();

		boolean isDuoDigit = Integer.toString(n).chars().distinct().count() <= 2;
		String val = isDuoDigit ? "y" : "n";

		System.out.println("DuoDigit : " + val);

		scanner.close();
	}
}
