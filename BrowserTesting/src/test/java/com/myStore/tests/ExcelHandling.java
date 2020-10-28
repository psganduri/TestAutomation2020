package com.myStore.tests;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class ExcelHandling extends FileHandler {

    /*******************************
     * read and write on Excel file with the help of Java IO package and Apache POI library.
     <dependency>
     <groupId>org.apache.poi</groupId>
     <artifactId>poi</artifactId>
     <version>3.12</version>
     </dependency>
     ********************************/
    public List<List<String>> readFromFile(String fileName, String sheetName,
                                           int headerRow1) {

        int headerRow = headerRow1 - 1;
        List<List<String>> table = new ArrayList<List<String>>();

        int colsNum = 0;

        try {
            FileInputStream file = new FileInputStream(new File(fileName));

            // workbook
            Workbook workbook;
            Sheet sheet;

            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file);
                sheet = workbook.getSheet(sheetName);
            } else {
                workbook = new XSSFWorkbook(file);
                sheet = workbook.getSheet(sheetName);
            }

            Iterator<Row> rowIterator = sheet.rowIterator();// iterator();

            int i = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // get number of columns from row 2 - header
                i++;
                if (i == headerRow)
                    colsNum = row.getLastCellNum();

                List<String> rowData = new ArrayList<String>();
                int lastColumn = Math.max(row.getLastCellNum(), colsNum);

                for (int cn = 0; cn < lastColumn; cn++) {
                    Cell c = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
                    if (c == null) {
                        rowData.add("");
                    } else {
                        rowData.add(handleCell(c.getCellType(), c));
                    }
                }
                table.add(rowData);
            }
            workbook.close();
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return table;
    }

    public List<List<String>> readFromFile(String fileName) {
        return readFromFile(fileName, "sheet1", 1);
    }

    public int getSheetCount(String fileName) {
        try {
            FileInputStream file = new FileInputStream(new File(fileName));
            Workbook workbook;

            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file);
            } else {
                workbook = new XSSFWorkbook(file);
            }

            return workbook.getNumberOfSheets();

        } catch (Exception e) {
            return 0;
        }
    }


    public static String handleCell(int type, Cell cell) {
        String s;
        BigDecimal big;
        switch (type) {
            case Cell.CELL_TYPE_BOOLEAN:
                s = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                big = new BigDecimal(cell.getNumericCellValue());
                s = String.valueOf(big);
                break;
            case Cell.CELL_TYPE_BLANK:
                s = "";
                break;
            case Cell.CELL_TYPE_ERROR:
                s = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                s = handleCell(cell.getCachedFormulaResultType(), cell);

                break;
            case Cell.CELL_TYPE_STRING:
                // s = cell.getStringCellValue().trim();
                s = cell.getStringCellValue();
                break;
            default:
                s = "";
        }
        return s;
    }

    public synchronized void writeToFile(String fileName, int sheetIndex1,
                                         String task, LinkedHashMap<String, Object> map, int starter1) {

        int sheetIndex = sheetIndex1 - 1;
        int starter = starter1 - 1;

        FileInputStream file;
        int row = 1;
        try {
            file = new FileInputStream(new File(fileName));
            Workbook workbook;
            Sheet sheet;

            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file);
                sheet = workbook.getSheetAt(sheetIndex); // from 0
            } else {
                workbook = new XSSFWorkbook(file); // org.apache.poi.openxml4j.exceptions.InvalidFormatException:
                sheet = workbook.getSheetAt(sheetIndex); // from 0
            }

            Cell cell = null;
            cell = sheet.getRow(row).getCell(starter++);

            cell.setCellValue(map.get("COLUMN_NEW_RESULT")
                    .toString());

            cell = sheet.getRow(row).getCell(starter++);
            cell.setCellValue(map.get("COLUMN_COMMENT").toString());

            cell = sheet.getRow(row).getCell(starter++);
            cell.setCellValue(map.get("COLUMN_EXECUTION_TIMESTAMP").toString());

            cell = sheet.getRow(row).getCell(starter++);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            if (!map.get("Client_First_Name").equals("")) {
                cell.setCellValue(map.get("Client_First_Name").toString());
            }

            cell = sheet.getRow(row).getCell(starter++);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            if (!map.get("COLUMN_APPLICATION_NO").equals("")) {
                cell.setCellValue(map.get("COLUMN_APPLICATION_NO").toString());
            }

            cell = sheet.getRow(row).getCell(starter++);
            if (!map.get("COLUMN_PROPOSAL_NO").equals("")) {
                cell.setCellValue(map.get("COLUMN_PROPOSAL_NO").toString());
            }


            cell = sheet.getRow(row).getCell(starter++);
            cell.setCellType(Cell.CELL_TYPE_STRING);

            if (map.get("Email") != null) {
                cell.setCellValue(map.get("Email").toString());
            }

            FileOutputStream updateFile = new FileOutputStream(new File(
                    fileName));
            workbook.write(updateFile);
            updateFile.close();
            workbook.close();
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public synchronized void writeToFile4PolicyNotice1(String fileName,
                                                       ArrayList<String> activePolicyList) {

        if (activePolicyList.isEmpty())
            return;

        FileInputStream file;
        try {
            file = new FileInputStream(new File(fileName));

            // workbook
            XSSFWorkbook workbook;
            XSSFSheet sheet;
            XSSFRow row;

            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheetAt(1); // from 0

            int rowNum = 1;
            int colNum = 0;

            for (String policyNumber : activePolicyList) {

                rowNum = sheet.getLastRowNum();
                colNum = 0;
                row = sheet.createRow(rowNum + 1);

                row.createCell(colNum++).setCellValue(policyNumber);
            }

            FileOutputStream updateFile = new FileOutputStream(new File(
                    fileName));
            workbook.write(updateFile);
            updateFile.close();
            workbook.close();
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> getRowNumbersNotHidden(String fileName, String sheetName, int dataRowStart) {
        ArrayList<Integer> exeRowNum = null;
        FileInputStream fis = null;
        XSSFWorkbook wb = null;
        XSSFSheet ws = null;

        try {
            fis = new FileInputStream(new File(fileName));
            wb = new XSSFWorkbook(fis);
            ws = wb.getSheet(sheetName);
            CTRow[] sData = ws.getCTWorksheet().getSheetData().getRowArray();
            int totalNumOfRowsInSheet = sData.length;
            System.out.println("sheetName to check for rows to run total num of rows ( hidden or not hidden)"
                    + totalNumOfRowsInSheet);

            exeRowNum = new ArrayList<Integer>();

            for (CTRow row : sData) {
                // skip header. excel starts count at 1 not 0.
                int rowNum = (int) row.getR();

                System.out.println("rowNum" + rowNum);
                if (rowNum < (dataRowStart)) {
                    continue;
                }

                if (!row.isSetHidden()) {

                    exeRowNum.add(rowNum);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            exeRowNum = null;
            return exeRowNum;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return exeRowNum;

    }

    public static LinkedHashMap<String, String[]> getColumnData(String fPath, String sheetName, List<Integer> rowNos,
                                                                int colHeader, int colData) throws Exception {

        File file = new File(fPath);
        OPCPackage fis = OPCPackage.open(file);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet ws = wb.getSheet(sheetName);
        LinkedHashMap<String, String[]> sheetInfo = new LinkedHashMap<String, String[]>();
        sheetInfo = new LinkedHashMap<String, String[]>();

        for (int i = 0; i <= rowNos.size() - 1; i++) {
            XSSFRow row0 = ws.getRow(rowNos.get(i) - 1);
            Iterator<Cell> cellIterator;
            cellIterator = row0.iterator();
            ArrayList<String> keys = new ArrayList<String>();
            int j = colHeader;
            String key = null;
            ArrayList<String> value = new ArrayList<String>();
            int z = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.println("[" + cell.getStringCellValue().trim() + "] ");
                if (!cell.getStringCellValue().equals("") && cell.getStringCellValue() != null) {

                    if (j == colHeader) {
                        key = cell.getStringCellValue().trim();
                        Assert.assertFalse("duplicate key cannot be added" + key, sheetInfo.containsKey(key));
                        keys.add(key);
                    }
                    if (j > colHeader) {
                        value.add(cell.getStringCellValue().trim());
                        z++;
                    }
                    j++;
                }
            }
            String[] dialogueText = new String[value.size()];
            dialogueText = value.toArray(dialogueText);
            sheetInfo.put(key, (String[])dialogueText); // put in key with no value
        }
        return sheetInfo;
    }
}






