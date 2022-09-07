package com.aca.springbasicapp.cook;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aca.springbasicapp.gui.MyWin;
import com.aca.springbasicapp.school.Student;

public class AppMain {
	
	public static void main(String[] args) {
		/*
		Chef chef = new Chef();
		chef.cook();
		 */
		
		//요리사가 쓰고자 하는 객체를, 스피링이 고나여하여 직접 객체를 주입시켜주자(injection)
		//왜 주입하나 ? 요리사클래스에서 new하는 걸 피하기 위해 new 하는 순간 정확한 자료형 명시와 함께
		//결합도가 강해지므로
		
		//application에서 사용할 객체들을 스프링이 관리해 줄수 있는데, 이때 사용되는 스프링의 객체를 가ㅊ리켜
		//스피링 빈 컨테이너라 하면, 자료형은 applicationContext 이다.
		//앞으로 개발자가 프로그램에서 사용할 객체들은 자바 클래스에서 생성하지 말고, xml에 명시해 놓으면
		//스프링 컨테이너가 알아서 인스턴스를 생성하영 관리해준다.
		//이래의 applicationContext를 객체를 메모리에 올릴때, 지정한xml을 파싱하고, 그 xml에 명시된 모든 bean들은
		//개체의 인스턴스가 만들어져서 컨테이너에 의해 관리된다.
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app/config/context.xml");
//		Chef chef = (Chef) context.getBean("chef"); //새롭게 인스턴스를 생성하지 않고 이미 컨테이너가 생성된 빈을 얻어오기
//		chef.cook();
//		MyWin myWin = (MyWin) context.getBean("myWin");
//		myWin.init();
//		
		Student student = (Student) context.getBean("student");
		student.studyTime1();
		student.studyTime2();
		student.studyTime3();
		student.studyTime4();
		student.studyTime5();
	}
}
	
