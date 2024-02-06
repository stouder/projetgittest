package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParcoursIntermediaire2 {

	public static void main(String[] args) {
		Integer firstPoint = 1;
		Integer endPoint = 6;

		Map<Integer, List<Integer>> routes = new HashMap<>();
		routes.put(1, List.of(3, 2, 5, 6));
		routes.put(2, List.of(4, 5));
		routes.put(3, List.of(4));
		routes.put(4, List.of(5));
		routes.put(5, List.of(6));

		List<List<Integer>> chemins = findPaths(routes, firstPoint, endPoint);

		if (chemins != null && !chemins.isEmpty()) {
			System.out.println("Chemins trouvés : ");
			for (List<Integer> chemin : chemins) {
				System.out.println(chemin);
			}
		} else {
			System.out.println("Aucun chemin trouvé entre " + firstPoint + " et " + endPoint);
		}
	}

	static List<List<Integer>> findPaths(Map<Integer, List<Integer>> routes, int depart, int endPoint) {
		List<List<Integer>> paths = new ArrayList<>();
		List<Integer> currentPath = new ArrayList<>();
		findPathsRecursive(routes, depart, endPoint, currentPath, paths);

		return paths;
	}

	static void findPathsRecursive(Map<Integer, List<Integer>> routes, int currentPoint, int endPoint,
			List<Integer> currentPath, List<List<Integer>> paths) {
		
		currentPath.add(currentPoint);

		if (currentPoint == endPoint) {
			paths.add(new ArrayList<>(currentPath));
		} else if (routes.containsKey(currentPoint)) {
			for (int testPoint : routes.get(currentPoint)) {
				findPathsRecursive(routes, testPoint, endPoint, currentPath, paths);
			}
		}

		currentPath.remove(currentPath.size() - 1);
	}
}
