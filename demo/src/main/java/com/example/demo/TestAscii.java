package com.example.demo;

import org.apache.commons.lang3.StringUtils;

public class TestAscii {

	
	private static StringBuilder toBinary(String st) {
		
		StringBuilder binaryStringBuilder = new StringBuilder();

	    for (char c : st.toCharArray()) {
	            binaryStringBuilder.append(StringUtils.leftPad(Integer.toBinaryString(c), 7, '0'));
	        }
        
		StringBuilder sb = new StringBuilder();
		
		char[] tab = binaryStringBuilder.toString().toCharArray();
		char tempChar = tab[0];
		
		if(tempChar == '1') {
			sb.append("0 ");
		}else {
			sb.append("00 ");
		}
		
		int index = 0;
		
		for(char ch:binaryStringBuilder.toString().toCharArray()) {
			if(tempChar == ch) {
				sb.append("0");
			}else {
				if(index != binaryStringBuilder.toString().toCharArray().length -1) {
					sb.append(" ");
				}
				
				if(ch == '1') {
					sb.append("0 0");
				}else{
					sb.append("00 0");
				}
				
				tempChar = ch;
			}
			
		}
		
		
		return sb; 
	}

	public static void main(String[] args) {
		String st = "%";
		System.out.println(toBinary(st)); // 00 0 0 0 00 00 0 0 00 0 0 0
	}

}
