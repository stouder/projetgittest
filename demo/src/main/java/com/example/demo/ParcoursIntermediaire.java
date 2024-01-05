package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParcoursIntermediaire {

    public static void main(String[] args) {

        Integer pointDeDepart = 1;
        Integer pointDarrivee = 4;

        Map<Integer,List<Integer>> listeDeplacements = new HashMap<>();
        listeDeplacements.put(1,List.of(2,3));
        listeDeplacements.put(2,List.of(5));
        listeDeplacements.put(3,List.of(4));
        
        List<Integer> parcours = trouverParcours(listeDeplacements, pointDeDepart, pointDarrivee);

        if (parcours != null) {
            System.out.println("Parcours trouvé : " + parcours);
        } else {
            System.out.println("Aucun parcours trouvé entre " + pointDeDepart + " et " + pointDarrivee);
        }
    }


    static List<Integer> trouverParcours(Map<Integer,List<Integer>> listeDeplacements, int depart, int arrivee) {

        List<Integer> parcours = new ArrayList<>();
        int actuel = depart;

        while (actuel != arrivee) {
            parcours.add(actuel);

            if (!listeDeplacements.containsKey(actuel)) {
                return null; 
            }

            
            if(listeDeplacements.get(actuel).size()>1) {
            	actuel = listeDeplacements.get(actuel).get(0);
            }else {
            	actuel = listeDeplacements.get(actuel).get(0);
            }
            
        }

        parcours.add(arrivee);
        
        return parcours;
    }
}
