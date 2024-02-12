package com.example.demo;

import java.util.Random;

public class ApproximationPi {

	public static final int TOTALPOINTS = 100000000;

	public static void main(String[] args) {

		int pointsInUpperRightQuadrant = countPointsInUpperRightQuadrant(TOTALPOINTS);
		double piApproximation = 4.0 * pointsInUpperRightQuadrant / TOTALPOINTS;
		System.out.println("Approximation de pi en utilisant " + TOTALPOINTS + " points : " + piApproximation);
	}

	public static int countPointsInUpperRightQuadrant(int totalPoints) {
		Random random = new Random();
		int pointsInQuadrant = 0;

		for (int i = 0; i < totalPoints; i++) {
			double x = random.nextDouble();
			double y = random.nextDouble();

			// Vérifier si le point est dans le quart supérieur droit
			if (x * x + y * y <= 1 && x >= 0 && y >= 0) {
				pointsInQuadrant++;
			}
		}

		return pointsInQuadrant;
	}
}
