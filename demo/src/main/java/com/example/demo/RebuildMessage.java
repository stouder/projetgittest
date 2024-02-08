package com.example.demo;

public class RebuildMessage {

	private static StringBuilder buildMessage(String[] tab, StringBuilder msg, char search, char end) {

		if (search == end) {
			return msg;
		}

		for (String chaine : tab) {
			if (chaine.charAt(0) == search) {
				if (chaine.charAt(0) == 'A') {
					msg.append(chaine);
				} else {
					msg.append(chaine.substring(1));
				}

				return buildMessage(tab, msg, chaine.charAt(chaine.length() - 1), end);
			}
		}

		return null;
	}

	public static String rebuildMessage(String[] parts) {
		StringBuilder msg = new StringBuilder();
		buildMessage(parts, msg, 'A', 'Z');

		return msg.toString();
	}

	public static void main(String[] args) {
		String[] parts = { "d---Z", "A---b", "b---d" };
		String message = rebuildMessage(parts);
		System.out.println(message);
	}

}
