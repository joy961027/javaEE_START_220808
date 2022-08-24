package com.aca.web0823.pattern;

public class UseDog {
	public static void main(String[] args) {
		Dog dog1 = Dog.getInstance();
		Dog dog2 = Dog.getInstance();
		
	
		System.out.println(dog1);
		System.out.println(dog2);
	}
}
