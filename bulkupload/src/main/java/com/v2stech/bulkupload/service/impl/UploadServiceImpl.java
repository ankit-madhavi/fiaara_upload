package com.v2stech.bulkupload.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.v2stech.bulkupload.service.UploadService;

public class UploadServiceImpl implements UploadService{

	private static final String SEMI_COLON = ";";

	private static final String BRACES_CLOSE = ")";

	private static final char QUOTES = '"';

	private static final String COMMA = ",";

	private static final String BRACES_OPEN = "(";

	private static final String VALUES = " values ";

	private static final String USER_TABLE_WITH_COLUMN_NAME = "user (fore_name,family_name,username,pincode,email,user_type,role)";

	private static final String INSERT = "Insert into ";
	
	private static final String FILE_PATH = File.separator + "home" + File.separator + "v2stech" + File.separator
			+ "Downloads" + File.separator;

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
			query.append((int) row.getCell(3).getNumericCellValue());
			query.append(COMMA);
			query.append(QUOTES);
			query.append(row.getCell(4).toString());
			query.append(QUOTES);
			query.append(COMMA);
			query.append(QUOTES);
			query.append(row.getCell(5).toString());
			query.append(QUOTES);
			query.append(COMMA);
			query.append(QUOTES);
			query.append(row.getCell(6).toString());
			query.append(QUOTES);
			query.append(BRACES_CLOSE);
			if(row.getRowNum() == row.getSheet().getLastRowNum()) {
				query.append(SEMI_COLON);
			} else {
				query.append(COMMA);
			}
		}
		return query;
	}
}
