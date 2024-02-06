package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

class SolutionNainGeants {

	static Map<Integer, List<Integer>> map = new HashMap<>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(); // the number of relationships of influence

		for (int i = 0; i < n; i++) {
			map.computeIfAbsent(in.nextInt(), key -> new ArrayList<>()).add(in.nextInt());
		}

		int max = 0;
		for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
			max = Math.max(max, hauteurArbreRecursif(e.getKey()));
		}

		System.out.println(max);
	}

	static int hauteurArbreRecursif(Integer cle) {
	    //  'map' est une variable de classe représentant une carte où les clés sont des Integer et les valeurs sont des listes d'Integers.
	    List<Integer> liste = map.get(cle);
	    
	    // Vérifiez si la liste associée à la clé n'est pas nulle et n'est pas vide.
	    if (liste != null && !liste.isEmpty()) {
	        // Initialise une variable pour stocker la hauteur maximale.
	        int max = 0;      
	        // Parcourez la liste d'entiers.
	        for (Integer i : liste) {
	            // Appelle récursivement la méthode pour calculer la hauteur du sous-arbre enraciné à 'i'.
	            // Ajoutez 1 à la hauteur, car chaque niveau dans l'arbre contribue à la hauteur totale.
	            max = Math.max(max, hauteurArbreRecursif(i) + 1);
	        }
	        
	        // Retourne la hauteur maximale des sous-arbres enracinés au nœud actuel.
	        return max;
	    }
	    
	    // Si la liste est nulle ou vide, retourne 1 (indiquant la hauteur du nœud actuel).
	    return 1;
	}
	
	static int hauteurArbreInteratif(Integer racine) {
		 if (racine == null || !map.containsKey(racine)) {
	            return 0; // La racine est nulle ou absente dans la carte, la hauteur est 0.
	        }

	        Map<Integer, Integer> heights = new HashMap<>();
	        Stack<Integer> stack = new Stack<>();

	        stack.push(racine);
	        heights.put(racine, 1);

	        while (!stack.isEmpty()) {
	            Integer current = stack.pop();
	            int currentHeight = heights.get(current);

	            List<Integer> children = map.get(current);
	            if (children != null) {
	                for (Integer child : children) {
	                    stack.push(child);
	                    heights.put(child, currentHeight + 1);
	                }
	            }
	        }

	        // Recherche de la hauteur maximale dans la carte des hauteurs.
	        return Collections.max(heights.values());
	}
}
