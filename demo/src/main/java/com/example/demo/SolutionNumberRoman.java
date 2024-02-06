package com.example.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class SolutionNumberRoman {

	private static final Map<Character, Integer> romanToDecimal = new HashMap<>();
	static {
		romanToDecimal.put('I', 1);
		romanToDecimal.put('V', 5);
		romanToDecimal.put('X', 10);
		romanToDecimal.put('L', 50);
		romanToDecimal.put('C', 100);
		romanToDecimal.put('D', 500);
		romanToDecimal.put('M', 1000);
	}

	private static final Map<Integer, String> decimalToRoman = new TreeMap<>(Collections.reverseOrder());
	static {
		decimalToRoman.put(1000, "M");
		decimalToRoman.put(900, "CM");
		decimalToRoman.put(500, "D");
		decimalToRoman.put(400, "CD");
		decimalToRoman.put(100, "C");
		decimalToRoman.put(90, "XC");
		decimalToRoman.put(50, "L");
		decimalToRoman.put(40, "XL");
		decimalToRoman.put(10, "X");
		decimalToRoman.put(9, "IX");
		decimalToRoman.put(5, "V");
		decimalToRoman.put(4, "IV");
		decimalToRoman.put(1, "I");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String S2016 = decimalToRoman(2016); // MMXVI
		String S999 = decimalToRoman(999); // CMXCIX

		int result = romanToDecimal(in.next()) + romanToDecimal(in.next());

		System.out.println(decimalToRoman(result));
	}

	private static int romanToDecimal(String s) {
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			int value = romanToDecimal.get(s.charAt(i));
			if (i < s.length() - 1 && value < romanToDecimal.get(s.charAt(i + 1))) {
				res -= value;
			} else {
				res += value;
			}
		}
		return res;
	}

	private static String decimalToRoman(int number) {
		StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, String> entry : decimalToRoman.entrySet()) {
            int value = entry.getKey();
            String roman = entry.getValue();

            while (number >= value) {
                sb.append(roman);
                number -= value;
            }
        }

        return sb.toString();
	}

}
