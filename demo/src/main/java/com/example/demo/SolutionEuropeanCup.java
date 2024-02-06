package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
/*
 * Cardiff Blues,FC Grenoble,London Irish,Rovigo Delta
Atlantique Stade Rochelais,Aviron Bayonnais,Connacht,Exeter Chiefs
Bucuresti Wolves,Newcastle Falcons,Newport Dragons,Stade Francais Paris
Edinburgh Rugby,London Welsh,Lyon Olympique Universitaire,Union Bordeaux-Begles
CA Brive,Gloucester Rugby,US Oyonnax,Zebre
Cardiff Blues,37,5,FC Grenoble,14,1
London Irish,70,10,Rovigo Delta,14,2
Rovigo Delta,18,2,Cardiff Blues,33,5
FC Grenoble,15,0,London Irish,25,4
FC Grenoble,68,10,Rovigo Delta,10,1
Cardiff Blues,24,3,London Irish,14,2
Rovigo Delta,17,1,FC Grenoble,20,4
London Irish,34,3,Cardiff Blues,23,2
Cardiff Blues,104,16,Rovigo Delta,12,2
London Irish,43,6,FC Grenoble,41,4
Rovigo Delta,6,0,London Irish,34,5
FC Grenoble,3,0,Cardiff Blues,28,4
Connacht,48,7,Atlantique Stade Rochelais,12,2
Aviron Bayonnais,30,3,Exeter Chiefs,24,2
Atlantique Stade Rochelais,25,3,Aviron Bayonnais,13,1
Exeter Chiefs,33,5,Connacht,13,1
Atlantique Stade Rochelais,10,1,Exeter Chiefs,36,4
Connacht,42,5,Aviron Bayonnais,19,1
Exeter Chiefs,41,6,Atlantique Stade Rochelais,17,2
Aviron Bayonnais,27,3,Connacht,29,2
Aviron Bayonnais,14,2,Atlantique Stade Rochelais,0,0
Connacht,24,4,Exeter Chiefs,33,3
Exeter Chiefs,45,6,Aviron Bayonnais,3,1
Atlantique Stade Rochelais,20,2,Connacht,30,4
Newcastle Falcons,43,7,Bucuresti Wolves,19,3
Stade Francais Paris,22,1,Newport Dragons,38,4
Newport Dragons,26,3,Newcastle Falcons,30,3
Bucuresti Wolves,9,0,Stade Francais Paris,13,1
Newcastle Falcons,30,4,Stade Francais Paris,23,2
Bucuresti Wolves,10,1,Newport Dragons,37,5
Stade Francais Paris,31,5,Newcastle Falcons,24,3
Newport Dragons,69,11,Bucuresti Wolves,17,3
Stade Francais Paris,47,7,Bucuresti Wolves,12,0
Newcastle Falcons,29,4,Newport Dragons,40,6
Newport Dragons,30,2,Stade Francais Paris,19,3
Bucuresti Wolves,10,1,Newcastle Falcons,52,8
Union Bordeaux-Begles,13,1,Edinburgh Rugby,15,2
Lyon Olympique Universitaire,28,4,London Welsh,18,2
London Welsh,20,2,Union Bordeaux-Begles,52,7
Edinburgh Rugby,25,1,Lyon Olympique Universitaire,17,2
Union Bordeaux-Begles,37,5,Lyon Olympique Universitaire,29,2
Edinburgh Rugby,25,3,London Welsh,13,1
Lyon Olympique Universitaire,37,4,Union Bordeaux-Begles,28,3
London Welsh,6,0,Edinburgh Rugby,24,2
Union Bordeaux-Begles,26,4,London Welsh,3,0
Lyon Olympique Universitaire,21,2,Edinburgh Rugby,19,2
Edinburgh Rugby,38,4,Union Bordeaux-Begles,20,2
London Welsh,12,2,Lyon Olympique Universitaire,17,3
Gloucester Rugby,55,7,CA Brive,0,0
Zebre,24,2,US Oyonnax,33,3
CA Brive,21,3,Zebre,26,2
US Oyonnax,15,0,Gloucester Rugby,25,1
CA Brive,22,3,US Oyonnax,30,3
Gloucester Rugby,35,4,Zebre,10,2
Zebre,16,2,Gloucester Rugby,32,4
US Oyonnax,22,3,CA Brive,17,2
Gloucester Rugby,33,5,US Oyonnax,3,0
Zebre,23,2,CA Brive,13,1
CA Brive,20,2,Gloucester Rugby,31,4
US Oyonnax,20,3,Zebre,3,0

loucester Rugby - Connacht
Exeter Chiefs - Newcastle Falcons
Newport Dragons - Cardiff Blues
London Irish - Edinburgh Rugby
 */

class SolutionEuropeanCup {

	private static final int NUM_POOLS = 5;
	private static final int TOTAL_MATCHES = 60;
	private static final String POOL = "Pool ";
	private static final int SECOND_QUALIFIED_TEAMS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Map<String, Map<String, Pair<Integer, Integer>>> resultats = new HashMap<>();

		int poolNumber = 1;
		while (poolNumber <= NUM_POOLS) {
			List<String> teams = Arrays.asList(scanner.nextLine().split(","));
			Map<String, Pair<Integer, Integer>> teamresultat = new HashMap<>();

			for (String team : teams) {
				teamresultat.put(team, Pair.of(0, 0));
			}

			resultats.put(POOL + poolNumber, teamresultat);
			poolNumber++;
		}

		int numMatch = 1;
		final LinkedHashMap<String, Pair<Integer, Integer>> teamresultatTemp = new LinkedHashMap<>();
		while (numMatch <= TOTAL_MATCHES) {
			String[] match = scanner.nextLine().split(",");
			processMatch(teamresultatTemp, match);

			numMatch++;
		}

		for (int numPool = 1; numPool <= NUM_POOLS; numPool++) {
			classByPool(teamresultatTemp, resultats, numPool);
		}

		Map<String, Pair<Integer, Integer>> firstTeam = new HashMap<>();
		Map<String, Pair<Integer, Integer>> secondTeam = new HashMap<>();
		List<String> teamsForQuart = qualified(resultats, firstTeam, secondTeam);

		getQuarterFinals(teamsForQuart).forEach(System.out::println);

		scanner.close();
	}

	private static void processMatch(Map<String, Pair<Integer, Integer>> teamresultat, String[] match) {
		String teamA = match[0];
		int pointsA = Integer.parseInt(match[1]);
		int triesA = Integer.parseInt(match[2]);

		String teamB = match[3];
		int pointsB = Integer.parseInt(match[4]);
		int triesB = Integer.parseInt(match[5]);

		updatePoolResults(teamresultat, teamA, pointsA, triesA, teamB, pointsB, triesB);
	}

	private static void updatePoolResults(Map<String, Pair<Integer, Integer>> teamresultat, String teamA,
			int teamAPoints, int teamATries, String teamB, int teamBPoints, int teamBTries) {

		int teamApts = calculateScore(teamAPoints, teamATries, teamBPoints);
		int teamBpts = calculateScore(teamBPoints, teamBTries, teamAPoints);

		Pair<Integer, Integer> infoTeamA = teamresultat.getOrDefault(teamA, Pair.of(0, 0));
		Pair<Integer, Integer> infoTeamB = teamresultat.getOrDefault(teamB, Pair.of(0, 0));

		Pair<Integer, Integer> newInfoTeamA = Pair.of(infoTeamA.getLeft() + teamApts,
				infoTeamA.getRight() + teamATries);
		Pair<Integer, Integer> newInfoTeamB = Pair.of(infoTeamB.getLeft() + teamBpts,
				infoTeamB.getRight() + teamBTries);

		teamresultat.put(teamA, newInfoTeamA);
		teamresultat.put(teamB, newInfoTeamB);
	}

	private static int calculateScore(int points, int tries, int opponentTries) {
		int score = points > opponentTries ? 4 : 0;

		if (tries >= 4) {
			score += 1;
		}

		return score;
	}

	private static void classByPool(LinkedHashMap<String, Pair<Integer, Integer>> resultatByPool,
			Map<String, Map<String, Pair<Integer, Integer>>> resultats, int numpool) {

		Map<String, Pair<Integer, Integer>> poolResults = resultats.get(POOL + numpool);

		resultatByPool.forEach((team, resultatTeam) -> {
			if (poolResults.containsKey(team)) {
				poolResults.put(team, resultatTeam);
			}
		});

		Map<String, Pair<Integer, Integer>> sortedPoolResults = poolResults.entrySet().stream()
				.sorted(Comparator
						.comparing((Map.Entry<String, Pair<Integer, Integer>> entry) -> entry.getValue().getLeft())
						.thenComparing(entry -> entry.getValue().getRight()).reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));

		resultats.put(POOL + numpool, sortedPoolResults);
	}

	private static List<String> qualified(Map<String, Map<String, Pair<Integer, Integer>>> resultats,
			Map<String, Pair<Integer, Integer>> firstTeam, Map<String, Pair<Integer, Integer>> secondTeam) {
		
		List<String> qualified = new ArrayList<>();
		
		for (Map.Entry<String, Map<String, Pair<Integer, Integer>>> poolEntry : resultats.entrySet()) {
			Map<String, Pair<Integer, Integer>> poolResults = poolEntry.getValue();

			int count = 0;
			for (Map.Entry<String, Pair<Integer, Integer>> teamEntry : poolResults.entrySet()) {
				if (count == 0) {
					firstTeam.put(teamEntry.getKey(), teamEntry.getValue());
				} else if (count == 1) {
					secondTeam.put(teamEntry.getKey(), teamEntry.getValue());
				}
				
				count++;
				if (count == 2) {
					break;
				}
			}
		}

	
		LinkedHashMap<String, Pair<Integer, Integer>> firstTeamSorted = sortResultatTeams(firstTeam);
		LinkedHashMap<String, Pair<Integer, Integer>> secondTeamSorted = sortResultatTeams(secondTeam);
		
		for (Map.Entry<String, Pair<Integer, Integer>> entry : firstTeamSorted.entrySet()) {
			qualified.add(entry.getKey());
		}

		int num = 0;
		for (Map.Entry<String, Pair<Integer, Integer>> entry : secondTeamSorted.entrySet()) {
			if (num < SECOND_QUALIFIED_TEAMS) {
				qualified.add(entry.getKey());
			}
			num++;
		}

		return qualified;
	}

	private static List<String> getQuarterFinals(List<String> qualified) {
		List<String> quarterFinals = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			String team1 = qualified.get(i);
			String team2 = qualified.get(7 - i);
			quarterFinals.add(team1 + " - " + team2);
		}

		return quarterFinals;
	}

	private static LinkedHashMap<String, Pair<Integer, Integer>> sortResultatTeams(
			Map<String, Pair<Integer, Integer>> teamResultats) {
		return teamResultats.entrySet().stream()
				.sorted(Comparator
						.comparing((Map.Entry<String, Pair<Integer, Integer>> entry) -> entry.getValue().getLeft())
						.thenComparing(entry -> entry.getValue().getRight()).reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));

	}

	private static void displayResultatMatch(Map<String, Map<String, Pair<Integer, Integer>>> resultats) {

		resultats.forEach((key, value) -> {
			System.out.println("Key: " + key);
			value.forEach((team, scores) -> System.out
					.println("  Team: " + team + ", Points: " + scores.getLeft() + ", Tries: " + scores.getRight()));
		});
	}
}
