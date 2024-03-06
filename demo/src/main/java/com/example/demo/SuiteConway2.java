package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SuiteConway2 {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int R = in.nextInt();
		int L = in.nextInt();

		List<List<Integer>> res = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		list.add(R);
		res.add(list);

		for (int i = 0; i < L - 1; i++) {
			list = new ArrayList<>();

			int temp = res.get(i).get(0);
			int count = 1;

			for (int j = 1; j < res.get(i).size(); j++) {
				if (res.get(i).get(j) == temp) {
					count++;
				} else {
					list.add(count);
					list.add(temp);
					temp = res.get(i).get(j);
					count = 1;
				}
			}

			list.add(count);
			list.add(temp);
			res.add(list);
		}

		System.out.println(res.get(L - 1).stream().map(Object::toString).collect(Collectors.joining(" ")));

	}

}
