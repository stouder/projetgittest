package com.example.demo;

import java.util.*;

public class SolutionTelephone3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Node root = new Node();

        while (in.hasNext()) {
            String numberPhone = in.next();
            if (numberPhone.equals("end")) {
                break;
            }

            root.add(numberPhone);
            root.printNumberSamePrefix(numberPhone);
        }
    }

    static class Node {
        private Map<Integer, Node> children = new HashMap<>();
        private List<String> numbers = new ArrayList<>();

        public void add(String numero) {
            Node currentNode = this;

            for (char c : numero.toCharArray()) {
                int chiffre = c - '0';

                currentNode.children.computeIfAbsent(chiffre, k-> new Node());

                currentNode = currentNode.children.get(chiffre);
                currentNode.numbers.add(numero);
            }
        }

        void printNumberSamePrefix(String chiffre) {
            Node currentNode = this;

            for (char c : chiffre.toCharArray()) {
                int digit = c - '0';
                if (currentNode.children.containsKey(digit)) {
                    currentNode = currentNode.children.get(digit);
                } else {
                    return;
                }
            }

            System.out.println("Numéros partageant les " + chiffre.length() + " premiers chiffres avec le préfixe '" + chiffre + "': " + currentNode.numbers);
        }
    }
}
