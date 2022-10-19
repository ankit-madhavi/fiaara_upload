package com.v2stech.bulkupload.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface UploadService {

	StringBuilder uploadFile(String fileName, String table) throws IOException;

	ByteArrayInputStream downloadFile(String table) throws IOException;

}
