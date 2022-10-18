package com.v2stech.bulkupload.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.v2stech.bulkupload.service.UploadService;

@RestController
public class UploadController {
	
	private static final String SQL = ".sql";
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
	
	@GetMapping("/download/{query}")
	public ResponseEntity<Resource> dowload(@PathVariable String query) throws IOException{
		InputStreamResource inputStreamResource = new InputStreamResource(uploadService.downloadFile(query));
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename= User"+new Date()+SQL)
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(inputStreamResource);
	}
	
}
