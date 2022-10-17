package com.v2stech.bulkupload.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.v2stech.bulkupload.service.UploadService;

@RestController
public class UploadController {
	
	@Autowired
	UploadService uploadService;
	
	
	@RequestMapping("/")
	public ModelAndView getWelcomePage() {
		return new ModelAndView("index");
	}
	
	@PostMapping("/read")
	public String uploadFile(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
		return uploadService.uploadFile(file.getOriginalFilename()).toString();
	}
	
}
