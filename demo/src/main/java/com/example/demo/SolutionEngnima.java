package com.example.demo;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse the standard input
 * according to the problem statement.
 **/
class SolutionEngnima {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String operation = in.nextLine();
		int pseudoRandomNumber = in.nextInt();
		List<String> rotors = new ArrayList<>();
		if (in.hasNextLine()) {
			in.nextLine();
		}
		for (int i = 0; i < 3; i++) {
			rotors.add(in.nextLine());
		}
		String message = in.nextLine();
		String res = "";

		if (operation.equals("ENCODE")) {
			res = codeCesear(message, pseudoRandomNumber);
			res = encode(res, rotors);
		} else if (operation.equals("DECODE")) {
			res = decode(message, rotors);
			res = codeCesearReverse(res,pseudoRandomNumber);
		}

		System.out.println(res);
	}


	static String codeCesear(String msg, int num) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < msg.length(); i++) {
			char c = msg.charAt(i);
			char encryptedChar = (char) (c + num + i);
			res.append(encryptedChar);
		}
		return res.toString();
	}

	static String encode(String msg, List<String> rotors) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < msg.length(); i++) {
			char c = msg.charAt(i);
			int index = c - 'A';
			char encodedChar = encodeCharacter(index, rotors);
			res.append(encodedChar);
		}
		return res.toString();
	}

	private static char encodeCharacter(int index, List<String> rotors) {

		index = (index) % 26;

		char encodedChar = rotors.get(0).charAt(index);
		encodedChar = rotors.get(1).charAt(encodedChar - 'A');
		encodedChar = rotors.get(2).charAt(encodedChar - 'A');

		return encodedChar;
	}

	static String decode(String msg, List<String> rotors) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < msg.length(); i++) {
			char encodedChar = decodeCharacter(msg.charAt(i), rotors);
			res.append(encodedChar);
		}
		return res.toString();
	}

	private static char decodeCharacter(char c, List<String> rotors) {
		int pos = 0;
		char encodedChar = c;
		
		for(int i=2;i>=0;i--) {
			pos = rotors.get(i).indexOf(encodedChar);
			encodedChar = (char) ('A' + pos);
		}
		return encodedChar;
	}
	
	static String codeCesearReverse(String msg, int num) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < msg.length(); i++) {
			char c = msg.charAt(i);
			char encryptedChar = adjustNegativeShift(c ,- num - i);
			res.append(encryptedChar);
		}
		return res.toString();
	}
	
	private static char adjustNegativeShift(char c,int shift) {
		return (char) ('A' + Math.floorMod((c - 'A' + shift), 26));
	}
}