package com.example.demo;

import java.util.List;
import java.util.ArrayList;

public class Codingame {
	
	public static void main(String[] args) {
		List stores = new ArrayList<>(); 
		
		stores.add(0); 
		stores.add(1); 
		stores.add(2);
		stores.add(3); 
		stores.add(3);
		
		List predefinedPaths = new ArrayList<>(); 
		
		predefinedPaths.add(List.of(0, 1)); 
		predefinedPaths.add(List.of(1, 2)); 
		predefinedPaths.add(List.of(2, 3)); 
		
		int result = countPaths(stores, predefinedPaths); 
				
	} 
	
	public static int countPaths(List stores, List<List<Integer>> predefinedPaths) { 
		int totalPaths = 0; 
		
		for (List path : predefinedPaths) { 
			if (!isValidPath(stores, path)) { 
				totalPaths = 0;
				break; 
			} 
			
		} 
		
		return totalPaths; 
	}
	
	private static boolean isValidPath(List stores, List path) {
		int startIndex = stores.indexOf(path.get(0)); 
		int endIndex = stores.indexOf(path.get(1)); 
		return startIndex != -1 && endIndex != -1 && startIndex < endIndex; 
	} 
}
