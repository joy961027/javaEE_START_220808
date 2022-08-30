package com.academy.web0829.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.academy.web0829.board.repository.Member;

/*
 * xml의 모든 노드마다 이벤트를 발생시켜주는 이벤트 핸들러를 재정의 할려구함
 */
public class MyHandler extends DefaultHandler{
	List<Member> list;
	Member member ;
	boolean isName=false;//실행부가 name태그를 지나치고 잇는 여부를 판단해주는 논리값
	boolean isNation=false;//실행부가 nation태그를 지나치고 잇는 여부를 판단해주는 논리값
	public void startDocument() throws SAXException {
		System.out.println("문서가 시작되었어요");
	}
	
	public void endDocument() throws SAXException {
		System.out.println("문서의 끝이에요, 총회원 수는 " +list);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getName()+" "+ list.get(i).getNation());
		}
	}
	
	//시작태그를 만날때 호출되는 메서드
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		//System.out.print("<"+tag+">");
		if(tag.equals("members")) {
			list = new ArrayList<>();
		}else if(tag.equals("member")) {
			member = new Member();
		}else if(tag.equals("name")) {
			isName =true;
		}else if(tag.equals("nation")) {
			isNation = true;
		}
		
		
	}

	//태그 사이의 데이터를 만날때 호출되는 메서드
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length);
		//System.out.print(content);
		//지금 지나가는 실행부가
		if(isName) {
			member.setName(content);
		}else if(isNation) {
			member.setNation(content);
		}
	}
	
	//닫는 태그를 만날때 호출되는 메서드
	public void endElement(String uri, String localName, String tag) throws SAXException {
		//System.out.println("</"+tag+">");
		if(tag.equals("members")) {
			
		}else if(tag.equals("member")) {
			list.add(member);
		}else if(tag.equals("name")) {
			isName =false;
		}else if(tag.equals("nation")) {
			isNation = false;
		}
		
	}
	
	public List<Member> getList() {
		return list;
	}
}
