package com.homecafe.controller.upload;

import com.homecafe.controller.ApiResponse;
import com.homecafe.service.upload.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileUploadController {

	private final FileUploadService fileUploadService;

	@Operation(summary = "파일 업로드 API")
	@PostMapping("/api/v1/upload/image")
	public ApiResponse<String> uploadImage(@RequestPart MultipartFile file) {
		return ApiResponse.of(fileUploadService.uploadImage(file));
	}

}
