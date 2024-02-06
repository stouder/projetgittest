package com.example.demo;

import java.util.*;

public class SolutionTelephone {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Node root = new Node();

		for (int i = 0; i < N; i++) {
			root.add(in.next().chars().toArray(), 0);
		}

		System.out.println(root.countNode());
	}

	static class Node {
		private Map<Integer, Node> children = new HashMap<>();

		public void add(int[] number, int index) {
		    if (index < number.length) {
		        int digit = number[index];
		        Node child = children.computeIfAbsent(digit, k -> new Node());
		        child.add(number, index + 1);
		    }
		}

		// On compte
		int countNode() {
			int val = 0;

			Deque<Node> stack = new LinkedList<>();
			stack.addAll(children.values()); // on initianalise la stack

			while (!stack.isEmpty()) {
				Node current = stack.pop(); // on recupere le current et on enleve l'element courant
				val++; // on incremente
				stack.addAll(current.children.values()); // nouvelle stack
			}

			return val;
		}
	}
}
