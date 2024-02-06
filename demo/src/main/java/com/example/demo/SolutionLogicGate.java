package com.example.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SolutionLogicGate {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = Integer.parseInt(scanner.nextLine());

		int m = Integer.parseInt(scanner.nextLine());

		Map<String, String> inputSignals = new HashMap<>();

		for (int i = 0; i < n; i++) {
			String[] inputSignal = scanner.nextLine().split(" ");
			String signalName = inputSignal[0];
			String signalForm = inputSignal[1];
			inputSignals.put(signalName, signalForm);
		}

		for (int i = 0; i < m; i++) {
			String[] outputSignal = scanner.nextLine().split(" ");
			String outputName = outputSignal[0];
			String gateType = outputSignal[1];
			String input1Name = outputSignal[2];
			String input2Name = outputSignal[3];

			Character[] input1 = getInput(inputSignals.get(input1Name));
			Character[] input2 = getInput(inputSignals.get(input2Name));

			String result = applyLogicGate(gateType, input1, input2);

			System.out.println(outputName + " " + getOutput(result));
		}
	}

	static Character[] getInput(String input) {
		return input.chars().mapToObj(c -> (char) c == '_' ? '0' : '1').toArray(Character[]::new);
	}

	static String applyLogicGate(String gate, Character[] input1, Character[] input2) {
		switch (gate) {
		case "AND":
            return operationAnd(input1, input2);
        case "OR":
            return operationOr(input1, input2);
        case "XOR":
            return operationXor(input1, input2);
        case "NAND":
            return operationNand(input1, input2);
        case "NXOR":
            return operationNxor(input1, input2);
        case "NOR":
            return operationNor(input1, input2);
        default:
            return "";
		}
	}

	static String operationAnd(Character[] input1, Character[] input2) {
		if (input1.length == input2.length) {
			char[] result = new char[input1.length];

			for (int i = 0; i < input1.length; i++) {
				result[i] = (char) (input1[i] & input2[i]);
			}

			return new String(result);

		}
		
		return "";
	}

	static String operationOr(Character[] input1, Character[] input2) {
		if (input1.length == input2.length) {
			char[] result = new char[input1.length];

			for (int i = 0; i < input1.length; i++) {
				result[i] = (char) (input1[i] | input2[i]);
			}

			return new String(result);

		}
		
		return "";
	}
	
	static String operationXor(Character[] input1, Character[] input2) {
	    char[] result = new char[input1.length];
	    for (int i = 0; i < input1.length; i++) {
	    	result[i] = (char) ('0' + (input1[i] - '0') ^ (input2[i] - '0'));
	    }
	    return new String(result);
	}
	
	static String operationNand(Character[] input1, Character[] input2) {
	    // NAND is the inverse of AND
	    return invertResult(operationAnd(input1, input2));
	}

	static String operationNxor(Character[] input1, Character[] input2) {
	    // NXOR is the inverse of XOR
	    return invertResult(operationXor(input1, input2));
	}

	static String operationNor(Character[] input1, Character[] input2) {
	    // NOR is the inverse of OR
	    return invertResult(operationOr(input1, input2));
	}

	static String invertResult(String result) {
	    char[] invertedResult = new char[result.length()];
	    for (int i = 0; i < result.length(); i++) {
	        invertedResult[i] = (char) ('1' - result.charAt(i) + '0');
	    }
	    return new String(invertedResult);
	}
	static String convertForm(Character[] tab) {
		return Arrays.stream(tab).map(c -> c.equals('0') ? '_' : '-').map(Object::toString)
				.collect(Collectors.joining());
	}

	static String getOutput(String input) {
		
		Character[] tab = input.chars().mapToObj(c -> (char) c).toArray(Character[]::new);

		return convertForm(tab);

	}
}
