package com.example.demo;

import java.util.Scanner;

public class SolutionFolder {

	 public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Lire l'ordre de pliage
	        String order = scanner.nextLine();

	        // Lire le côté spécifié
	        char side = scanner.nextLine().charAt(0);

	        // Calculer et afficher le résultat
	        int result = countLayers(order, side);
	        System.out.println(result);
	    }

	    public static int countLayers(String order, char side) {
	        int layers = 1;  // La couche initiale est toujours visible

	        for (char fold : order.toCharArray()) {
	            layers = updateLayers(layers, fold, side);
	        }

	        return layers;
	    }

	    public static int updateLayers(int currentLayers, char fold, char side) {
	        if (side == 'R') {
	        	if (fold == 'U' || fold == 'D' ) {
	                return currentLayers * 2;
	            } else {
	                return currentLayers * 2 - 1;
	            }
	        } else if (side == 'L') {
	        	if (fold == 'U' || fold == 'D' ) {
	                return currentLayers * 2 ;
	            } else {
	                return currentLayers * 2 -1;
	            }
	        } else if (side == 'U') {
	        	if (fold == 'R' || fold == 'L' ) {
	                return currentLayers * 2;
	            } else {
	                return currentLayers * 2 - 1;
	            }
	        } else if (side == 'D') {
	        	if (fold == 'R' || fold == 'L' ) {
	                return currentLayers * 2;
	            } else if (fold == 'U') {
	                return currentLayers * 2;
	            } else {
	                return currentLayers * 2 - 1;
	            }
	        }

	        return currentLayers;
	    }
}

