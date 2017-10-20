package com.wdc.learnning.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ExcelCreateRead {

    private static final String file = "logs/excel-create.xlsx";

    public static void createExcel(String filePath) throws IOException {
        try(FileOutputStream fileOut = new FileOutputStream(filePath)) {
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet();
            Row row = null;
            Cell cell = null;

            CellStyle css1 = wb.createCellStyle();
            CellStyle css2 = wb.createCellStyle();
            CellStyle css3 = wb.createCellStyle();
            CellStyle css4 = wb.createCellStyle();
            CellStyle css5 = wb.createCellStyle();

            DataFormat dataFormat = wb.createDataFormat();

            Font font1 = wb.createFont();
            Font font2 = wb.createFont();

            font1.setFontHeightInPoints((short) 12);
            font1.setColor((short) 0xc);//12
            font1.setBold(true);

            font2.setFontHeightInPoints((short) 10);
            font2.setColor(Font.COLOR_RED);
            font2.setBold(true);

            css1.setFont(font1);
            css1.setDataFormat(dataFormat.getFormat("#,##0.0"));

            css2.setFont(font2);
            css2.setBorderBottom(BorderStyle.THIN);
            css2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            css2.setDataFormat(dataFormat.getFormat("text"));

            //日期
            css3.setDataFormat(dataFormat.getFormat("m/d/yy h:mm"));
            css4.setDataFormat(dataFormat.getFormat("yyyy-m-d AM/PM h:mm"));
            css5.setBorderBottom(BorderStyle.THICK);

            wb.setSheetName(0, "你好");
            int rowNum;
            for (rowNum = 0; rowNum < 10; rowNum++) {
                row = sheet.createRow(rowNum);

                if ((rowNum % 2) == 0) {
                    row.setHeight((short) 0x249);
                }

                for (int cellNum = 0; cellNum < 10; cellNum+=2) {
                    cell = row.createCell(cellNum);
                    cell.setCellValue(rowNum * 10000 + cellNum
                            + (((double) rowNum / 1000)
                            + ((double) cellNum / 10000)));
                    cell.setCellStyle(css1);

                    cell = row.createCell(cellNum + 1);

                    if ((rowNum % 2) == 0) {
                        cell.setCellStyle(css2);
                        cell.setCellValue("test");
                    } else {
                        cell.setCellStyle(css2);
                        cell.setCellValue("你好");
                    }

                    sheet.setColumnWidth((cellNum + 1), 500 * 8);
                }
            }
            rowNum++;

            row = sheet.createRow(rowNum);
            for (int i = 0; i < 15; i++) {
                cell = row.createCell(i);
                if ((i % 2) == 0) {
                    cell.setCellValue(new Date());
                    cell.setCellStyle(css3);
                } else if ((i % 3) == 0){
                    cell.setCellValue(new Date());
                    cell.setCellStyle(css4);
                } else if ((i % 5) == 0){
                    cell.setCellStyle(css5);
                } else {
                    cell.setCellValue(new Date());
                }
            }

            wb.write(fileOut);
        }


    }

    public static void readExcel(String filePath) throws IOException {
        try(XSSFWorkbook wb = new XSSFWorkbook(filePath)) {
            XSSFSheet sheet = null;
            XSSFRow row = null;
            XSSFCell cell = null;

            for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
                sheet = wb.getSheetAt(sheetNum);
                int rowStart = Math.min(15, sheet.getFirstRowNum());
                int rowEnd = Math.max(1400, sheet.getLastRowNum());
                for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                    row = sheet.getRow(rowNum);
                    if (row == null) continue;
                    int lastColumn = Math.max(row.getLastCellNum(), 5);
                    for (int colNum = 0; colNum < lastColumn; colNum++) {
                        cell = row.getCell(colNum);
                        if (cell == null) continue;
                        String value;
                        switch (cell.getCellTypeEnum()){
                            case FORMULA:
                                value = "FORMULA:" + cell.getCellFormula();
                                break;
                            case NUMERIC:
                                String dataFormatString = cell.getCellStyle().getDataFormatString();
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    value = "NUMERIC-" + dataFormatString + ":" + cell.getDateCellValue();
                                } else {
                                    value = "NUMERIC-" + dataFormatString + ":" + cell.getNumericCellValue();
                                }
                                break;
                            case STRING:
                                value = "STRING:" + cell.getStringCellValue();
                                break;
                            case BOOLEAN:
                                value = "BOOLEAN:" + cell.getBooleanCellValue();
                                break;
                            case BLANK:
                            case _NONE:
                                value = "BLANK or NONE";
                                break;
                            case ERROR:
                                value = "ERROR";
                                break;
                            default:
                                value = "UNKNOWN value of type " + cell.getCellTypeEnum();
                        }
                        System.out.println(cell.getColumnIndex() + ":\t" + value);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (!new File(file).exists()) {
            createExcel(file);
        }
        readExcel(file);
    }
}
