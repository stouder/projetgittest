package com.example.demo;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class SolutionMontpellier {

    private static double distance(double longA, double latA, double longB, double latB){
        double x = (longB-longA)*Math.cos((latA+latB)/2);
        double y = latB - latA;
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2)) * 6371;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String LON = in.next();
        String LAT = in.next();
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            String DEFIB = in.nextLine();
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        double min = Double.POSITIVE_INFINITY;
        String res = "";
        Double d;
        try {
        	 Double lon = Double.valueOf(LON.replace(',', '.'));
        	 Double lat = Double.valueOf(LAT.replace(',', '.'));
        	 
        	 in.nextLine();
             
             res = IntStream.range(0, N)
             .mapToObj(i -> in.nextLine().replace(',', '.').split(";"))
             .min((defib1, defib2) -> Double.compare(
                     distance(lon, lat, Double.valueOf(defib1[4]), Double.valueOf(defib1[5])),
                     distance(lon, lat, Double.valueOf(defib2[4]), Double.valueOf(defib2[5]))
             ))
             .map(defib -> defib[1])
             .orElse(null);
        } catch (NumberFormatException e) {
            System.err.println("Erreur de conversion : " + e.getMessage());
        }
       
   
        
        
        System.out.println(res);
    }
}
