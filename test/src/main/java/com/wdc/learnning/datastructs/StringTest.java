package com.wdc.learnning.datastructs;

public class StringTest {
    public static void main(String[] args) {
//        System.out.println("abc".contains(""));
//        System.out.println('\u5d07');
//        int index = findSubStr("abccdefgcdf", "de", 0);
//        System.out.println(index);
        System.out.println(checkValue());
    }

    private static int checkValue() {
        int i = 1;
        return i++;
    }

    private static int findSubStr(String whole, String sub, int pos) {
        char[] wholeArr = whole.toCharArray();
        char[] subArr = sub.toCharArray();
        for (int i = pos; i < wholeArr.length; i++) {
            int j = 0;
            for (; j < subArr.length; j++) {
                if (subArr[j] != wholeArr[i]) break;
                i++;
            }
            if (j == subArr.length) return i - j;
        }
        return -1;
    }
}
