package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Scrable {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		if (in.hasNextLine()) {
			in.nextLine();
		}

		List<String> dictionary = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			dictionary.add(in.nextLine());
		}

		String sevenLetters = in.nextLine();
		List<Character> letters = new ArrayList<>();

		for (int i = 0; i < sevenLetters.length(); i++) {
			letters.add(sevenLetters.charAt(i));
		}

		int bestScore = 0;
		String bestWord = null;
		
		for (String word : dictionary) {
			boolean found = checkWord(letters, word);

			if (found) {
				int currentScore = calcScore(word);
				
				if(currentScore > bestScore) {
					bestScore = currentScore;
					bestWord = word;
				}
			}
		}
		
		if(bestScore != 0) {
			System.out.println("Meilleur Mot " + bestWord + " Score : " + bestScore);
		}else {
			System.out.println("Pas de mot trouvé");
		}
		

	}

	private static int calcScore(final String word) {
		int res = 0;
		for (Character c : word.toCharArray()) {
			res += toPoints(c);
		}
		return res;
	}

	private static boolean checkWord(List<Character> listeDeCaracteres, String word) {
		Map<Character, Integer> letters = new HashMap<>();

		for (char c : listeDeCaracteres) {
			letters.put(c, letters.getOrDefault(c, 0) + 1);
		}

		for (char c : word.toCharArray()) {
			if (!letters.containsKey(c) || letters.get(c) == 0) {
				return false;
			}
			letters.put(c, letters.get(c) - 1);
		}

		return true;
	}

	private static int toPoints(Character c) {
		int points = 1;
		if (c == 'd' || c == 'g') {
			points = 2;
		} else if (c == 'b' || c == 'c' || c == 'm' || c == 'p') {
			points = 3;
		} else if (c == 'f' || c == 'h' || c == 'v' || c == 'w' || c == 'y') {
			points = 4;
		} else if (c == 'k') {
			points = 5;
		} else if (c == 'j' || c == 'x') {
			points = 8;
		} else if (c == 'q' || c == 'z') {
			points = 10;
		}
		return points;
	}

}
