package com.v2stech.bulkupload.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.v2stech.bulkupload.service.UploadService;

public class UploadController {
	
	@Autowired
	UploadService uploadService;

	@PostMapping("/read")
	public String uploadFile(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
		return uploadService.uploadFile(file.getOriginalFilename()).toString();
	}
	
}
