package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.Pair;



class SolutionLabyrinthe {

    public static void main(String[] args) {
    	 Scanner scanner = new Scanner(System.in);

         // Lecture des dimensions du labyrinthe
         int W = scanner.nextInt();
         int H = scanner.nextInt();

         // Lecture de la position de départ
         int startX = scanner.nextInt();
         int startY = scanner.nextInt();

         // Initialisation du labyrinthe
         char[][] labyrinth = new char[W][H];

         // Lecture du labyrinthe
         for (int i = 0; i < H; i++) {
             labyrinth[i] = scanner.next().toCharArray();
         }

         // Recherche des sorties possibles
         List<Pair<Integer, Integer>> outputs = findOutput(labyrinth, startX, startY);

         // Affichage du résultat
         System.out.println("Nombre de sortie " + outputs.size());
         for (Pair output : outputs) {
             System.out.println(output.getRight() + " " + output.getLeft());
         }

         scanner.close();
    }
    
    private static List<Pair<Integer, Integer>> findOutput(char[][] labyrinth,int startX,int startY) {
    	List<Pair<Integer, Integer>> outputs = new ArrayList<>();
        boolean[][] visited = new boolean[labyrinth.length][labyrinth[0].length];  
    	findOutputHelper(labyrinth,startX,startY,outputs,visited);
    	
    	return outputs;
    }
    
    private static void findOutputHelper(char[][] labyrinth,int posX,int posY,List<Pair<Integer, Integer>> outputs,boolean[][] visited) {
    	    	
    	if(!checkMouve(labyrinth,posX,posY)|| checkVisited(visited,posX,posY)) {
    		return ;
    	}

    	visited[posX][posY] = true;
    	
    	if(checkOutput(labyrinth,posX,posY)) {
    		outputs.add(Pair.of(posX,posY));
    	}
    	
    	findOutputHelper(labyrinth, posX - 1, posY,outputs,visited);
    	findOutputHelper(labyrinth, posX + 1, posY, outputs,visited);
    	findOutputHelper(labyrinth, posX, posY - 1, outputs,visited);
    	findOutputHelper(labyrinth, posX, posY + 1,outputs, visited);
    	
    }
    
    private static boolean checkVisited(boolean[][] visited, int posX, int posY) {
        return visited[posX][posY];
    }
    
    private static boolean checkMouve(char[][] labyrinth, int posX, int posY) {
        return posX >= 0 && posX < labyrinth.length && posY >= 0 && posY < labyrinth[0].length;
    }
    
    private static boolean checkOutput(char[][] labyrinth, int posX, int posY) {
       return labyrinth[posX][posY] == '.' && (posX == labyrinth.length - 1 || posY == labyrinth[0].length - 1);
    }
}
