package com.aca.web0823.pattern;
//싱글톤패턴
public class Dog {
	private static Dog instance;
	private String name ="동구";
	
	
	//생성자 호출 막음 생성자 호출외의 다른 방법으로 인스턴스를 얻어가게 해야함
	private Dog() {
	}
	public static Dog getInstance() {
		if(instance==null) {
			instance = new Dog();
		}
		return instance;
	}
	
}
