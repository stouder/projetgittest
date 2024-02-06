package com.example.demo;

import java.util.*;
import java.io.*;
import java.math.*;

class SolutionRugby {

	private static Set<String> combinations = new HashSet<>();
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int score = in.nextInt();

		for (int i = 0; i <= score / 5; i++) {
			for (int j = 0; j <= i; j++) {
				int nbPoint = score - (i * 5) - (j * 2);
				if (nbPoint >= 0 && nbPoint % 3 == 0) {
					System.out.println(i + " " + j + " " + nbPoint / 3);
				}
			}
		}
		
		System.out.println("---------");
		
		calculScoreRecursif(score,0,0,0);
	}



	private static void calculScoreRecursif(int sc, int essai, int transformation, int kick) {

		if (sc == 0) {
			String combination = essai + " " + transformation + " " + kick;
            if (!combinations.contains(combination)) {
                System.out.println(combination);
                combinations.add(combination);
            }
		}

		if (sc >= 7) {
			calculScoreRecursif(sc - 7, essai+1, transformation+1, kick);
		}
		
		if (sc >= 5) {
			calculScoreRecursif(sc - 5, essai + 1, transformation, kick);
		}
		
		if (sc >= 3) {
			calculScoreRecursif(sc - 3, essai, transformation, kick+ 1);
		}
	}
}