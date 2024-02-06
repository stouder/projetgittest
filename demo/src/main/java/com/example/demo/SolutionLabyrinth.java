package com.example.demo;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SolutionLabyrinth {

	private static final int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int startX = scanner.nextInt();
		int startY = scanner.nextInt();
		int rabbitX = scanner.nextInt();
		int rabbitY = scanner.nextInt();

		int width = scanner.nextInt();
		int height = scanner.nextInt();

		String[] labyrinth = new String[height];
		for (int i = 0; i < height; i++) {
			labyrinth[i] = scanner.next();
		}

		int[] start = { startY, startX };
		int[] rabbit = { rabbitY, rabbitX };

		int[] result = findPath(labyrinth, start, rabbit);
		System.out.println(result[0] + " " + result[1]);

		scanner.close();
	}

	private static int[] findPath(String[] labyrinth, int[] start, int[] rabbit) {

		Set<String> visited = new HashSet<>();
		int[] result = { -1, -1 };
		
		if (findPathHelper(labyrinth, start, rabbit, visited)) {
			result[0] = visited.size() - 1;
			reverseArray(labyrinth);
			visited.clear();
			findPathHelper(labyrinth, rabbit, start, visited);
			result[1] = visited.size() - 1;
		}

		return result;
	}

	private static boolean findPathHelper(String[] labyrinth, int[] current, int[] target, Set<String> visited) {
		int x = current[0];
		int y = current[1];

		String currentString = x + " " + y;
		if (current[0] == target[0] && current[1] == target[1]) {
			return true;
		}

		if (!visited.contains(currentString)) {
			visited.add(currentString);

			for (int[] direction : directions) {
				int nx = x + direction[0];
				int ny = y + direction[1];

				if (isValid(nx, ny, labyrinth) && labyrinth[nx].charAt(ny) != 'x') {
					int[] neighbor = { nx, ny };
					if (findPathHelper(labyrinth, neighbor, target, visited)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	// on reste dans le labyrinthe
	private static boolean isValid(int x, int y, String[] labyrinth) {
		return x >= 0 && x < labyrinth.length && y >= 0 && y < labyrinth[0].length();
	}

	private static void reverseArray(String[] array) {
		int i = 0; 
		int	j = array.length - 1;
		while (i < j) {
			String temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}
	}
}
