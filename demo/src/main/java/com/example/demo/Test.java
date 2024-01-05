package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {
		System.out.println(" Tableau  ");
		Integer[] tab = new Integer[7];
		tab[0] = 7;
		tab[1] = 3;
		tab[2] = 6;
		tab[3] = 4;
		tab[4] = 3;
		tab[5] = 4;
		tab[6] = 9;

		for (int i = 0; i < tab.length; i++) {
			System.out.print(tab[i]);
		}

		System.out.println(" ----- ");

		List<Integer> liste = new ArrayList<>();
		liste = Arrays.asList(tab);

		List<Integer> noDuplicates = liste.stream().distinct().collect(Collectors.toList());

		Integer[] array = new Integer[noDuplicates.size()];
		noDuplicates.toArray(array);

		System.out.println(" Tableau Integer sans doublon ");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}

		System.out.println(" ----- ");

		int[] tabInt = new int[7];
		tabInt[0] = 7;
		tabInt[1] = 3;
		tabInt[2] = 6;
		tabInt[3] = 4;
		tabInt[4] = 3;
		tabInt[5] = 4;
		tabInt[6] = 9;

		Set<Integer> valeur = new HashSet<>();
		List<Integer> listNoDuplicate = new ArrayList<>();
		
		for (int i = 0; i < tabInt.length; i++) {
			if (!valeur.contains(tabInt[i])) {
				valeur.add(tabInt[i]);
				listNoDuplicate.add(tabInt[i]);
			}
		}

		int[] arrayWithoutDuplicate = new int[listNoDuplicate.size()];
		int index = 0;
		
		for(Integer el:listNoDuplicate) {
			arrayWithoutDuplicate[index]=el;
			index++;
		}
		
		System.out.println(" Tableau int sans doublon ");
		
		for (int i = 0; i < arrayWithoutDuplicate.length; i++) {
			System.out.print(arrayWithoutDuplicate[i]);
		}

		System.out.println(" ----- ");

		int[] tabHash = new int[7];
		tabHash[0] = 7;
		tabHash[1] = 3;
		tabHash[2] = 6;
		tabHash[3] = 4;
		tabHash[4] = 3;
		tabHash[5] = 4;
		tabHash[6] = 9;

	}

}
