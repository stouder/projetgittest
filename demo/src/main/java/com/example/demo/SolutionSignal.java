package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SolutionSignal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of input signals
        int n = Integer.parseInt(scanner.nextLine());

        // Number of output signals
        int m = Integer.parseInt(scanner.nextLine());

        // Map to store input signal data
        Map<String, String> inputSignals = new HashMap<>();

        // Read input signal data
        for (int i = 0; i < n; i++) {
            String[] inputSignal = scanner.nextLine().split(" ");
            String signalName = inputSignal[0];
            String signalForm = inputSignal[1];
            inputSignals.put(signalName, signalForm);
        }

        // Process each output signal
        for (int i = 0; i < m; i++) {
            String[] outputSignal = scanner.nextLine().split(" ");
            String outputName = outputSignal[0];
            String gateType = outputSignal[1];
            String input1Name = outputSignal[2];
            String input2Name = outputSignal[3];

            // Get input values
            int input1 = getInputValue(inputSignals.get(input1Name));
            int input2 = getInputValue(inputSignals.get(input2Name));

            // Apply logic gate operation
            int result = applyLogicGate(gateType, input1, input2);

            // Print output signal
            System.out.println(outputName + " " + getSignalForm(result));
        }
    }

    private static int getInputValue(String signalForm) {
        // Convert signal form to binary value
    	return Integer.parseInt(signalForm.replace("_", "0").replace("-", "1"), 2);
    }

    private static int applyLogicGate(String gateType, int input1, int input2) {
        // Apply logic gate operation
        switch (gateType) {
            case "AND":
                return input1 & input2;
            case "OR":
                return input1 | input2;
            case "XOR":
                return input1 ^ input2;
            case "NAND":
                return ~(input1 & input2) & 1;
            case "NOR":
                return ~(input1 | input2) & 1;
            case "NXOR":
                return ~(input1 ^ input2) & 1;
            default:
                return 0;
        }
    }

    private static String getSignalForm(int value) {
        // Convert binary value to signal form
        return value == 0 ? "_" : "-";
    }
}
