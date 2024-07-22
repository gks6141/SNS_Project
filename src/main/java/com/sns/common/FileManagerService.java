package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;



@Component
public class FileManagerService {

	//저장 될 서버 경로
	public static final String FILE_UPLOAD_PATH = "D:\\한주형\\6_spring_project\\sns\\sns_workspace\\images/";
	
	//집에서 사용되는 경로
//	public static final String FILE_UPLOAD_PATH = "C:\\한주형2\\6_project\\sns\\workspace\\images/";
	
	//input : file, userLoginId
	//output: String
	
	public String uploadFile(MultipartFile file, String loginId){
		String directoryName= loginId + "_" + System.currentTimeMillis();
		
		String filePath = FILE_UPLOAD_PATH + directoryName + "/";
		
		File directory = new File(filePath);
		
		if(directory.mkdir() == false) {
			return null;
		}
		
		// 업로드 (바이너리값)
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return "/images/"+ directoryName +"/" + file.getOriginalFilename();
	}
}
