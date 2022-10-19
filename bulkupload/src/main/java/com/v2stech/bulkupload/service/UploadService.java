package com.v2stech.bulkupload.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface UploadService {

	StringBuilder uploadUserFile(String fileName,String table) throws IOException;

	ByteArrayInputStream downloadFile() throws IOException;

}
