package com.example.demo;

import java.util.Arrays;

public class RiskBattleProbabilityCalculator {
	public static void main(String[] args) {
		int numSides = 6; // Nombre de côtés du dé
		int attaques = 3; // Nombre d'attaques
		int defenses = 1; // Nombre de défenses

		int[] currentAttacks = new int[attaques];
		int[] currentDefenses = new int[defenses];

		int[] winsAttack = new int[1];
		int[] winsDefense = new int[1];

		simulateAttacks(numSides, attaques, defenses, currentAttacks, currentDefenses, 0, 0, winsAttack, winsDefense);

		System.out.println("Nombre de victoires de l'attaque: " + winsAttack[0]);
		System.out.println("Nombre de victoires de la défense: " + winsDefense[0]);

		System.out.print((double) winsDefense[0] / (winsAttack[0] + winsDefense[0]));
		System.out.print((double) winsAttack[0] / (winsAttack[0] + winsDefense[0]));
	}

	public static void simulateAttacks(int numSides, int attaques, int defenses, int[] currentAttacks,
			int[] currentDefenses, int attaqueIndex, int defenseIndex, int[] winsAttack, int[] winsDefense) {
		if ((attaques > defenses && attaqueIndex == attaques) || (defenses > attaques && defenseIndex == defenses)) {
			int attaqueMax = Arrays.stream(currentAttacks).max().getAsInt();
			int defenseMax = Arrays.stream(currentDefenses).max().getAsInt();

			if (attaqueMax > defenseMax) {
				winsAttack[0]++;
			} else {
				winsDefense[0]++;
			}
			return;
		}

		for (int i = 1; i <= numSides; i++) {
			if (attaqueIndex < attaques) {
				currentAttacks[attaqueIndex] = i;
			}
			for (int j = 1; j <= numSides; j++) {
				if (defenseIndex < defenses) {
					currentDefenses[defenseIndex] = j;
				}

				simulateAttacks(numSides, attaques, defenses, currentAttacks, currentDefenses, attaqueIndex + 1,
						defenseIndex + 1, winsAttack, winsDefense);
			}
		}
	}

}
