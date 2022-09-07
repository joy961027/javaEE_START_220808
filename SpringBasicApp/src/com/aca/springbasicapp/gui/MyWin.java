package com.aca.springbasicapp.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
//지금까지 작성했떤 gui 프로그래밍을 spring di를 적용하여 개발해본다.
import javax.swing.JTextArea;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
@Data
public class MyWin extends JFrame {
	//상위 자료형으로 선언한 이유? 결합도를 약화 시키기 위해
	private JComponent area; //주입받으려교 
	private JComponent t_input;
	private JComponent bt;
	
	
	public void init() {
		area.setPreferredSize(new Dimension(300,400));
		add(area);
		add(t_input);
		add(bt);
		//조립
		setLayout(new FlowLayout());
		
		setVisible(true);
		setSize(400,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
