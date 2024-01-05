package com.example.demo;

public class Next2 {
	public static void main(String[] args) {
		int nombreInitial = 10;

		int prochainNombre = trouverProchainNombreSansChiffres(nombreInitial);

		System.out.println(
				"Le prochain nombre sans chiffres du nombre initial " + nombreInitial + " est : " + prochainNombre);
	}

	static int trouverProchainNombreSansChiffres(int nombreInitial) {
		// Convertir le nombre initial en une chaîne de caractères
		String nombreInitialStr = Integer.toString(nombreInitial);

		// Initialiser le prochain nombre
		int prochainNombre = nombreInitial + 1;

		String prochainNombreStr = Integer.toString(prochainNombre);
		
		// Boucler jusqu'à trouver un nombre sans chiffres du nombre initial
		while (contientChiffres(prochainNombreStr, nombreInitialStr)) {
			prochainNombre++;
		}

		return prochainNombre;
	}

	static boolean contientChiffres(String nombre, String chiffresExclus) {
		// Vérifier si le nombre contient des chiffres du chiffresExclus
		for (char chiffre : chiffresExclus.toCharArray()) {
			if (nombre.indexOf(chiffre) != -1) {
				return true;
			}
		}
		return false;
	}
}
