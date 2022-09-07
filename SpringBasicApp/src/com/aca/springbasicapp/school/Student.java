package com.aca.springbasicapp.school;


//DI는 결합도를 약화시킬 수 있지만, AOP는 더 나아가 결합도 자체를 없애버릴 수 있다.
public class Student {
	//어플리케이션에서 전반적으로 사용되는 핵심 로직이 아닌 공통로직을 
	//별도로 빼주자!
	public void studyTime1() {
		System.out.println("국어를 공부해요.");
		
	}
	public void studyTime2() {
		System.out.println("수학을 공부해요.");
		
	}
	public void studyTime3() {
		
		System.out.println("영어를 공부해요.");
		
	}
	public void studyTime4() {
		System.out.println("과학을 공부해요.");
		
	}
	public void studyTime5() {
		System.out.println("체육을 공부해요.");
	}
	
}
