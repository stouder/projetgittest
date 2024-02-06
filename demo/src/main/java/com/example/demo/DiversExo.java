package com.example.demo;

import static java.lang.Math.abs;
import static java.lang.Math.max;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiversExo {

	@FunctionalInterface
	interface Operation {
		int calculate(int a, int b);
	}

	static class Multiply {
		public int performOperation(int number1, int number2) {
			return number1 * number2;
		}
	}

	static class Addition {
		public int performOperation(int number1, int number2) {
			return number1 + number2;
		}
	}

	@FunctionalInterface
	interface CustomOperation {
		public int performOperation(int number);
	}

	static class MultiplyOperation implements CustomOperation {
		public int performOperation(int number) {
			return 2 * number;

		}
	}

	static class SquareOperation implements CustomOperation {
		public int performOperation(int number) {
			return number * number;
		}
	}

	static class InvertOperation implements CustomOperation {
		public int performOperation(int number) {
			return -number;
		}
	}

	static int[] filterDuplicate(int[] arr) {
		return Arrays.stream(arr).distinct().toArray();
	}

	static void sortByEven(List<Integer> liste) {
		liste.sort((e1, e2) -> (e1 % 2) - (e2 % 2));
	}

	static List<Integer> squareNumberEven(List<Integer> list) {
		return list.stream().filter(n -> n % 2 == 0).map(n -> n * n).collect(Collectors.toList());
	}

	static void sortAgeList(List<Personne> list) {
		list.sort((p1, p2) -> p1.getAge() - p2.getAge());
	}

	static void sortNameList(List<Personne> list) {
		list.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
	}

	static void sortAgeAndNameList(List<Personne> list) {
		list.sort(Comparator.comparingInt(Personne::getAge).thenComparing(Personne::getName));
	}

	static double getAverageAge(List<Personne> list) {
		return list.stream().filter(p -> p.getAge() < 30).mapToInt(p -> p.getAge()).average().orElse(0.0);
	}

	static List<Integer> ageToBirthYear(List<Personne> list) {
		return list.stream().mapToInt(personne -> LocalDate.now().getYear() - personne.getAge()).boxed()
				.collect(Collectors.toList());
	}

	static List<String> filterString(List<String> list) {
		return list.stream().filter(ch -> ch.length() > 3 && ch.startsWith("A")).collect(Collectors.toList());
	}

	static void filterEmployee(List<Employee> list) {
		list.stream().filter(emp -> emp.getAge() > 30)
				.sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary())).forEach(System.out::println);
	}

	static boolean filterByThreshold(List<Integer> numbers, int threshold) {
		return numbers.stream().allMatch(n -> n > threshold);
	}

	static String concatenerString(List<String> list, char startingLetter) {
		return list.stream().filter(s -> s.charAt(0) == startingLetter).collect(Collectors.joining());
	}

	static boolean checkIfNull(List<String> list) {
		return list.stream().anyMatch(Objects::isNull);
	}

	static boolean checkIfStartA(List<String> list, String startingLetter) {
		return list.stream().anyMatch(s -> s.startsWith(startingLetter));
	}

	static boolean checkIfNoneStartsWithA(List<String> list, String startingLetter) {
		return list.stream().noneMatch(s -> s.startsWith(startingLetter));
	}

	static long countIfCondition(List<String> list) {
		return list.stream().filter(ch -> ch.startsWith("A")).count();
	}

	static List<String> convertToUpperCase(List<String> list) {
		return Optional.ofNullable(list).map(lst -> lst.stream().map(String::toUpperCase).collect(Collectors.toList()))
				.orElse(Collections.emptyList());
	}

	static String concatenerChaines(List<String> list) {
		String result = list.stream().reduce("", (one, s) -> one + " " + s);

		return result;
	}

	static boolean sameType(List<Objects> list) {

		boolean allSameType = list.stream().map(Object::getClass).distinct().count() == 1;

		return allSameType;
	}

	static List<String> extractElement(List<String> elts, int nbElement) {
		return elts.stream().limit(nbElement).collect(Collectors.toList());
	}

	static int sumLengthString(List<String> elts) {
		return elts.stream().mapToInt(String::length).sum();
	}

	static List<String> lengthString(List<String> elts) {
		return elts.stream().map(s -> "Taille de " + s + " : " + s.length()).collect(Collectors.toList());
	}

	static List<Integer> lengthString2(List<String> elts) {
		return elts.stream().map(String::length).collect(Collectors.toList());
	}

	static long countNumOK(List<String> elts) {
		return elts.stream().filter(name -> name.startsWith("A") && name.length() > 3).count();
	}

	private static void performOperation(int a, int b, Operation operation) {

	}

	static String yougestPerson(List<Personne> personnes) {
		String nomPersonnePlusJeune = personnes.stream().filter(p -> p.getAge() < 30) // Filtre les personnes de moins
																						// de 30 ans
				.sorted((p1, p2) -> p1.getAge() - p2.getAge()) // Trie par ordre décroissant d'âge
				.map(Personne::getName) // Obtient le nom de la personne la plus jeune
				.findFirst().orElse("Aucune personne trouvée");

		return nomPersonnePlusJeune;
	}

	static int closetToZero(int[] arr) {
		int temp = arr[0];

		for (int i = 0; i < arr.length; ++i) {
			int abs = Math.abs(arr[i]);
			if (abs < temp) {
				temp = abs;
			} else if (abs == temp) {
				temp = max(arr[i], temp);
			}
		}

		return temp;
	}

	static int closetToZero2(int[] arr) {
		if (arr.length == 0) {
			throw new IllegalArgumentException("Le tableau ne peut pas être vide");
		}

		int closest = arr[0];

		for (int i = 1; i < arr.length; i++) {
			if (Math.abs(arr[i]) < Math.abs(closest) || (Math.abs(arr[i]) == Math.abs(closest) && arr[i] > closest)) {
				closest = arr[i];
			}
		}

		return closest;
	}

	static int closetToZero3(int[] arr) {
		return Arrays.stream(arr)
				.reduce((a, b) -> Math.abs(a) < Math.abs(b) || (Math.abs(a) == Math.abs(b) && a > 0) ? a : b).orElse(0);

	}

	static int greaterElements(int[] arr) {
		return Arrays.stream(arr).reduce(Integer.MAX_VALUE, (a,b)->Integer.max(a,b));
	}
	
	static boolean exists(int[] ints, int k) {
		return Arrays.binarySearch(ints, k) >= 0 ? true : false;
	}

	public static int calculateTotalPrice2(int[] prices, int discount) {
		int total1 = 0;
		int len = prices.length;
		Arrays.sort(prices);
		total1 = Arrays.stream(prices).limit(len - 1).reduce((a, b) -> a + b).getAsInt();

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

		char[] digits = String.valueOf(identificationNumber).toCharArray();

		int even = 0;
		int odd = 0;
		for (int i = 0; i < digits.length; i++) {
			if (i % 2 == 0) {
				even = even + Integer.parseInt(String.valueOf(digits[i]));
			}

			if (i % 2 == 1) {
				odd = odd + Integer.parseInt(String.valueOf(digits[i]));
			}

		}

		even = 3 * even + odd;

		String resutlat = String.valueOf(even);
		char[] resutlats = resutlat.toCharArray();
		int rest = 0;
		if (Integer.parseInt(String.valueOf(resutlats[resutlats.length - 1])) != 0) {
			rest = 10 - Integer.parseInt(String.valueOf(resutlats[resutlats.length - 1]));
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
		Arrays.sort(numbers);

		int diff = numbers[1] - numbers[0];
		int diffTemp = 0;

		for (int i = 1; i < numbers.length - 1; i++) {
			diffTemp = numbers[i + 1] - numbers[i];
			if (diffTemp < diff) {
				diff = diffTemp;
			}
		}

		return diffTemp;
	}

	public static int findSmallestIntervalLambda(int[] numbers) {
		Arrays.sort(numbers);

		return IntStream.range(0, numbers.length - 1).map(i -> numbers[i + 1] - numbers[i]).min().getAsInt();
	}

	public static boolean isTwin(String a, String b) {
		byte[] first = a.toLowerCase().getBytes();
		byte[] second = b.toLowerCase().getBytes();
		Arrays.sort(first);
		Arrays.sort(second);

		return new String(first).equals(new String(second));
	}

	public static void main(String[] args) {

		int[] arr1 = { 5, 1, 1, 7, 5, 9 };

		arr1 = filterDuplicate(arr1);

		for (int i = 0; i < arr1.length - 1; i++) {
			System.out.print(arr1[i]);
		}

		System.out.println("----------- filterDuplicate");

		List<Integer> listOfIntegers = Arrays.asList(1, 2, 3, 4, 56, 7, 89, 10);
		int max = listOfIntegers.stream().mapToInt(v -> v).max().getAsInt();

		System.out.println("max :" + max);

		System.out.println("----------- Max");

		List<Integer> list = Arrays.asList(2, 4, 1, 3, 7, 5, 9, 6, 8);
		Optional<Integer> maxNumber = list.stream().max((i, j) -> i.compareTo(j));
		System.out.println(maxNumber.get());

		System.out.println("----------- Luhn");

		String s1 = computeCheckDigit(39847);
		System.out.println("Valeur  " + s1);

		System.out.println("----------- combinaison");

		long nb = combinaison(4);

		System.out.println("Nombre de tirage possible : " + nb);

		System.out.println("----------- CalculateTotalPrice");

		int[] prices = { 11, 20, 40, 9, 4 };
		int total = calculateTotalPrice(prices, 20);

		System.out.println("Total : " + total);

		System.out.println("----------- fizzBuzz");

		String fizzBuzz = returnResultFizzBuzz(1, 15);

		System.out.println("FizzBuzz " + fizzBuzz);

		Integer[] nbs = new Integer[] { 1, 8, 2, 1, 3, 4, 4, 4, 5, 7, 6, 8 };

		Set<Integer> setString = new LinkedHashSet<Integer>();
		for (int i = 0; i < nbs.length; i++) {
			setString.add(nbs[i]);
		}

		Set<Integer> setStringStream = Arrays.asList(nbs).stream().collect(Collectors.toCollection(LinkedHashSet::new));
		System.out.println("setString " + setString);
		System.out.println("setStringStream " + setStringStream);

		System.out.println("----------- ");

		// Set<Integer> allItems = new HashSet<>();
		Set<Integer> duplicates = Arrays.stream(nbs)
				// .filter(n -> !allItems.add(n)) //Set.add() returns false if the item was
				// already in the set.
				.collect(Collectors.toSet());
		System.out.println(duplicates); //

		System.out.println("-----------");

		int[] nombres = { 8, 2, 3, 4, 1, 2, 3, 4, 5, 1, 2, 3, 4 };

		nombres = Arrays.stream(nombres).distinct().toArray();
		for (int i = 0; i < nombres.length - 1; i++) {
			System.out.println("nombres " + nombres[i]);
		}

		System.out.println("----------- Recherche");

		int[] numbers = { 130, 150, 17, 15, 120 };
		System.out.println("index of 2 is " + Arrays.binarySearch(numbers, 7));

		System.out.println("----------- Exists");

		boolean v = exists(numbers, 2);
		System.out.println("Exists : " + v);

		System.out.println("----------- Interval");

		int[] str = { -12, 2, 13, 141, 5, 121, 34 };

		int near = Arrays.stream(str).reduce((a, b) -> abs(a) < abs(b) ? a : (abs(a) == abs(b) ? max(a, b) : b))
				.getAsInt();
		int smallinterval = Arrays.stream(numbers).sorted().limit(2).reduce((small, big) -> (abs(big) - abs(small)))
				.getAsInt();
		int intervallambda = findSmallestIntervalLambda(str);
		int interval = findSmallestInterval(str);

		System.out.println("interval " + interval + " - " + intervallambda);
		System.out.println("near :" + near);
		System.out.println("smallinterval :" + smallinterval);

		System.out.println("----------- closetToZero");

		int[] arr = { 15, -7, 9, 14, 7, 12 };
		int[] intMin = { 8, 2, 14, -11, -121, -18, 5, -5, 21 };
		int diff1 = closetToZero(arr);
		int diff2 = closetToZero(intMin);

		System.out.println("closetToZero 1 : " + diff1);
		System.out.println("closetToZero 2 : " + diff2);

		System.out.println("-----------");

		System.out.println("----------- sortByEven");

		List<Integer> listTest = Arrays.asList(1, 2, 3, 4, 56, 7, 89, 10);
		sortByEven(listTest);
		listTest.forEach(n -> System.out.print(n + "-"));

		System.out.println("");
		System.out.println("-----------");

		System.out.println("----------- Square number Even");
		List<Integer> numbersEven = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<Integer> numbersEvens = squareNumberEven(numbersEven);

		numbersEvens.forEach(n -> System.out.println(n));

		System.out.println("-----------");
		System.out.println("----------- Chaine > 3 et commaencat par A");

		List<String> listeStrings = Arrays.asList("Apple", "Banana", "Orange", "Avocado", "Grapes", "Kiwi");
		filterString(listeStrings).forEach(ch -> System.out.println(ch));

		System.out.println("-----------");
		System.out.println("----------- Exercice avec Personne");
		System.out.println("");

		List<Personne> personnes = Arrays.asList(new Personne("Alice", 25), new Personne("Bob", 35),
				new Personne("Charlie", 40), new Personne("David", 28));

		double ageAvg = getAverageAge(personnes);

		System.out.println("Personne de plus de 30 ans classé par salaire decroissant : " + ageAvg);
		System.out.println("");
		System.out.println("-----------");

		sortAgeAndNameList(personnes);
		// sortNameList(personnes);

		personnes.stream().forEach(p -> System.out.println(p.getName()));

		System.out.println("Personne la plus jeune : " + yougestPerson(personnes));

		System.out.println("");
		System.out.println("-----------");

		System.out.println("Liste des années de naissance : " + ageToBirthYear(personnes));

		System.out.println("-----------");
		System.out.println("----------- Exercice avec Employee");

		List<Employee> employeeList = List.of(new Employee("Alice", 28, 60000), new Employee("Bob", 35, 90000),
				new Employee("Charlie", 31, 55000), new Employee("David", 40, 70000));

		filterEmployee(employeeList);

		System.out.println("");
		System.out.println("-----------");

		System.out.println("-----------");
		System.out.println("----------- Exercice avec Valeur seuil");

		List<Integer> testList = Arrays.asList(1, 5, 56, 7, 89, 10);
		boolean retour = filterByThreshold(testList, -4);

		System.out.println("Retour : " + retour);

		System.out.println("");
		System.out.println("-----------");
		System.out.println("----------- Exercice count si condition");

		long nbIfCount = countIfCondition(listeStrings);
		System.out.println("valeur : " + nbIfCount);

		System.out.println("");
		System.out.println("-----------");
		System.out.println("----------- Exercice condition au moins 1 null");

		List<String> listeString = Arrays.asList("Apple", "fabien", "Orange", "Avocado", "Grapes", "Kiwi");

		boolean b = checkIfNull(listeString);

		System.out.println("b " + b);

		System.out.println("");
		System.out.println("-----------");
		System.out.println("----------- Exercice avec functionalInterface 1");

		Operation addition = (a, c) -> a + c;
		performOperation(5, 3, addition);

		Operation multiplication = (a, c) -> a * c;
		performOperation(5, 3, multiplication);

		System.out.println("");
		System.out.println("-----------");
		System.out.println("----------- Exercice avec functionalInterface 2");

		int number = 3;

		CustomOperation multiplyOperation = new MultiplyOperation();
		CustomOperation squareOperation = new SquareOperation();
		CustomOperation invertOperation = new InvertOperation();

		int res1 = multiplyOperation.performOperation(3);
		int res2 = squareOperation.performOperation(3);
		int res3 = invertOperation.performOperation(3);

		System.out.println("res1 " + res1 + " - res2 - " + res2 + " - res3 - " + res3);

		System.out.println("");
		System.out.println("-----------");
		System.out.println("----------- Exercice avec functionalInterface 2");

		List<Integer> listStringSize = lengthString2(listeString);
		listStringSize.forEach(e -> System.out.println(e));
	}

}
