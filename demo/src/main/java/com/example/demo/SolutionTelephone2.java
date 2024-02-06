package com.example.demo;

import java.util.*;

public class SolutionTelephone2 {

    public static void main(String[] args) {
        List<String> numeros = Arrays.asList(
                "123456789",
                "1234567890",
                "987654321",
                "987123456",
                "123987456"
        );

        Map<String, List<String>> numerosParPrefixe = new HashMap<>();

       
        for (String numero : numeros) {
            String prefixe = numero.substring(0, 3); // Utilisation des 3 premiers chiffres comme préfixe

            numerosParPrefixe.computeIfAbsent(prefixe, k -> new ArrayList<>()).add(numero);
        }

        for (Map.Entry<String, List<String>> entry : numerosParPrefixe.entrySet()) {
            System.out.println("Numéros avec le même préfixe " + entry.getKey() + ": " + entry.getValue());
        }
    }
}

