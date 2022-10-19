package com.v2stech.bulkupload.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface UploadService {


	StringBuilder uploadSiteType(String fileName,String table) throws IOException;

	StringBuilder uploadUserFile(String fileName, String table) throws IOException;

	ByteArrayInputStream downloadFile(String table) throws IOException;

	Object uploadRegionFile(String fileName, String table) throws IOException;

}
