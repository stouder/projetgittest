package com.example.demo;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Rome {

    public static void main(String args[]) {

    List<List<Integer>> paths = new ArrayList<>();
    paths.add(Arrays.asList(1, 50));
    paths.add(Arrays.asList(50,100));
    paths.add(Arrays.asList(25,50));
    paths.add(Arrays.asList(75,25));
    paths.add(Arrays.asList(100,75));
       
    int nbPath=0;
    nbPath = path(1,100,paths,nbPath);

    System.out.println("nbPath :" + nbPath);

    }

    static int path(int departure,int arrive,List<List<Integer>> paths,int nbPath) {
        int secondpoint = 0;
       
        for(List<Integer> path:paths) {
        	secondpoint = checkPath(departure,path);
        	
        	if(secondpoint == arrive){
                nbPath++;              
            }else {
            	if(secondpoint != 0){
                    path(secondpoint,arrive,paths,nbPath);
                }	
            }
    
        }
        
        return nbPath;
    }

    static int checkPath(int firstPoint,List<Integer> path){
        if(path.get(0) == firstPoint){
            return path.get(1);
        }else {
        	return 0;
        }    
    }
}
