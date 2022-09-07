package com.aca.springbasicapp.cook;

public class ElecPan implements Pan{

	@Override
	public void boil() {
		System.out.println("전기로 음식을 데워요.");
	
	}
}

