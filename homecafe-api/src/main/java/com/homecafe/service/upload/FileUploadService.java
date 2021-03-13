package com.homecafe.service.upload;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.homecafe.exception.ValidationException;
import com.homecafe.external.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileUploadService {

	private final S3Service s3Service;

	public String uploadImage(MultipartFile file) {
		String fileName = createFileName(file.getOriginalFilename());

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(file.getContentType());

		try (InputStream inputStream = file.getInputStream()) {
			s3Service.uploadFile(inputStream, objectMetadata, fileName);
		} catch (IOException e) {
			throw new ValidationException(String.format("파일 변환 중 에러가 발생하였습니다 (%s)", file.getOriginalFilename()), "잘못된 형식의 파일입니다.");
		}
		return s3Service.getFileUrl(fileName);
	}

	private String createFileName(String originalFileName) {
		return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
	}

	private String getFileExtension(String fileName) {
		try {
			return fileName.substring(fileName.lastIndexOf("."));
		} catch (StringIndexOutOfBoundsException e) {
			throw new ValidationException(String.format("잘못된 형식의 파일 (%s) 입니다", fileName), "잘못된 형식의 파일입니다.");
		}
	}

}