package com.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	// 이미지 파일 저장 

		
	// 멤버 변수로 밖으로 빼기
	public static final String FILE_UPLOAD_PATH = "C:\\Users\\User\\Desktop\\개발\\Spring\\Project\\image file\\memo\\upload/";  
				// 파일을 어디에 저장할지 폴더 경로 설정, 경로 맨 뒤에 슬래시 붙여주기 ★★
				// static : 객체 생성 없이 가능
	
	
	// 파일을 저장하고 외부에서 접근 가능한 경로로 리턴하는 기능
	public static String saveFile(int userId, MultipartFile file) {		
		// 파일을 어디에 저장할지 폴더 경로 설정
		
		
		// 예시) C:\\Users\\User\\Desktop\\개발\\Spring\\Project\\upload/3_48551451742/asdf.jpg
				// (위의 예시에서) 언더바 : user가 같은 사진을 올렸을 경우 업로드한 시간으로 구분하기 위해서 사용
		// 디렉토리(폴더)를 새로 만든다
		// 디렉토리 이름 규칙
		// 사용자 구분  => userId
		// 시간 정보 - UNIX 타임 : 1970년 1월 1일을 기준으로 몇 millisecond(1/1000 초)가 지났는지를 표현 
		
		
			// /3_48551451742
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		
		
		
		// 디렉토리 생성
		String filePath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(filePath);	// java.io.File 로 임포트
			
		if (directory.mkdir() == false) {
				
			// 디렉토리 생성 실패했을 경우
			return null;
		}
		
		
		
		// 파일 저장
		try {
			byte[] bytes = file.getBytes();
				
				// filePath + file.getOriginalFilename()
				// 윗줄의 코드로 인해서 아래의 예시와 같은 주소(?)가 만들어짐
				// 예시) C/:\\Users\\User\\Desktop\\개발\\Spring\\Project\\upload/3_48551451742/asdf.jpg
			
			Path path = Paths.get(filePath + file.getOriginalFilename());	// java.nio.file.Path 로 임포트 (인터페이스) 
			Files.write(path, bytes);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			// 파일 처리 예외 상황
			return null;
		}
		
		
		
		// 외부에서 접근 가능한 경로 리턴 
		// 규칙 정하기  =>  /images/3_48551451742/asdf.jpg
			// <img src="/images/3_48551451742/asdf.jpg">  이렇게 사용 가능
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}
	
	
	
	// 이미지 파일 삭제  => 파일 삭제가 문제가 됐는지 확인하기 위헤 boolean 사용
	public static boolean removeFile(String filePath) {	// /images/3_1241241/text.png
		
		if(filePath == null) {
			
			return false;
		}
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images/", "");
		
		Path path = Paths.get(realFilePath);
		
		// 파일이 있는지 확인 (파일이 있을 경우에만 삭제)
		if(Files.exists(path)) {
			
			try {
				Files.delete(path);
			} catch (IOException e) {
				
				e.printStackTrace();
				
				return false;
			}
			
		}
		
			// 파일 => C/:\\Users\\User\\Desktop\\개발\\Spring\\Project\\upload/3_48551451742/asdf.jpg
		
			// 폴더 지우려면 => C/:\\Users\\User\\Desktop\\개발\\Spring\\Project\\upload/3_48551451742
		
		path = path.getParent();
		
		// 디렉토리가 존재하는지 확인
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				
				e.printStackTrace();
				return false;	// 문제가 됐으면 false로 return
			}
			
		}
		
		return true;	// 문제가 없으면 true로 return
		
	}
	
}
