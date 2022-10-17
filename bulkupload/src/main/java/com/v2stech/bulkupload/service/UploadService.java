package com.v2stech.bulkupload.service;

import java.io.IOException;

public interface UploadService {

	StringBuilder uploadFile(String fileName) throws IOException;

}
