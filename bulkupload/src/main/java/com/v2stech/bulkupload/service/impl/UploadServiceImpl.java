package com.v2stech.bulkupload.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v2stech.bulkupload.repository.UserRepository;
import com.v2stech.bulkupload.repository.UserTypeRepository;
import com.v2stech.bulkupload.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {

	private static final String SQL = ".sql";

	private static final String PATHNAME = "src/main/resources/sql/";

	private static final String ACTIVE = "Active";

	private static final String SEMI_COLON = ";";

	private static final String BRACES_CLOSE = ")";

	private static final char QUOTES = '"';

	private static final String COMMA = ",";

	private static final String BRACES_OPEN = "(";

	private static final String VALUES = " values ";

	private static final String USER_TABLE_WITH_COLUMN_NAME = "users (FORENAME,FAMILY_NAME,USERNAME,POSTCODE,EMAIL_ADDRESS,USER_TYPE_ID,USER_STATUS_CODE)";

	private static final String SITE_TYPE_TABLE_WITH_COLUMN_NAME = "site_types (SITE_TYPE_NAME)";

	private static final String REGION_TABLE_WITH_COLUMN_NAME = "region (REGION_NAME,REGION_MANAGER_ID)";

	private static final String INSERT = "Insert into ";

	private static final String FILE_PATH = File.separator + "home" + File.separator + "v2stech" + File.separator
			+ "Downloads" + File.separator;

	@Autowired
	UserTypeRepository userTypeRepository;

	@Autowired
	UserRepository userRepository;

	public Sheet readFile(String filePath) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(new File(filePath));
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		return workbook.getSheetAt(0);
	}

	@Override

	public StringBuilder uploadUserFile(String fileName, String table) throws IOException {
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
			query.append(userTypeRepository.findByTypeName(row.getCell(5).toString()).getId());
			query.append(COMMA);
			query.append(QUOTES);
			query.append(BRACES_CLOSE);
			if (row.getRowNum() == row.getSheet().getLastRowNum()) {
				query.append(SEMI_COLON);
			} else {
				query.append(COMMA);
			}
		}
		File file = new File(PATHNAME + table + SQL);
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(query.toString());
		}
		return query;
	}

	@Override
	public StringBuilder uploadSiteType(String fileName, String table) throws IOException {
		StringBuilder query = new StringBuilder();
		query.append(INSERT);
		query.append(SITE_TYPE_TABLE_WITH_COLUMN_NAME);
		query.append(VALUES);
		for (Row row : readFile(FILE_PATH + fileName)) {
			if (row.getRowNum() == 0) {
				continue;
			}
			query.append(BRACES_OPEN);
			query.append(QUOTES);
			query.append(row.getCell(0).toString());
			query.append(QUOTES);
			query.append(BRACES_CLOSE);
			if (row.getRowNum() == row.getSheet().getLastRowNum()) {
				query.append(SEMI_COLON);
			} else {
				query.append(COMMA);
			}
		}
		File file = new File(PATHNAME + table + SQL);
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(query.toString());
		}
		return query;
	}

	@Override
	public ByteArrayInputStream downloadFile(String table) throws IOException {
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			byte[] array = Files.readAllBytes(Paths.get(PATHNAME + table + SQL));
			return new ByteArrayInputStream(array);
		}
	}

	@Override
	public Object uploadRegionFile(String fileName, String table) throws IOException {
		StringBuilder query = new StringBuilder();
		query.append(INSERT);
		query.append(REGION_TABLE_WITH_COLUMN_NAME);
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
			query.append(userRepository.findByForenameAndFamilyName(row.getCell(1).toString().split("\\s+")[0],
					row.getCell(1).toString().split("\\s+")[1]).getUserId());
			query.append(BRACES_CLOSE);
			if (row.getRowNum() == row.getSheet().getLastRowNum()) {
				query.append(SEMI_COLON);
			} else {
				query.append(COMMA);
			}
		}
		File file = new File(PATHNAME + table + SQL);
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(query.toString());
		}
		return query;
	}
}
