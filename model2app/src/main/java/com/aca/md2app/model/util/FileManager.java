package com.aca.md2app.model.util;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.aca.md2app.domain.Gallery;

import lombok.Data;
@Data
//파일과 관련된 업무처리를 담당하는 유틸리티 클래스
public class FileManager {
	DiskFileItemFactory factory = new DiskFileItemFactory();
	int maxSize = 5 * 1024 * 1024;
	FileItem fileItem;
	private String dest;
	private String extension;
	
	// 파일을 지정한 경로에 지정하는 메서드
	public Gallery getParam(HttpServletRequest request) {
		// jsp의 application 내장객체이자 servletcontext 자료형인 객체를 request객체로부터 얻어올수있나 yes
		String savePath = request.getServletContext().getRealPath("/data");
		dest = savePath;
		// 서버어디에 ?? 용량제한은 얼마나? 설정정보가 필요함
		// 업로드 전 설정정보를 관리하는 객체
		factory.setSizeThreshold(maxSize);
		factory.setRepository(new File(savePath));

		// 업로드를 담당하는 객체
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		Gallery gallery = new Gallery();
		String ext = null;
		try {
			// 요청객체를 분석후, 텍스트와 파일을 FileItem에담는다
			// 우리의 경우 texr 3개 file1개 = 4개를 담는다
			List<FileItem> uploadList = servletFileUpload.parseRequest(request);
			System.out.println(uploadList.size());
			for (int i = 0; i < uploadList.size(); i++) {
				FileItem item = uploadList.get(i);
				if (item.isFormField()) {// text인경우
					System.out.println(item.getFieldName() + "의 값은" + item.getString());

					switch (item.getFieldName()) {
						case "title":gallery.setTitle(item.getString());break;
						case "writer":gallery.setWriter(item.getString());break;
						case "content":gallery.setContent(item.getString());break;
					}

				} else {// 바이너리 file인경우
					System.out.println("파일명은 " + item.getName());
					ext = FileManager.getExt(item.getName());
					extension = ext;
					fileItem = item;//멤버변수에 보관해야, 다른메서드에서 접근할수 있으니가
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return gallery;

	}

	//파일을 지정한 경로에 저장하기
	public void saveAs(String dest) {
		try {
			System.out.println(dest);
			fileItem.write(new File(dest));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dsd");
		}
	}
	
	// 파일명 추출
	

	// 파일확장자 추출
	public static String getExt(String path) {
		int index = path.lastIndexOf(".");
		return path.substring(index+1, path.length());
	}
	/*
	 * public static void main(String[] args) { System.out.println(
	 * getExt("d://dsafa/sdf/sd/fas/df/dfed.df.f.da.sss.df.txt")); }
	 */
}
