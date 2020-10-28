package com.myStore.tests;


import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;



public class ReadExcel {
	@BeforeClass
	public static void setup() {
		
	}
	
	@Test
	public void readSheet() throws IOException {
//		java.util.List<java.util.List<String>> mybook = ExcelHandling.readFromFile("Book1");
		String myexcel = ExcelHandling.readFile("C:\\Users\\psgan\\AutoBrowser\\BrowserTesting\\src\\test\\java\\com\\myStore\\tests\\Book1.xlsx");
		System.out.println(myexcel);
		ExcelHandling ex= new ExcelHandling();
		List<List<String>> myobj= ex.readFromFile("C:\\Users\\psgan\\AutoBrowser\\BrowserTesting\\src\\test\\java\\com\\myStore\\tests\\Book1.xlsx","Sheet1.xls",1);
		System.out.println(myobj);
//		for(List<String> ls: myobj) {
//			for(String rowv:ls) {
//				System.out.println(rowv);
//				
//			}
//		}
	}

	public ReadExcel() {
		// TODO Auto-generated constructor stub
	}

}
