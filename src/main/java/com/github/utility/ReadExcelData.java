package com.github.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	public static void main(String[] args) {
		String scenarioName = "SelectPostpaidPlanABCD";
		// readData(scenarioName);
		String status = "FAIL";
		setTestRunStatus( scenarioName , status );
	}

	public static void setTestRunStatus(String scenarioName, String status){
		try {
			FileInputStream fis = new FileInputStream(new File("resources/testData.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet("HLS");
			int rowsCount = sh.getPhysicalNumberOfRows();
			int getTheRowNumberOfRequiredScenarioToChangeTheStatus = 0;
			int getColumnNumberOfStatus = 0;
			int columnCount = 0;

			/**
			 * For fetching row index 
			 */
			for(int i = 0; i < rowsCount; i++){
				Row row = sh.getRow(i);
				columnCount = row.getPhysicalNumberOfCells();
				for(int j = 0; j < columnCount; j++ ){
					Cell c = row.getCell(j);
					try {
						CellType type = c.getCellTypeEnum();
						if (type == CellType.STRING) {
							if(((Object)c).toString().equals(scenarioName)){
								getTheRowNumberOfRequiredScenarioToChangeTheStatus = i;
								break;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if(getTheRowNumberOfRequiredScenarioToChangeTheStatus > 0){
					break;
				}
			}

			/**
			 * for fetching column where status needs to be updated 
			 */
			for(int i = 0; i < columnCount; i++ ){
				Cell c = sh.getRow(getTheRowNumberOfRequiredScenarioToChangeTheStatus -1).getCell(i);
				CellType type = c.getCellTypeEnum();
				if(type == CellType.STRING){
					if(((Object)c).toString().equals("STATUS")){
						getColumnNumberOfStatus = i;
						break;
					}
				}
			}

			sh.getRow(getTheRowNumberOfRequiredScenarioToChangeTheStatus).createCell(getColumnNumberOfStatus).setCellValue(status);

			FileOutputStream fos =  new FileOutputStream(new File("resources/testData.xlsx"));
			wb.write(fos);
			wb.close();
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static HashMap<String, String> readData(String scenarioName) {
		HashMap<String, String> map = new HashMap<>();
		try{
			FileInputStream fis = new FileInputStream(new File("resources/testData.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet("HLS");
			int rowsCount = sh.getPhysicalNumberOfRows();
			int rowNumOfRequiredScenario = 0;
			int columnCount = 0;

			Sarita_Reads_Row:
				for(int i = 0; i < rowsCount; i++){
					Row row = sh.getRow(i);
					columnCount = row.getPhysicalNumberOfCells(); // return no of columns

					Sarita_Reads_Column:
						for(int j = 0; j <  columnCount; j++){
							Cell c = row.getCell(j);
							CellType type = c.getCellTypeEnum();
							if (type == CellType.STRING){
								if(((Object)c).toString().equals(scenarioName)){
									rowNumOfRequiredScenario = i ; // here 'i' indicates the iteration on rows from the outer 'for' loop
									break Sarita_Reads_Row;
								}
							}
						}
				}

			/**
			 *  here we will again read the rows/columns to read only that row which we got at index 'rowNumOfRequiredScenario'
			 */
			int reqColumnCount = sh.getRow(rowNumOfRequiredScenario).getPhysicalNumberOfCells();
			for(int j = 0 ; j < reqColumnCount ; j++){
				String myKey = sh.getRow(rowNumOfRequiredScenario - 1).getCell(j).getStringCellValue();
				Cell c = sh.getRow(rowNumOfRequiredScenario).getCell(j);
				String myValue = "";
				try {
					if(1 == c.getCellType()){
						myValue = sh.getRow(rowNumOfRequiredScenario).getCell(j).getStringCellValue();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				map.put(myKey, myValue);
			}
			wb.close();
			fis.close();
		}catch(Exception eererer){
			eererer.printStackTrace();
		}finally{
			System.out.println("it will run anyhow");
		}
		return map;
	}



	private static void readData111111(String scenarioName) {
		try{
			FileInputStream fis = new FileInputStream(new File("resources/testData.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet("HLS");
			int rowsCount = sh.getPhysicalNumberOfRows();
			int rowNumOfRequiredScenario = 0;
			int columnCount = 0;

			for(int i = 0; i < rowsCount; i++){
				Row row = sh.getRow(i);
				columnCount = row.getPhysicalNumberOfCells(); // return no of columns
				for(int j = 0; j < columnCount; j++){
					Cell c = row.getCell(j);
					if(((Object)c).toString().equals(scenarioName)){
						rowNumOfRequiredScenario = i ; // here 'i' indicates the iteration on rows from the outer 'for' loop
						break;
					}
				}
			}

			// here we will again read the rows/columns to read only that row which we got at index 'rowNumOfRequiredScenario'
			for(int j = 0 ; j < columnCount ; j++){
				Cell c = sh.getRow(rowNumOfRequiredScenario).getCell(j);
				System.out.println(c);
			}

			wb.close();
			fis.close();

			System.out.println("helhloe");




		}catch(Exception eererer){
			eererer.printStackTrace();
		}finally{
			System.out.println("it will run anyhow");
		}
	}


	private static void readData() {
		try{
			FileInputStream fis = new FileInputStream(new File("resources/testData.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet("HLS");
			List <Object> lst = new ArrayList <>();
			int rowsCount = sh.getPhysicalNumberOfRows();
			for(int i = 2; i < rowsCount; i++){
				Row row = sh.getRow(i);
				int columnCount = row.getPhysicalNumberOfCells(); // return no of columns
				for(int j =0; j <columnCount; j++){
					Cell  c = row.getCell(j);
					lst.add(c);
				}
			}
			System.out.println(lst.get(3));

			wb.close();
			fis.close();

			System.out.println("helhloe");




		}catch(Exception eererer){
			eererer.printStackTrace();
		}finally{
			System.out.println("it will run anyhow");
		}
	}


}
