package com.framework.utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelSheet {
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	
	public ReadExcelSheet(String path) {

		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			int number = sheet.getPhysicalNumberOfRows();
			return number;
		}
	}
	
	
	
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				try {
				if (row.getCell(i).getStringCellValue()	!= null)	{						
					if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
						col_Num = i;
						break;
					}
				}
				}
				
				catch (NullPointerException e) {
					continue;
				}
			}
			
			if (col_Num == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);

			if (cell == null)
				return "";
			
			String cellText = "";
			switch (cell.getCellTypeEnum()) {
			 	case BLANK :
                 //To-do
				 cellText = "";
                 break;
                 
			   case NUMERIC:
				    cellText = String.valueOf(cell.getNumericCellValue());
				    break;
			
			   case STRING:
				   cellText =  cell.getStringCellValue();
				   break;
			
			   default:
			}
			  
			return cellText;

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colName
					+ " does not exist in xlsx";
		}
	}

	
	
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			String cellText = "";
			switch (cell.getCellTypeEnum()) {
			 	case BLANK :
                 //To-do
				 cellText = "";
                 break;
                 
			   case NUMERIC:
				    cellText = String.valueOf(cell.getNumericCellValue());
				    break;
			
			   case STRING:
				   cellText =  cell.getStringCellValue();
				   break;
			
			   default:
			}
			  
			return cellText;
			
		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum
					+ " does not exist  in xlsx ";
		}
	}

	
	/**
	 * @author 
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * @param data
	 * @return Boolean value for data Set in a cell or not
	 */
	public boolean setCellData(String sheetName, String colName, int rowNum,
			String data) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			
			for (int i = 0; i < row.getLastCellNum(); i++) {
				try {
					
					System.out.println(row.getCell(i).getStringCellValue());
						if (row.getCell(i).getStringCellValue()	!= null)	{						
						if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
							{
							colNum = i;
							System.out.println(colNum);
							break;
							}
						}
				}
				
				catch (NullPointerException e) {
					continue;
				}
			}
			
			if (colNum == -1)
				return false;

			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			// cell style
			// CellStyle cs = workbook.createCellStyle();
			// cs.setWrapText(true);
			// cell.setCellStyle(cs);
			cell.setCellValue(data);

			fileOut = new FileOutputStream(path);

			workbook.write(fileOut);

			fileOut.close();

		 }catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

/**
 * @author 
 * @param sheetName
 * @return Whether Sheet Exist or not
 */
	public boolean isSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	
	
	/**
	 * @author 
	 * @param sheetName
	 * @return Total number of columns in a sheet
	 */
	public int getColumnCount(String sheetName) {
		
		
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();
	}
	
	/*public static void main(String[] args) {
		
		ReadExcelSheet excel = new ReadExcelSheet("D:\\ExtentReports\\SeleniumFramework\\src\\main\\resources\\TestData.xlsx");
		System.out.println(excel.getCellData("Sheet1",2,14));
	}*/

}