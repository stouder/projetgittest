package com.example.demo;
import static java.lang.Math.abs;
import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiversExo {

	static boolean exists(int[] ints, int k) {
		int index = Arrays.binarySearch(ints, k);

		if (index >= 0) {
			return true;
		} else {
			return false;
		}

	}

	public static int calculateTotalPrice2(int[] prices, int discount) {
		int total1 = 0;
		int len = prices.length;
		Arrays.sort(prices);
		// for(int i=0;i<len-1;i++) {
		// total1=total1+prices[i];
		// }
		total1 = Arrays.stream(prices).limit(len - 1).reduce((a, b) -> a + b).getAsInt();
		System.out.println("Sum " + total1);

		// reduce(0, Integer::sum);
		return (int) ((total1 + (float) ((prices[len - 1]) - ((prices[len - 1]) * discount / 100f))));

	}

	public static int calculateTotalPrice(int[] prices, int discount) {
		int total1 = 0;
		int len = prices.length;
		Arrays.sort(prices);
		for (int i = 0; i < len - 1; i++) {
			total1 = total1 + prices[i];
		}
		return (int) ((total1 + (float) ((prices[len - 1]) - ((prices[len - 1]) * discount / 100f))));

	}

	public static String isDuoDigit(int number) {
		boolean isDuoDigit = Integer.toString(number).replace("-", "").chars().distinct().count() <= 2;
		return isDuoDigit ? "y" : "n";
	}

	public static String computeCheckDigit(int identificationNumber) {

		String number = String.valueOf(identificationNumber);
		char[] digits = number.toCharArray();

		int res = 0;
		int odd = 0;
		for (int i = 0; i < digits.length; i++) {
			if (i % 2 == 0) {
				System.out.println("res" + i + " " + res);
				System.out.println("i" + i + " " + digits[i]);
				res = res + Character.getNumericValue(digits[i]);
				System.out.println("res" + i + " " + res);
			}

			if (i % 2 == 1) {
				System.out.println("i" + i + " " + digits[i]);
				odd = odd + Character.getNumericValue(digits[i]);
				System.out.println("odd" + i + " " + odd);
			}

		}

		res = 3 * res + odd;

		System.out.println("res " + res);

		String resutlat = String.valueOf(res);
		char[] resutlats = resutlat.toCharArray();
		int rest = 0;
		if (Character.getNumericValue(resutlats[resutlats.length - 1]) != 0) {
			rest = 10 - Character.getNumericValue(resutlats[resutlats.length - 1]);
		}

		return String.valueOf(rest);
	}

	public static String returnResultFizzBuzz(int pos, int end) {
		String result;
		if (pos <= end) {

			if (pos % 15 == 0) {
				result = "FizzBuzz";
			} else if (pos % 3 == 0) {
				result = "Fizz";
			} else if (pos % 5 == 0) {
				result = "Buzz";
			} else {
				result = String.valueOf(pos);
			}
			return result + "\n" + returnResultFizzBuzz(++pos, end);
		} else {
			return "";
		}
	}

	public static long combinaison(int n) {
		long count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n - i; j++) {
				count++;
			}
		}

		return count;
	}

	public static int findSmallestInterval(int[] numbers) {
		// we write the code here

		Arrays.sort(numbers);// i sorted it
		int diff = numbers[1] - numbers[0];
		int diffNeu = 0;

		for (int i = 1; i < numbers.length - 1; i++) {
			diffNeu = numbers[i + 1] - numbers[i];
			if (diffNeu < diff) {
				diff = diffNeu;
			}
		}

		return diffNeu;
	}

	public static int findSmallestIntervalLambda(int[] numbers) {
		// we write the code here

		Arrays.sort(numbers);// i sorted it
	
		return IntStream.range(0, numbers.length-1).map(i->numbers[i+1] - numbers[i]).min().getAsInt();
	}

		
	 public static boolean isTwin(String a, String b) {
	        byte[] first = a.toLowerCase().getBytes();
	        byte[] second = b.toLowerCase().getBytes();
	        Arrays.sort(first);
	        Arrays.sort(second);
	        
	        return new String(first).equals(new String(second));
	}
		
	public static int next(int n) {
		int res = n;

		List<Integer> numbers = new ArrayList<>();
		while (n != 0) {
			numbers.add(n % 10);
			n /= 10;
		}

		while (true) {
			res++;

			if (res == Integer.MAX_VALUE) {
				res = -1;
				break;
			}

			String tempString = Integer.toString(res);
			Optional<Integer> resultat = numbers.stream().filter(d -> tempString.contains(String.valueOf(d))).findAny();
			if (resultat.isEmpty()) {
				break;
			}
		}

		return res;
	}

	public static void main(String[] args) {

		List<Integer> listOfIntegers = Arrays.asList(1, 2, 3, 4, 56, 7, 89, 10);
		Integer expectedResult = 89;

		// then
		int max = listOfIntegers.stream().mapToInt(v -> v).max().getAsInt();
		
		System.out.println("max :" + max);
		List<Integer> list = Arrays.asList(2, 4, 1, 3, 7, 5, 9, 6, 8);
		Optional<Integer> maxNumber = list.stream().max((i, j) -> i.compareTo(j));
		System.out.println(maxNumber.get());
		
		String s1 = computeCheckDigit(39847);
		System.out.println("s " + s1);

		long nb = combinaison(4);

		System.out.println("Nombre de tirage possible : " + nb);

		int t = next(654321);

		System.out.println("next : " + t);

		int[] prices = { 11, 20, 40, 9, 4 };
		int total = calculateTotalPrice(prices, 20);

		System.out.println("Total : " + total);

		String fizzBuzz = returnResultFizzBuzz(1, 15);

		System.out.println("FizzBuzz " + fizzBuzz);

		Integer[] nbs = new Integer[] { 1, 8, 2, 1, 3, 4, 4, 4, 5, 7, 6, 8 };
		
	
		
		Set<Integer> setString = new LinkedHashSet<Integer>();
		for (int i = 0; i < nbs.length; i++) {
			setString.add(nbs[i]);
		}

		Set<Integer> setStringStream = Arrays.asList(nbs).stream().collect(Collectors.toCollection(LinkedHashSet::new));
		System.out.println("nbs " + nbs);
		System.out.println("setString " + setString);
		System.out.println("setStringStream " + setStringStream);

		// Set<Integer> allItems = new HashSet<>();
		Set<Integer> duplicates = Arrays.stream(nbs)
				// .filter(n -> !allItems.add(n)) //Set.add() returns false if the item was
				// already in the set.
				.collect(Collectors.toSet());
		System.out.println(duplicates); //

		int nombres[] = { 8, 2, 3, 4, 1, 2, 3, 4, 5, 1, 2, 3, 4 };

		nombres = Arrays.stream(nombres).distinct().toArray();

		System.out.println("nombres : " + nombres);

		int[] numbers = { 130, 150, 17, 15, 120 };
		System.out.println("index of 2 is " + Arrays.binarySearch(numbers, 7));

		boolean v = exists(numbers, 2);
		System.out.println("v : " + v);

		int[] str = { -12, 2, 13, 141, 5, 121, 34 };

		int near = Arrays.stream(str).reduce((a, b) -> abs(a) < abs(b) ? a : (abs(a) == abs(b) ? max(a, b) : b))
				.getAsInt();
		int smallinterval = Arrays.stream(numbers).sorted().limit(2).reduce((small, big) -> (abs(big) - abs(small)))
				.getAsInt();

		int intervallambda = findSmallestIntervalLambda(str);
		int interval = findSmallestInterval(str);
		
		System.out.println("interval " + interval + " - " + intervallambda );
		
		System.out.println("near :" + near);

		System.out.println("smallinterval :" + smallinterval);

		// .ifPresent(System.out::println);

		int[] arr = { 15, -7, 9, 14, 7, 12 };

		// arr[i] > 0 && arr[closestIndex] < 0

		int diff = Integer.MAX_VALUE;

		for (int i = 0; i < arr.length; ++i) {
			int abs = Math.abs(arr[i]);
			if (abs < diff) {
				diff = abs;
			} else if (abs == diff) {
				// same distance to zero but positive
				diff = max(arr[i], diff);
			}
		}
		System.out.println(near + " - " + diff);

		String s = "JavaTest";
		int va = s.indexOf("Java");

		System.out.println("va " + va);

		int intMin[] = { 8, 2, 14, -11, -121, -18, 5, -5, 21 };

		Arrays.sort(intMin);
		for (int i : intMin) {
			System.out.println("intMin " + i);
		}
		int diffMin = intMin[1] - intMin[0];
		int diffNeu;

		for (int i = 1; i < intMin.length - 1; i++) {
			diffNeu = intMin[i + 1] - intMin[i];
			if (Math.abs(diffNeu) < Math.abs(diffMin)) {
				diffMin = diffNeu;
			}
		}

		System.out.println("diffMin : " + diffMin);
	}

}
