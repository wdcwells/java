package com.wdc.learnning.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvProcesser {
    private static final String COMMA = ",";

    public static void main(String[] args) throws IOException {
//        new CsvProcesser().processByBufferReader("/Users/wdc/Desktop/Tmp/recovery/蔚商-test1.csv");
        new CsvProcesser().processByCommonsCsv("/Users/wdc/Desktop/Tmp/recovery/蔚商-test1.csv");

    }

    private List<JavaItem> processByCommonsCsv(String fileName) throws IOException {
        List<JavaItem> inputList = new ArrayList<>();
        try (Reader in = new FileReader(fileName)) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            for (CSVRecord record : records) {
                Integer id = Integer.valueOf(record.get(0));
                String name = record.get(1);
                BigDecimal bigDecimal = BigDecimal.valueOf(Double.valueOf(record.get(2)));
                Date date = new Date();
                inputList.add(new JavaItem(id, name, bigDecimal, date));
            }
        }
        return inputList;
    }

    private List<JavaItem> processByBufferReader(String inputFilePath) throws IOException {
        List<JavaItem> inputList;
        File inputF = new File(inputFilePath);
        InputStream inputFS = new FileInputStream(inputF);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputFS))) {
            inputList = br.lines().skip(1).map(this::mapToItem).filter(Objects::nonNull).collect(Collectors.toList());
        }
        return inputList;
    }

    private JavaItem mapToItem(String line) {
        String[] p = line.split(COMMA);// a CSV has comma separated lines
        if (p.length >=4 ) {
            JavaItem item = new JavaItem(
                    Integer.valueOf(p[0]), p[1], BigDecimal.valueOf(Double.valueOf(p[2])), new Date());
            return item;
        }
        return null;
    }

    private class JavaItem {
        private Integer id;
        private String name;
        private BigDecimal bigDecimal;
        private Date date;


        public JavaItem(Integer id, String name, BigDecimal bigDecimal, Date date) {
            this.id = id;
            this.name = name;
            this.bigDecimal = bigDecimal;
            this.date = date;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getBigDecimal() {
            return bigDecimal;
        }

        public void setBigDecimal(BigDecimal bigDecimal) {
            this.bigDecimal = bigDecimal;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
