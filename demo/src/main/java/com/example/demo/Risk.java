package com.example.demo;

import java.util.Scanner;

public class Risk {

	public static double calculateVictoryProbability() {
		int victoireAttaque = 0;
		int total = 0;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (i > j) {
					victoireAttaque++;

				}
				total++;
			}
		}

		double p = 0.0;

		if (total != 0) {
			p = (double) victoireAttaque / total;
		}

		return p;
	}

	public static boolean victoire(int attack, int defense) {
		return attack == 0 || defense == 0;
	}

	public static double calculateOverallVictoryProbability(int attack, int defense) {
		if (attack == 0) {
			return 0.0;
		}
		if (defense == 0) {
			return 100.0;
		}

		double victoryProbability = calculateVictoryProbability();
		double overallProbability = 0.0;

		for (int i = 1; i <= attack; i++) {
			for (int j = 1; j <= defense; j++) {
				if (i > j) {
					overallProbability += victoryProbability
							* calculateOverallVictoryProbability(attack - 1, defense - 1);
				} else {
					overallProbability += (1 - victoryProbability)
							* calculateOverallVictoryProbability(attack, defense - 1);
				}
			}
		}

		return overallProbability / (attack * defense);
	}

	public static double calculateVictoryProbability(int attackDice, int defenseDice) {
		int victoryAttack = 0;
		int total = 0;

		for (int i = 1; i <= attackDice; i++) {
			for (int j = 1; j <= defenseDice; j++) {
				if (i > j || (i == j && attackDice > 1)) {
					victoryAttack++;
				}
				total++;
			}
		}

		double p = 0.0;

		if (total != 0) {
			p = (double) victoryAttack / total;
		}

		return p;
	}

	public static double calculateVictoryProbability3vs1() {
		int attackWins = 0;
		int totalOutcomes = 0;

		// Boucle sur tous les résultats possibles du dé d'attaque 1
		for (int attackRoll1 = 1; attackRoll1 <= 6; attackRoll1++) {
			// Boucle sur tous les résultats possibles du dé d'attaque 2
			for (int attackRoll2 = 1; attackRoll2 <= 6; attackRoll2++) {
				// Boucle sur tous les résultats possibles du dé d'attaque 3
				for (int attackRoll3 = 1; attackRoll3 <= 6; attackRoll3++) {
					// Boucle sur tous les résultats possibles du dé de défense
					for (int defenseRoll = 1; defenseRoll <= 6; defenseRoll++) {
						totalOutcomes++;

						// Vérifier si l'attaquant gagne
						if (attackRoll1 > defenseRoll || attackRoll2 > defenseRoll || attackRoll3 > defenseRoll) {
							attackWins++;
						}
					}
				}
			}
		}

		// Calculer la probabilité de victoire de l'attaquant
		double victoryProbability = (double) attackWins / totalOutcomes;

		return victoryProbability;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Entrée
		System.out.println("Veuillez entrer le nombre d'armées d'attaque :");
		int armeesattaque = scanner.nextInt();

		System.out.println("Veuillez entrer le nombre d'armées de défense :");
		int armeesdefense = scanner.nextInt();

		double v = calculateOverallVictoryProbability(armeesattaque, armeesdefense);

		System.out.printf("%.2f%%%n", v);

		// Calcul des probabilités de victoire
		double probability = calculateVictoryProbability3vs1();
		System.out.printf("%.2f%%%n", probability);

	}

}
