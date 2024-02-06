package com.example.demo;

import java.util.Scanner;

public class SolutionPlayer {
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt(); // Nombre de lignes
        int columns = in.nextInt(); // Nombre de colonnes

        // Stratégie basée sur le théorème de Sprague-Grundy
        while (true) {
            int[] nimbers = new int[rows];

            // Calcul des nimbers pour chaque ligne
            for (int i = 0; i < rows; i++) {
                int xPlayer = in.nextInt(); // Position du pion du joueur
                int xBoss = in.nextInt(); // Position du pion de l'adversaire

                nimbers[i] = calculateNimber(xPlayer, xBoss, columns);
            }

            // Trouver le coup optimal
            int rowToMove = findOptimalMove(nimbers);

            // Déplacement vers la position optimale
            int newX = nimbers[rowToMove] ^ columns;
            System.out.println(rowToMove + " " + newX);
        }
    }

    // Calcul du nimber pour une ligne donnée
    private static int calculateNimber(int xPlayer, int xBoss, int columns) {
        return (xBoss - xPlayer - 1) ^ columns;
    }

    // Recherche du coup optimal basé sur les nimbers
    private static int findOptimalMove(int[] nimbers) {
        int optimalRow = 0;
        int nimSum = 0;

        for (int nimber : nimbers) {
            nimSum ^= nimber;
        }

        // Recherche d'une ligne avec un nimber non nul
        for (int i = 0; i < nimbers.length; i++) {
            if ((nimbers[i] ^ nimSum) < nimbers[i]) {
                optimalRow = i;
                break;
            }
        }

        return optimalRow;
    }
}

