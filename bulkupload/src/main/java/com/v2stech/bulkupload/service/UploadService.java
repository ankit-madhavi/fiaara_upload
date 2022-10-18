package com.v2stech.bulkupload.service;

import java.io.IOException;
import java.io.InputStream;

public interface UploadService {

	StringBuilder uploadFile(String fileName) throws IOException;

	InputStream downloadFile(String query) throws IOException;

}
