package javaseapp.util;
//key-value의 쌍으로 객체를 관리하는 Map의 유형 중, Properties를 사용해본다

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropTest {
	Properties props;//key-value로 이루어진 데이터를 이해하는 객체
	FileInputStream fis;
	
	public PropTest() {
		try {
			fis = new FileInputStream("D:/jsp_workspace/javaseapp/data/start.txt");
			props = new Properties();
			props.load(fis);
			//검색 (key)
			String value =props.getProperty("/movie.do");
			System.out.println("결과 값은 "+value);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	public static void main(String[] args) {
		new PropTest();
	}
}
