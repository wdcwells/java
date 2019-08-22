package com.wdc.study.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * title: <br>
 * description: 描述<br>
 * Copyright: Copyright (c)2014<br>
 * Company: 易宝支付(YeePay)<br>
 *
 * @author wdc
 * @version 1.0.0
 * @since 2019-08-17 22:15
 */
public class ExcelPoiUtil {

    public static void main(String[] args) throws Exception {
        printErrorCode();
//        printWqh();
    }

    private static void printErrorCode() throws Exception {
        final File importFile = new File("/Users/wdc/Documents/tests/tmp/opr交易错误码.xlsx");
        final List<Line> lines = Lists.newArrayList();

        try (Workbook wb = WorkbookFactory.create(importFile)) {
            final WorkBookParseConfig parseConfig = new WorkBookParseConfig();
            parseConfig.setDataRowStart(1);
            parseConfig.setHeaders(new String[]{"type", "apiGroup", "apiUri", "errorCode", "subErrorCode", "subErrorMsg", "solution"});
            parseConfig.setLineIdPrefix("题目");
            lines.addAll(parseWorkBook(wb, parseConfig));
        }
        System.out.println("总数量：" + lines.size());
        final Map<String, ErrorCode> map = Maps.newHashMap();
        int i = 0, j = 0;
        for (; i < lines.size(); i++) {
            final Line line = lines.get(i);
            if (null != line && CollectionUtils.isNotEmpty(line.getColumns())) {
                final ErrorCode errorCode = new ErrorCode();
                line.getColumns().forEach(column -> {
                    switch (column.getColumnName()) {
                        case "type":
                            errorCode.setType(column.getColumnValue().toString().trim());
                            break;
                        case "apiGroup":
                            errorCode.setApiGroup(column.getColumnValue().toString().trim());
                            break;
                        case "apiUri":
                            errorCode.setApiUri(column.getColumnValue().toString().trim());
                            break;
                        case "errorCode":
                            errorCode.setErrorCode(Double.valueOf(column.getColumnValue().toString().trim()).intValue() + "");
                            break;
                        case "subErrorCode":
                            errorCode.setSubErrorCode(column.getColumnValue().toString().trim());
                            break;
                        case "subErrorMsg":
                            errorCode.setSubErrorMsg(column.getColumnValue().toString().trim());
                            break;
                        case "solution":
                            errorCode.setSolution((String) column.getColumnValue());
                            break;
                    }
                });
                if (map.get(errorCode.getUniqId()) != null) {
                    System.out.println("重复错误码：" + errorCode);
                    j++;
                } else {
                    map.put(errorCode.getUniqId(), errorCode);
                }
            }
        }

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("重复数量：" + j);
        System.out.println("入库数量：" + map.size());
        System.out.println("---------------------------------------------------------------------------");


        final StringBuilder deleteSql = new StringBuilder();
        final ErrorCode[] errorCodes = map.values().toArray(new ErrorCode[map.size()]);
        for (int i1 = 0; i1 < errorCodes.length; i1++) {
            final ErrorCode errorCode = errorCodes[i1];
            deleteSql.append(String.format("delete from tbl_error_code_solution where error_code_id in (select id from tbl_error_code " +
                            "where type = '%s' and error_code = '%s' and sub_error_code = '%s' and api_group_code = '%s' and api_uri = '%s');%n",
                    errorCode.getType(), errorCode.getErrorCode(), errorCode.getSubErrorCode(), errorCode.getApiGroup(), errorCode.getApiUri()
            ));

            final String solution = null == errorCode.getSolution() ? "" : errorCode.getSolution();

            System.out.println(String.format("insert into tbl_error_code_solution(error_code_id, inner_solution, outer_solution) values(ifnull((select id from tbl_error_code " +
                            "where type = '%s' and error_code = '%s' and sub_error_code = '%s' and api_group_code = '%s' and api_uri = '%s'), -99999), '%s', '%s');",
                    errorCode.getType(), errorCode.getErrorCode(), errorCode.getSubErrorCode(), errorCode.getApiGroup(), errorCode.getApiUri(), solution, solution
            ));
        }


//        System.out.println("delete sqls:\n" + deleteSql);
        System.out.println("---------------------------------------------------------------------------");


//        final PrintConfig printConfig = new PrintConfig();
//        printConfig.setSkipColumns(new int[]{});
//        printConfig.setLineSeparator("\n");
//        printConfig.setColumnSeparator("\t");

    }

    private static class ErrorCode {
        private String type;
        private String apiGroup;
        private String apiUri;
        private String errorCode;
        private String subErrorCode;
        private String subErrorMsg;
        private String solution;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getApiGroup() {
            return apiGroup;
        }

        public void setApiGroup(String apiGroup) {
            this.apiGroup = apiGroup;
        }

        public String getApiUri() {
            return apiUri;
        }

        public void setApiUri(String apiUri) {
            this.apiUri = apiUri;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getSubErrorCode() {
            return subErrorCode;
        }

        public void setSubErrorCode(String subErrorCode) {
            this.subErrorCode = subErrorCode;
        }

        public String getSubErrorMsg() {
            return subErrorMsg;
        }

        public void setSubErrorMsg(String subErrorMsg) {
            this.subErrorMsg = subErrorMsg;
        }

        public String getSolution() {
            return solution;
        }

        public void setSolution(String solution) {
            this.solution = solution;
        }

        public String getUniqId() {
            return type + errorCode + subErrorCode + apiGroup + apiUri;
        }

        @Override
        public String toString() {
            return "ErrorCode{" +
                    "type='" + type + '\'' +
                    ", apiGroup='" + apiGroup + '\'' +
                    ", apiUri='" + apiUri + '\'' +
                    ", errorCode='" + errorCode + '\'' +
                    ", subErrorCode='" + subErrorCode + '\'' +
                    ", subErrorMsg='" + subErrorMsg + '\'' +
                    ", solution='" + solution + '\'' +
                    '}';
        }
    }

    private static void printWqh() throws Exception {
        final List<Line> lines = Lists.newArrayList();
        for (int i = 1; i < 9; i++) {
            final File importFile = new File("/Users/wdc/Downloads/压缩包/UMU_题库(护理题库" + i + ").xlsx");
            try (Workbook wb = WorkbookFactory.create(importFile)) {
                final WorkBookParseConfig parseConfig = new WorkBookParseConfig();
                parseConfig.setDataRowStart(2);
                parseConfig.setHeaders(new String[]{"问题描述", "题型", "正确答案", "分值", "难度", "答案说明", "选项A", "选项B", "选项C", "选项D", "选项E"});
                parseConfig.setLineIdPrefix("题目");
                lines.addAll(parseWorkBook(wb, parseConfig));
            }
        }
        final PrintConfig printConfig = new PrintConfig();
        printConfig.setSkipColumns(new int[]{3, 4, 5});
        printConfig.setLineSeparator("\n");
        printConfig.setColumnSeparator("\n");
        final String result = specialPrintToString(lines, printConfig);
        try (FileWriter writer = new FileWriter(new File("/Users/wdc/Documents/wqh/护理题库/题库汇总.txt"))) {
            writer.write(result);
        }
    }

    public static final class WorkBookParseConfig {
        /**
         * 标题行
         */
        private String[] headers;

        /**
         * 数据起始行号(0……n), 默认从第一行
         */
        private int dataRowStart;

        /**
         * 行描述前缀
         */
        private String lineIdPrefix;

        public String[] getHeaders() {
            return headers;
        }

        public void setHeaders(String[] headers) {
            this.headers = headers;
        }

        public int getDataRowStart() {
            return dataRowStart;
        }

        public void setDataRowStart(int dataRowStart) {
            this.dataRowStart = dataRowStart;
        }

        public String getLineIdPrefix() {
            return lineIdPrefix;
        }

        public void setLineIdPrefix(String lineIdPrefix) {
            this.lineIdPrefix = lineIdPrefix;
        }
    }

    public static class Line {
        private String lineName;
        private int LineIndex;
        private List<Column> columns;

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
        }

        public int getLineIndex() {
            return LineIndex;
        }

        public void setLineIndex(int lineIndex) {
            this.LineIndex = lineIndex;
        }

        public List<Column> getColumns() {
            return columns;
        }

        public void setColumns(List<Column> columns) {
            this.columns = columns;
        }
    }

    public static class Column {
        private String columnId;
        private int columnIndex;
        private CellType columnType;
        private String columnName;
        private Object columnValue;

        public String getColumnId() {
            return columnId;
        }

        public void setColumnId(String columnId) {
            this.columnId = columnId;
        }

        public int getColumnIndex() {
            return columnIndex;
        }

        public void setColumnIndex(int columnIndex) {
            this.columnIndex = columnIndex;
        }

        public CellType getColumnType() {
            return columnType;
        }

        public void setColumnType(CellType columnType) {
            this.columnType = columnType;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public Object getColumnValue() {
            return columnValue;
        }

        public void setColumnValue(Object columnValue) {
            this.columnValue = columnValue;
        }
    }

    private static class PrintConfig {
        private int[] skipLines;
        private int[] skipColumns;
        private String lineSeparator;
        private String columnSeparator;

        public int[] getSkipLines() {
            return skipLines;
        }

        public void setSkipLines(int[] skipLines) {
            this.skipLines = skipLines;
        }

        public int[] getSkipColumns() {
            return skipColumns;
        }

        public void setSkipColumns(int[] skipColumns) {
            this.skipColumns = skipColumns;
        }

        public String getLineSeparator() {
            return lineSeparator;
        }

        public void setLineSeparator(String lineSeparator) {
            this.lineSeparator = lineSeparator;
        }

        public String getColumnSeparator() {
            return columnSeparator;
        }

        public void setColumnSeparator(String columnSeparator) {
            this.columnSeparator = columnSeparator;
        }
    }

    private static List<Line> parseWorkBook(Workbook wb, WorkBookParseConfig parseConfig) {
        final ArrayList<Line> lines = Lists.newArrayList();
        int sheetNum = wb.getNumberOfSheets();
        if (sheetNum > 0) {
            for (int sn = 0; sn < sheetNum; sn++) {
                Sheet sheet = wb.getSheetAt(sn);
                Row row;
                Cell cell;
                Line line;
                Column column;
                int rowEnd = sheet.getPhysicalNumberOfRows();
                int rowStart = parseConfig.getDataRowStart();
                if (rowStart < 0) {
                    throw new IllegalArgumentException("dataRowStart is illegal");
                }

                for (int rowNum = rowStart, i = 1; rowNum < rowEnd; rowNum++, i++) {
                    row = sheet.getRow(rowNum);
                    if (row != null && !isblankRow(row)) {
                        line = new Line();
                        line.setColumns(Lists.newArrayList());
                        line.setLineName(parseConfig.getLineIdPrefix() + i);
                        line.setLineIndex(rowNum);
                        for (int colNum = 0; colNum < row.getPhysicalNumberOfCells(); colNum++) {
                            cell = row.getCell(colNum);
                            String colNumString = CellReference.convertNumToColString(colNum);
                            column = new Column();
                            column.setColumnId(colNumString);
                            column.setColumnIndex(colNum);
                            column.setColumnName(parseConfig.getHeaders()[colNum]);
                            richColumn(cell, column);
                            line.getColumns().add(column);
                        }
                        lines.add(line);
                    }
                }
            }
        }
        return lines;
    }

    private static void richColumn(Cell cell, Column column) {
        switch (cell.getCellTypeEnum()) {
            case FORMULA:
                column.setColumnType(CellType.FORMULA);
                column.setColumnValue(cell.getCellFormula());
                break;
            case NUMERIC:
                column.setColumnType(CellType.NUMERIC);
                if (DateUtil.isCellDateFormatted(cell)) {
                    column.setColumnValue(cell.getDateCellValue());
                } else {
                    column.setColumnValue(String.valueOf(cell.getNumericCellValue()));
                }
                break;
            case STRING:
                column.setColumnType(CellType.STRING);
                column.setColumnValue(cell.getStringCellValue());
                break;
            case BOOLEAN:
                column.setColumnType(CellType.BOOLEAN);
                column.setColumnValue(cell.getBooleanCellValue());
                break;
        }
    }

    private static String printToString(List<Line> lines, PrintConfig printConfig) {
        StringBuilder result = new StringBuilder();
        boolean skipLine = false, skipColumn = false;
        if (null != printConfig.getSkipLines()) {
            Arrays.sort(printConfig.getSkipLines());
            skipLine = true;
        }
        if (null != printConfig.getSkipColumns()) {
            Arrays.sort(printConfig.getSkipColumns());
            skipColumn = true;
        }
        if (null != lines && lines.size() > 0) {
            Line line;
            List<Column> columns;
            Column column;
            for (int i = 0; i < lines.size(); i++) {
                line = lines.get(i);
                if (skipLine && Arrays.binarySearch(printConfig.getSkipLines(), line.getLineIndex()) > -1) {
                    continue;
                }
                result.append(line.getLineName()).append(printConfig.getLineSeparator());
                columns = line.getColumns();
                if (null != columns && columns.size() > 0) {
                    for (int j = 0; j < columns.size(); j++) {
                        column = columns.get(j);
                        if (skipColumn && Arrays.binarySearch(printConfig.getSkipColumns(), column.getColumnIndex()) > -1) {
                            continue;
                        }
                        result.append(column.getColumnName()).append(":").append(column.getColumnValue()).append(printConfig.getColumnSeparator());
                    }
                }
            }
        }
        System.out.println(result);
        return result.toString();
    }

    private static String specialPrintToString(List<Line> lines, PrintConfig printConfig) {
        StringBuilder result = new StringBuilder();
        boolean skipLine = false, skipColumn = false;
        if (null != printConfig.getSkipLines()) {
            Arrays.sort(printConfig.getSkipLines());
            skipLine = true;
        }
        if (null != printConfig.getSkipColumns()) {
            Arrays.sort(printConfig.getSkipColumns());
            skipColumn = true;
        }
        if (null != lines && lines.size() > 0) {
            Line line;
            List<Column> columns;
            Column column;
            for (int i = 0, t = 1; i < lines.size(); i++, t++) {
                line = lines.get(i);
                if (skipLine && Arrays.binarySearch(printConfig.getSkipLines(), line.getLineIndex()) > -1) {
                    t++;
                    continue;
                }
                result.append(t + "、");
                columns = line.getColumns();
                if (null != columns && columns.size() > 0) {
                    result.append(columns.get(0).getColumnValue())
                            .append("(").append(columns.get(1).getColumnValue()).append(")\n")
                            .append(columns.get(2).getColumnName()).append(":");
                    switch (columns.get(2).getColumnValue().toString()) {
                        case "A":
                            result.append(columns.get(6).getColumnValue());
                            break;
                        case "B":
                            result.append(columns.get(7).getColumnValue());
                            break;
                        case "C":
                            result.append(columns.get(8).getColumnValue());
                            break;
                        case "D":
                            result.append(columns.get(9).getColumnValue());
                            break;
                        case "E":
                            result.append(columns.get(10).getColumnValue());
                            break;
                    }
                    result.append("\n----------------分割线---------------------\n");
                }
            }
        }
        System.out.println(result);
        return result.toString();
    }

    private static boolean isblankRow(Row row) {
        for (Cell cell : row) {
            if (!isBlankCell(cell)) return false;
        }
        return true;
    }

    private static boolean isBlankCell(Cell cell) {
        return null == cell || cell.getCellTypeEnum() == CellType._NONE
                || cell.getCellTypeEnum() == CellType.BLANK
                || cell.getCellTypeEnum() == CellType.ERROR;
    }

}
