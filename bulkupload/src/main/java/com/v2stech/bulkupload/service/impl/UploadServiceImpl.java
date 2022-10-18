package com.v2stech.bulkupload.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v2stech.bulkupload.repository.UploadRepository;
import com.v2stech.bulkupload.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

	private static final String PATHNAME = "src/main/resources/sql/User"+new Date()+".sql";

	private static final String ACTIVE = "Active";

	private static final String SEMI_COLON = ";";

	private static final String BRACES_CLOSE = ")";

	private static final char QUOTES = '"';

	private static final String COMMA = ",";

	private static final String BRACES_OPEN = "(";

	private static final String VALUES = " values ";

	private static final String USER_TABLE_WITH_COLUMN_NAME = "users (FORENAME,FAMILY_NAME,USERNAME,POSTCODE,EMAIL_ADDRESS,USER_TYPE_ID,USER_STATUS_CODE)";

	private static final String INSERT = "Insert into ";

	private static final String FILE_PATH = File.separator + "home" + File.separator + "v2stech" + File.separator
			+ "Downloads" + File.separator;

	@Autowired
	UploadRepository uploadRepository;

	public Sheet readFile(String filePath) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(new File(filePath));
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		return workbook.getSheetAt(0);
	}

	@Override
	public StringBuilder uploadFile(String fileName) throws IOException {
		StringBuilder query = new StringBuilder();
		query.append(INSERT);
		query.append(USER_TABLE_WITH_COLUMN_NAME);
		query.append(VALUES);
		for (Row row : readFile(FILE_PATH + fileName)) {
			if (row.getRowNum() == 0) {
				continue;
			}
			query.append(BRACES_OPEN);
			query.append(QUOTES);
			query.append(row.getCell(0).toString());
			query.append(QUOTES);
			query.append(COMMA);
			query.append(QUOTES);
			query.append(row.getCell(1).toString());
			query.append(QUOTES);
			query.append(COMMA);
			query.append(QUOTES);
			query.append(row.getCell(2).toString());
			query.append(QUOTES);
			query.append(COMMA);
			query.append(QUOTES);
			query.append((int) row.getCell(3).getNumericCellValue());
			query.append(QUOTES);
			query.append(COMMA);
			query.append(QUOTES);
			query.append(row.getCell(4).toString());
			query.append(QUOTES);
			query.append(COMMA);
			query.append(getUserType(row.getCell(5).toString()));
			query.append(COMMA);
			query.append(QUOTES);
			query.append(ACTIVE);
			query.append(QUOTES);
			query.append(BRACES_CLOSE);
			if (row.getRowNum() == row.getSheet().getLastRowNum()) {
				query.append(SEMI_COLON);
			} else {
				query.append(COMMA);
			}
		}
		return query;
	}

	public Long getUserType(String typeName) {
		return uploadRepository.findByTypeName(typeName).getId();
	}

	@Override
	public ByteArrayInputStream downloadFile(String query) throws IOException {
		File file = new File(PATHNAME);
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(query);
		}
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			byte[] array = Files.readAllBytes(Paths.get(PATHNAME));
			file.deleteOnExit();
			return new ByteArrayInputStream(array);
		}
	}
}
