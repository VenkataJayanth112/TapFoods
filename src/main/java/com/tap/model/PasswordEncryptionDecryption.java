package com.tap.model;

public class PasswordEncryptionDecryption {

	public static String encryption(String s) {
		String t="";
		
		for(int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			t+=(char)(c+1);
		}
		
		return t;
	}
	
	public static String decryption(String s) {
		String t="";
		
		
		for(int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			
			t+=(char)(c-1);
		}
		
		return t;
		
	}
	
}
