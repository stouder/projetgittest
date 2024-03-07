package com.example.demo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PlusRapide {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();

		List<String> timeList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String t = in.next();
			timeList.add(t);
		}

		Collections.sort(timeList, (t1, t2) -> {
			LocalTime time1 = LocalTime.parse(t1, DateTimeFormatter.ofPattern("HH:mm:ss"));
			LocalTime time2 = LocalTime.parse(t2, DateTimeFormatter.ofPattern("HH:mm:ss"));
			return time1.compareTo(time2);
		});

		System.out.println(timeList.get(0));

	}

}
