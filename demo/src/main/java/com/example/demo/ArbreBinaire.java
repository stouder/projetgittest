package com.example.demo;

import java.util.Stack;

class Node {
    int value;
    Node left, right;

    public Node(int item) {
        value = item;
        left = right = null;
    }
}

public class ArbreBinaire {

    Node root;

    public ArbreBinaire() {
        root = null;
    }

    // Méthode itérative pour trouver une valeur dans un arbre binaire (parcours en profondeur)
    public boolean rechercheValeurStack(int valeur) {
        if (root == null) {
            return false;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node noeud = stack.pop();

            // Vérifier si la valeur est égale à celle du nœud actuel
            if (noeud.value == valeur) {
                return true;
            }

            // Empiler les enfants du nœud actuel (droit puis gauche pour respecter l'ordre inverse dans la pile)
            if (noeud.right != null) {
                stack.push(noeud.right);
            }
            if (noeud.left != null) {
                stack.push(noeud.left);
            }
        }

        return false;
    }

    public boolean rechercheIterative(int v) {
    	Node current = this.root;
    	
    	while(current != null) {
    		if(current.value == v) {
    			return true;
    		}
    		
    		if(v > current.value) {
    			current = current.left;
    		}else {
    			current = current.right;
    		} 		
    	}
    	
    	return false;
    	
    }
    
    public static void main(String[] args) {
        ArbreBinaire arbre = new ArbreBinaire();

        // Ajouter des nœuds à l'arbre (exemple)
        arbre.root = new Node(2);
        arbre.root.left = new Node(1);
        arbre.root.right = new Node(3);
        arbre.root.left.left = new Node(4);
        arbre.root.left.right = new Node(5);

        int valeurRecherchee = 5;

        // Rechercher la valeur dans l'arbre
        if (arbre.rechercheValeurStack(valeurRecherchee)) {
        	System.out.println("La fonction rechercheValeurStack a permis de trouver la valeur " + valeurRecherchee + " dans l'arbre.");
        } else {
        	System.out.println("La fonction rechercheValeurStack n'a pas permis de trouver la valeur " + valeurRecherchee + " dans l'arbre.");
        }
        
        if (arbre.rechercheIterative(valeurRecherchee)) {
            System.out.println("La fonction rechercheIterative a permis de trouver la valeur " + valeurRecherchee + " dans l'arbre.");
        } else {
        	System.out.println("La fonction rechercheIterative n'a pas permis de trouver la valeur " + valeurRecherchee + " dans l'arbre.");
        }
        
    }
}

