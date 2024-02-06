package com.example.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Next {

	// Fonction pour trouver le prochain nombre qui remplit la condition
	// nous incrementons jusqu'au moment où la condition est rempie
	private static int next(int number) {

		Set<Character> digitSet = new HashSet<>();

		char[] digits = Integer.toString(number).toCharArray();
		for (char c : digits) {
			digitSet.add(c);
		}

		do {
			if (number == Integer.MAX_VALUE) {
				number = -1;
				break;
			}

			number++;
		} while (testListDigit(Integer.toString(number), digitSet));

		return number;
	}

	public static int next2(int n) {
		int res = n;

		List<Integer> numbers = new ArrayList<>();

		while (n != 0) {
			numbers.add(n % 10);
			n /= 10;
		}

		while (true) {
			res++;

			if (res == Integer.MAX_VALUE) {
				res = -1;
				break;
			}

			String tempString = Integer.toString(res);
			Optional<Integer> resultat = numbers.stream().filter(d -> tempString.contains(String.valueOf(d))).findAny();
			
			if (resultat.isEmpty()) {
				break;
			}
		}

		return res;
	}
	
	// la fonction test si l'un des chiffres de la liste est present dans le nombre
	// nous travaillons avec des String et des Char
	private static boolean testListDigit(String nextNumberStr, Set<Character> digitSet) {

		for (char digit : digitSet) {
			if (nextNumberStr.indexOf(digit) >= 0) {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		int num = 654321;
		int res = next(num);

		if (res != 0) {
			System.out.println("Resultat : " + res);
		} else {
			System.out.println("Pas de resultat possible pour ce int");
		}

	}

}
