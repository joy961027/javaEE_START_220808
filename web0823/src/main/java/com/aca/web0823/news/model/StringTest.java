package com.aca.web0823.news.model;

public class StringTest {
	
	/*
	 * java언어의 string의 특징
	 * 1) immutable : 불변
	 */
	public static void main(String[] args) {
		String a = "tiger";
		String b = "tiger";
		String x = new String("korea");
		String y = new String("korea");
		System.out.println(a==b);
		System.out.println(x==y);
		
	}

}
