package com.example.demo;

import java.util.Scanner;

public class NoSpoon {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int width = in.nextInt();
		int height = in.nextInt();
		in.nextLine();
		String[][] tab = new String[height][width];

		for (int i = 0; i < height; i++) {
			String line = in.nextLine();
			if (line.length() > width) {
				line = line.substring(0, width);
			}

			for (int j = 0; j < width; j++) {
				if (j < line.length()) {
					tab[i][j] = String.valueOf(line.charAt(j));
				} else {
					tab[i][j] = ".";
				}
			}
		}
		printTab(tab, width, height);
		for (int j = 0; j < width; j++) {
			for (int k = 0; k < height; k++) {
				if (tab[k][j].equals("O")) {
					String firstNode = j + " " + k;
					String right = findNode(j, k, "R", tab);
					String down = findNode(j, k, "D", tab);

					System.out.println(firstNode + " " + right + " " + down);
				}
			}

		}
	}

	private static String findNode(int x, int y, String sense, String[][] tab) {

		if (sense.equals("R")) {
			for (int i = x + 1; i < tab[0].length; i++) {
				if (tab[y][i].equals("O")) {
					return i + " " + y;
				}
			}
		} else if (sense.equals("D")) {
			for (int j = y + 1; j < tab.length; j++) {
				if (tab[j][x].equals("O")) {
					return x + " " + j;
				}
			}

		} else {
			return -1 + " " + -1;
		}

		return -1 + " " + -1;
	}

	private static void printTab(String[][] tab, int width, int height) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(tab[i][j] + " ");
			}
			System.out.println();
		}
	}
}
