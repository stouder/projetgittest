package com.example.demo;

import java.util.*;

class RomeCorrect {

    public static void main(String args[]) {
        List<List<Integer>> paths = new ArrayList<>();
        paths.add(Arrays.asList(1, 50));
        paths.add(Arrays.asList(50, 100));
        paths.add(Arrays.asList(50, 75));
        paths.add(Arrays.asList(75, 25));
        paths.add(Arrays.asList(75, 100));

        int nbPath = path(1, 100, paths) + path(100, 1, paths);

        System.out.println("nbPath :" + nbPath);
    }

    static int path(int departure, int arrive, List<List<Integer>> paths) {
        int nbPath = 0;
        nbPath = pathHelper(departure, arrive, paths, nbPath);
        
        return nbPath;
    }

    static int pathHelper(int currentPoint, int target, List<List<Integer>> paths, int nbPath) {
        for (List<Integer> path : paths) {
            int secondPoint = checkPath(currentPoint, path);

            if (secondPoint == target) {
                nbPath++;
            } else if (secondPoint != 0) {
                nbPath = pathHelper(secondPoint, target, paths, nbPath);
            }
        }

        return nbPath;
    }

    static int checkPath(int firstPoint, List<Integer> path) {
        if (path.get(0) == firstPoint) {
            return path.get(1);
        } else {
            return 0;
        }
    }
}

