package com.example.demo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DerivativeTime {

	/*
	 * (a*x)'=a (x^a)'=a*x^(a-1) (when a is not 0) (u+v)'=u'+v' (u*v)'=u'*v+v'*u
	 */
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String F = in.nextLine();
		String vs = in.nextLine();
		String dict = in.nextLine();
		String test = "5*x*y";
		F = F.replace('y', '6');
		String res = simpleDerivative(vs, F);
		System.out.println(res);

		int resultat = result(res, '5', vs.charAt(0));

		res = addDerivative("x", "3*x + 4*x^4");
		System.out.println("res : " + res);
		System.out.println("answer : " + resultat);
	}

	private static int result(String exp, char val, char var) {
		exp = exp.replace(var, val);
		return Stream.of(exp.split("\\*")).map(Integer::parseInt).reduce(1, (a, b) -> a * b);

	}

	private static String simpleDerivative(String var, String expression) {
		String[] parts = expression.split("\\*");
		int derivative = 1;
		for (String p : parts) {
			if (!p.equals(var)) {
				derivative *= Integer.parseInt(p);
			}
		}

		StringBuilder res = new StringBuilder();
		res.append(derivative);

		return res.toString();
	}

	private static String puissanceDerivative(String var, String expression) {

		String patternString = "(?:(\\d+)\\s*\\*\\s*)?" + var + "\\^(\\d+)";
		Pattern pattern = Pattern.compile(patternString);

		String a = null;
		String n = null;

		Matcher matcher = pattern.matcher(expression);
		if (matcher.find()) {
			a = matcher.group(1);
			n = matcher.group(2);
			a = (a != null) ? a : "1";
		}

		if (!matcher.matches()) {
			throw new IllegalArgumentException("L'expression n'est pas de la forme " + var + "^a");
		}

		// Extraire la valeur de "a"
		int fac = Integer.parseInt(a);
		int pow = Integer.parseInt(n);

		// Calculer la dérivée
		int derivative = fac;
		String derivativeExpression = fac + "*" + var + "^" + (pow - 1);

		// Ajouter la dérivée à l'expression d'origine
		StringBuilder res = new StringBuilder();
		res.append(derivative);
		res.append("*");
		res.append(derivativeExpression);

		return res.toString();

	}

	private static String addDerivative(String var, String expression) {
		// Diviser l'expression en deux parties : u et v
		String[] parts = expression.split("\\+");
		if (parts.length != 2) {
			throw new IllegalArgumentException("L'expression n'est pas de la forme u+v");
		}
		String u = parts[0].trim();
		String v = parts[1].trim();

		// Calculer les dérivées de u et v
		String du = derivative(var, u);
		String dv = derivative(var, v);

		// Ajouter les dérivées de u et v à l'expression d'origine
		StringBuilder res = new StringBuilder();
		res.append(du);
		res.append(" + ");
		res.append(dv);

		return res.toString();
	}

	private static String mutiplyDerivative(String var, String expression) {
		String[] parts = expression.split("\\*");
		if (parts.length != 2) {
			throw new IllegalArgumentException("L'expression n'est pas de la forme u+v");
		}
		String u = parts[0].trim();
		String v = parts[1].trim();

		String du = derivative(var, u);
		String dv = derivative(var, v);

		StringBuilder res = new StringBuilder();
		res.append(du);
		res.append('*');
		res.append(v);
		res.append(" + ");
		res.append(dv);
		res.append(u);

		return res.toString();

	}

	public static String derivative(String var, String expression) {
		// Vérifier si l'expression est une constante
		if (expression.matches("\\d+")) {
			return "0";
		}

		// Vérifier si l'expression est une variable
		if (expression.equals(var)) {
			return "1";
		}

		// Vérifier si l'expression est une puissance
		String patternString = "(?:(\\d+)\\s*\\*\\s*)?" + var + "\\^(\\d+)";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(expression);
		if (matcher.find()) {
			String powerExpression = matcher.group(1);
			String a = powerExpression.replaceAll("[" + var + "\\^]", "");
			String n = powerExpression.replaceAll("[" + var + "]", "");
			int exponent = Integer.parseInt(n);
			return exponent + "*" + a + "*" + var + "^" + (exponent - 1);

		}

		// Vérifier si l'expression est une addition
		pattern = Pattern.compile("(" + var + "\\+.+)|(.+\\+" + var + ")");
		matcher = pattern.matcher(expression);
		if (matcher.find()) {
			String[] terms = expression.split("\\+");
			StringBuilder result = new StringBuilder();
			for (String term : terms) {
				result.append("+" + derivative(var, term.trim()));
			}
			return result.toString().substring(1);
		}

		// Vérifier si l'expression est une multiplication
		pattern = Pattern.compile("(" + var + "\\*.+)|(.+\\*" + var + ")");
		matcher = pattern.matcher(expression);
		if (matcher.find()) {
			String[] factors = expression.split("\\*");
			StringBuilder result = new StringBuilder();
			for (String factor : factors) {
				result.append("+" + derivative(var, factor.trim()) + "*");
			}
			result.append(expression);
			return result.toString().substring(1);
		}

		// Si aucune des conditions ci-dessus n'est remplie, l'expression n'est pas
		// valide
		throw new IllegalArgumentException("L'expression n'est pas correcte");
	}

}
