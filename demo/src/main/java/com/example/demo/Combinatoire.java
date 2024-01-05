package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Combinatoire {


	
	private static List<List<Integer>> combinatoireRecursif(List<Integer> arr, int index,List<List<Integer>> result) {
		
		if(index == arr.size() - 1) {
			result.add(new ArrayList<>(arr));
		} else {
			for(int i=index;i<arr.size();i++) {
				Collections.swap(arr, index, i);
				combinatoireRecursif(arr,index+1,result);
				Collections.swap(arr,i,index);
			}	
		}
		
		return result;
	}
	
	public static void afficherCombinaisons(List<List<Integer>> combinaisons) {
        for (List<Integer> combinaison : combinaisons) {
            System.out.println(combinaison);
        }
    }
	
	private static boolean isValidPath(List stores, List path) {
		int startIndex = stores.indexOf(path.get(0)); 
		int endIndex = stores.indexOf(path.get(1)); 
		return startIndex != -1 && endIndex != -1 && startIndex < endIndex; 
	} 
	
	public static void main(String[] args) {
		int k = 0;
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		List<List<Integer>> pathExistant = new ArrayList<List<Integer>>();
		pathExistant.add(List.of(0, 1)); 
		pathExistant.add(List.of(1, 0)); 
		pathExistant.add(List.of(1, 2)); 
		pathExistant.add(List.of(3, 1)); 
		pathExistant.add(List.of(1, 3)); 
		pathExistant.add(List.of(2, 1)); 
		pathExistant.add(List.of(2, 3)); 
		pathExistant.add(List.of(3, 2)); 
		
		List<Integer> stores = Arrays.asList(0, 1, 2,3,3);    
        List<List<Integer>> combinaisons = combinatoireRecursif(stores, k,result);
        
       // for(List combinaison:combinaisons) {
        	 for(List path:pathExistant) {
             	if(isValidPath(stores,path)) {
             		System.out.println("path : " + path.get(0) + " - " + path.get(1));
             	}
             }
       // }
       
        
       // afficherCombinaisons(combinaisons);
		
				
	} 
}
