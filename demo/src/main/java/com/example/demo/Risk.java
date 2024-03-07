package com.example.demo;

import java.util.Scanner;

public class Risk {

	public static double calculerProbabiliteVictoire(int armees_attaque, int armees_defense) {
		if (armees_attaque < 1 || armees_defense < 1) {
			return 0.0;
		}

		int des_attaque = Math.min(3, armees_attaque - 1);
		int des_defense = Math.min(2, armees_defense);

		double[][] resultats = new double[des_attaque][des_defense];

		for (int i = 0; i < des_attaque; i++) {
			for (int j = 0; j < des_defense; j++) {
				if (i == 0) {
					resultats[i][j] = (6 - j) / 6.0;
				} else if (j == 0) {
					resultats[i][j] = (6 - i) / 6.0;
				} else {
					resultats[i][j] = (i - 1) / (double) (i + j - 1);
				}
			}
		}

		double victoires_attaquant = 0;
		double total_resultats = 0;

		for (int i = 0; i < des_attaque; i++) {
			for (int j = 0; j < des_defense; j++) {
				double probabilite = resultats[i][j];
				victoires_attaquant += probabilite;
				total_resultats += 1;
			}
		}

		return Math.round((victoires_attaquant / total_resultats) * 10000.0) / 100.0;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Entrée
		System.out.println("Veuillez entrer le nombre d'armées d'attaque :");
		int armees_attaque = scanner.nextInt();

		System.out.println("Veuillez entrer le nombre d'armées de défense :");
		int armees_defense = scanner.nextInt();

		// Calcul des probabilités de victoire
		double probabilite_victoire = calculerProbabiliteVictoire(armees_attaque, armees_defense);

		// Sortie
		System.out.println("Les chances de victoire de l'attaquant sont de : " + probabilite_victoire + "%");
	}

}
