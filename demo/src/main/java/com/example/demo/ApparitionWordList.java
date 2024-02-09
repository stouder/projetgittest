package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApparitionWordList {

	public static void main(String[] args) {

		List<String> words = new ArrayList<>();

		words.add("Fabien");
		words.add("toto");
		words.add("Stouder");
		words.add("toto");
		words.add("AnneLaure");
		words.add("Fabien");
		words.add("toto");

		Collections.sort(words);

		Map<String, Integer> wordCounts = new HashMap<>();
		for (String word : words) {
			wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
		}

		int[] counts = new int[wordCounts.size()];

		int index = 0;
		for (int count : wordCounts.values()) {
			counts[index++] = count;
		}

		Arrays.stream(counts).forEach(System.out::println);

		wordCounts.forEach((key, value) -> System.out.println(key + ": " + value));

		int num = 222;

	}
}
