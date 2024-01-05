package com.example.demo;

import java.util.*;
import java.io.*;
import java.math.*;


/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class SolutionClosetToZero {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        int[] temperatureList = new int[n];

        for (int i = 0; i < n; i++) {
            int t = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
            temperatureList[i] = t; 
        }

        int resultat = closetZero(temperatureList);
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(resultat);
    }

    private static int closetZero(int[] temperatureList){
       
        Arrays.sort(temperatureList);
        int t0 = temperatureList[0];
        for(int i=0;i<temperatureList.length;i++){
            int cur = temperatureList[i] * temperatureList[i];
            if(cur < t0*t0 ){
                t0 = temperatureList[i]; 
            }
        }

        return t0;
    }
}
