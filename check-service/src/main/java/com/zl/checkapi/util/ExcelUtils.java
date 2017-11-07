package com.zl.checkapi.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	/**
	 * 列名前缀
	 */
	public static String COLUMN_TAG = "COLUMN_";

	/**
	 * 读取excel，读取后的结果：Map<String, List<Map<String, String>>>
	 * sheet名，每个sheet中的行，每一行是COLUMN_0与值的对应关系，COLUMN_从0开始
	 *
	 * @param path
	 * @return Map<String, List<Map<String, String>>>
	 * @throws Exception
	 */
	public static Map<String, List<Map<String, String>>> readExcel(String path) throws Exception {
		//简单判断，根据后缀为xlsx或xls
		if (StringUtils.isBlank(path)) {
			return null;
		}

		if (path.contains("xlsx")) {
			return readXlsx(path);
		} else {
			return readXls(path);
		}
	}
    public static Map<String, List<Map<String, String>>> readExcel(InputStream in, String filename) throws Exception {
        //简单判断，根据后缀为xlsx或xls
        if (in == null || filename == null) {
            return null;
        }
        if (filename.contains("xlsx")) {
            return readXlsx(in);
        } else {
            return readXls(in);
        }
    }
    private static Map<String, List<Map<String, String>>> readXlsx(String path) throws Exception {
        InputStream in = new FileInputStream(path);
        return readXlsx(in);
    }
    private static Map<String, List<Map<String, String>>> readXlsx(InputStream in) throws Exception {
//		InputStream in = new FileInputStream(path);
        XSSFWorkbook workBook = new XSSFWorkbook(in);

		int sheetNumber = workBook.getNumberOfSheets();

		Map<String, List<Map<String, String>>> sheetMap = new HashMap<String, List<Map<String, String>>>();

		for (int i = 0; i < sheetNumber; i++) {
			XSSFSheet sheet = workBook.getSheetAt(i);

			if (sheet == null) {
				continue;
			}

			//记录单sheet所有row
			List<Map<String, String>> rowList = new ArrayList<Map<String, String>>();

			int rowNumber = sheet.getLastRowNum();
			for (int j = 0; j < rowNumber + 1; j++) {
				//读取行
				XSSFRow row = sheet.getRow(j);
				if (row == null) {
					continue;
				}
				
				Map<String, String> rowMap = new HashMap<String, String>();

				int rowColumnNumber = row.getLastCellNum();
				for (int k = 0; k < rowColumnNumber; k++) {
					XSSFCell cell = row.getCell(k);
					String cellValue = getCellValue(cell);
					if(StringUtils.isNotBlank(cellValue)){
						rowMap.put(COLUMN_TAG + k, cellValue.trim());
					}
				}
				
				if(!rowMap.isEmpty()){
					rowList.add(rowMap);
				}

			}

			sheetMap.put("Sheet"+(i+1), rowList);
		}

		return sheetMap;
	}
    
    private static String getCellValue(Cell cell){
    	
    	if(cell == null){
    		return null;
    	}
    	int type = cell.getCellType();
    	String value = null;
    	switch (type) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if(HSSFDateUtil.isCellDateFormatted(cell)){
				value = String.valueOf(cell.getDateCellValue().getTime());
			}else{
				value = new BigDecimal(cell.getNumericCellValue()).toPlainString();
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = String.valueOf(cell.getBooleanCellValue());
			break;
		default:
			break;
		}
    	
    	return value;
    }

    private static Map<String, List<Map<String, String>>> readXls(String path) throws Exception {
        InputStream in = new FileInputStream(path);
        return readXls(in);
    }

    private static Map<String, List<Map<String, String>>> readXls(InputStream in) throws Exception {
//		InputStream in = new FileInputStream(path);
		HSSFWorkbook workBook = new HSSFWorkbook(in);

		Map<String, List<Map<String, String>>> sheetMap = new HashMap<String, List<Map<String, String>>>();

		int sheetNumber = workBook.getNumberOfSheets();

		for (int i = 0; i < sheetNumber; i++) {
			HSSFSheet sheet = workBook.getSheetAt(i);

			if (sheet == null) {
				continue;
			}

			//记录单sheet所有row
			List<Map<String, String>> rowList = new ArrayList<Map<String, String>>();

			int rowNumber = sheet.getLastRowNum();
			for (int j = 1; j <= rowNumber; j++) {
				//读取行
				HSSFRow row = sheet.getRow(j);
				if (row == null) {
					continue;
				}
				
				Map<String, String> rowMap = new HashMap<String, String>();

				int rowColumnNumber = row.getLastCellNum();
				for (int k = 0; k < rowColumnNumber; k++) {
					HSSFCell cell = row.getCell(k);
					String cellValue = getCellValue(cell);
					if(StringUtils.isNotBlank(cellValue)){
						rowMap.put(COLUMN_TAG + k, cellValue.trim());
					}
				}

				if(!rowMap.isEmpty()){
					rowList.add(rowMap);
				}
			}

			sheetMap.put("Sheet"+(i+1), rowList);
		}

		return sheetMap;
	}

	/**
	 * 写入excel
	 * sheet名，每个sheet中的行，每一行是COLUMN_0与值的对应关系，COLUMN_从0开始
	 *
	 * @param map
	 * @param path
	 * @throws Exception
	 */
	public static void writeExcel(Map<String, List<Map<String, String>>> map, String path) throws Exception {
		//简单判断，根据后缀为xlsx或xls
		if (StringUtils.isBlank(path)) {
			return;
		}

		if (path.contains("xlsx")) {
			writeXlsx(map, path);
		} else {
			writeXls(map, path);
		}
	}

	private static void writeXlsx(Map<String, List<Map<String, String>>> map, String path) throws Exception {
		XSSFWorkbook workBook = new XSSFWorkbook();

		for (Entry<String, List<Map<String, String>>> entry : map.entrySet()) {
			XSSFSheet sheet = workBook.createSheet(entry.getKey());
			List<Map<String, String>> rows = entry.getValue();

			//写入内容
			for (int i = 0; i < rows.size(); i++) {
				XSSFRow rowObj = sheet.createRow(i);

				Map<String, String> row = rows.get(i);
				Set<String> columns = row.keySet();

				for (String column : columns) {
					int idx = Integer.parseInt(column.replace(COLUMN_TAG, ""));
                    if(i==0){
                    	sheet.autoSizeColumn(idx);
                    }
                    XSSFCell cell = rowObj.createCell(idx);
					cell.setCellValue(row.get(column));
				}
			}
		}

		OutputStream out = new FileOutputStream(path);
		workBook.write(out);
		out.close();
	}

	private static void writeXls(Map<String, List<Map<String, String>>> map, String path) throws Exception {
		HSSFWorkbook workBook = new HSSFWorkbook();

		for (Entry<String, List<Map<String, String>>> entry : map.entrySet()) {
			HSSFSheet sheet = workBook.createSheet(entry.getKey());

			List<Map<String, String>> rows = entry.getValue();
			for (int i = 0; i < rows.size(); i++) {
				HSSFRow rowObj = sheet.createRow(i);

				Map<String, String> row = rows.get(i);
				Set<String> columns = row.keySet();

				for (String column : columns) {
					int idx = Integer.parseInt(column.replace(COLUMN_TAG, ""));
                    if(i==0){
                    	sheet.autoSizeColumn(idx);
                    }
                    HSSFCell cell = rowObj.createCell(idx);
					cell.setCellValue(row.get(column));
				}
			}
		}

		OutputStream out = new FileOutputStream(path);
		workBook.write(out);
		out.close();
	}
	

}
