package com.Selenium.FileHandling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
    	//WebDriver driver = new ChromeDriver();        // directly upload and access the data in excel.
        //System.out.println("Hello World!");
        //driver.get("URL");    //In the URL ,give you url link 
        String excelFilePath = "C:\\Users\\_Duffer_\\Documents\\TestData.xlsx"; //replace your excel sheet path
        try {
        	FileInputStream fis=  new FileInputStream(new File(excelFilePath));
        	//new File(excelFilePath) - creates a File Object that points to the Excel File 
        	//FileInputStream()  create stream ,read the raw bite from the file 
        	
        	Workbook workbook = new XSSFWorkbook(fis);
        	//workbook -interface in apache POI = entire Excel work book
        	//passing file stream to the XSSFWorkbook Constructor, to pase the Excel File
        	
        	Sheet sheet= workbook.getSheetAt(0);
        	
        	//workbook.getSheetAt(0); -> get first sheet from the Excel 
        	
        	/*
        	Excel File (.xlsx)  -->  FileInputStream  -->  Workbook (XSSFWorkbook)  -->  Sheet (getSheetAt(0))
            |                       |                    |                              |
          Your File           Open in Java          Parse File                Pick First Sheet

             */   
        	
        	int rowCount = 0;
        	System.out.println("Reading first 10 rows of numeric data");
        	
        	
        	//In the sheet all rows are passed here in the loop
        	for(Row row : sheet) {
        		if(rowCount == 0) {
        			//To skip the Header in the Sheet 
        			rowCount++;
        			continue;
        		}
        		if (rowCount > 10) break;   // The sheet have 10 row of data , so if rowCount goes more than 10 ,The loop will break;
        		
        		Cell idCell = row.getCell(0); //cell is the class ,with its method we can access the cell data
        		Cell nameCell = row.getCell(1);
        		Cell value1Cell =row.getCell(2);
        		Cell value2Cell =row.getCell(3);
        		
        		int id =(int) idCell.getNumericCellValue();  
        		 //if your Excel cell has a value like 5, Java will treat it as 5.0 (which is a double),
        		        //--because Excel doesn't store strict types like int or float.
        		//But Usually Excel data accessed as double by Java  
        		String name= nameCell.getStringCellValue();
        		double val1 =value1Cell.getNumericCellValue();
        		double val2  =value2Cell.getNumericCellValue();
        		
        		double  sum = val1+ val2;
        		double avg=  sum/2;
        		double diff=val1 - val2;
        		double  prod = val1 * val2;
        		
        		System.out.println("Row #"+rowCount);
        		System.out.println("ID: "+id+",Name: "+ name);
        		System.out.println("value1: "+val1+",Value2: "+val2);
        		System.out.println("Sum: "+sum+",Avg: "+ avg + ", Diff: " +diff +",Product "+ prod);
        		System.out.println("----------");
        		rowCount++;
        		
        	}
        	workbook.close();    //Need to CLOSE  the Woork Book
        	fis.close();
        }catch (Exception e) {
        	e.printStackTrace();
        	
        }
    }
}
