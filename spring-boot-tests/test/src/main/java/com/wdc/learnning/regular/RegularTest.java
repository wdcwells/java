package com.wdc.learnning.regular;

import java.util.regex.Pattern;

public class RegularTest {
    private static final Pattern phonePattern = Pattern.compile("[^\u4e00-\u9fa5]{0,5}");
    private static final Pattern mobilePattern = Pattern.compile("^1\\d{10}");
    private static final Pattern creditCardPattern = Pattern.compile("[1-9]");
    private static final Pattern taiNoPattern = Pattern.compile("\\d{13}");

    public static void main(String[] args) {
//        String idNo = "3102101251364";
        String idNo = "1877019562761";
        System.out.println(idNo.length());
        System.out.println(checkTaiNo(idNo));

        System.out.println(mobilePattern.matcher("15801008348").matches());
        System.out.println(creditCardPattern.matcher("123").matches());
        System.out.println(creditCardPattern.matcher("9").matches());
        System.out.println(creditCardPattern.matcher("").matches());
//        System.out.println(Pattern.compile("[^\u4e00-\u9fa5]*").matcher("中").matches());
//        System.out.println(phonePattern.matcher("").matches());
//        System.out.println(phonePattern.matcher("1").matches());
//        System.out.println(phonePattern.matcher("232").matches());
//        System.out.println(phonePattern.matcher("666666").matches());
//        System.out.println(phonePattern.matcher("中66").matches());
    }

    private static boolean checkTaiNo(String idNo) {
        if (null == idNo || idNo.length() != 13 || !taiNoPattern.matcher(idNo).matches()) return false;
        char[] chars = idNo.toCharArray();
        int checkNo = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            checkNo += Character.getNumericValue(chars[i]) * (13 - i);
        }
        checkNo = checkNo % 11;
        if (checkNo <= 1) checkNo = 1 - checkNo;
        else checkNo = 11 - checkNo;
        return checkNo == Character.getNumericValue(chars[chars.length - 1]);
    }
}
