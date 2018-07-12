package com.wdc.study.utils;

/**
 * @author wdc
 * @date 2018/7/12
 */
public class ChineseUtil {

    /**
     * @info http://www.qqxiuzi.cn/zh/hanzi-unicode-bianma.php
     * 基本汉字	    20902字	4E00-9FA5
     * 基本汉字补充	74字	    9FA6-9FEF
     * 扩展A	        6582字	3400-4DB5
     * 扩展B	        42711字	20000-2A6D6
     * 扩展C	        4149字	2A700-2B734
     * 扩展D	        222字	2B740-2B81D
     * 扩展E	        5762字	2B820-2CEA1
     * 扩展F	        7473字	2CEB0-2EBE0
     * 康熙部首	    214字	2F00-2FD5
     * 部首扩展	    115字	2E80-2EF3
     * 兼容汉字	    477字	F900-FAD9
     * 兼容扩展	    542字	2F800-2FA1D
     * PUA(GBK)部件	81字	    E815-E86F
     * 部件扩展	    452字	E400-E5E8
     * PUA增补    	207字	E600-E6CF
     * 汉字笔画	    36字	    31C0-31E3
     * 汉字结构	    12字	    2FF0-2FFB
     * 汉语注音	    43字	    3105-312F
     * 注音扩展	    22字	    31A0-31BA
     * 〇	        1字	    3007
     */

    public static String ofCodePoint(int codePoint) {
        return String.valueOf(Character.toChars(codePoint));
    }

    public static void printCharsBetween(int begin, int end, int lineLength) {
        for (int i = begin, count = 1; i < end; i++, count = count > lineLength ? 1 : (count + 1)) {
            if (count == lineLength)
                System.out.println(ofCodePoint(i));
            else System.out.print(ofCodePoint(i) + " ");
        }
    }

    public static void main(String[] args) {
//        int begin = 0x4E00, end = 0x9FA5, lineLength = 500;
//        int begin = 0x9FA6, end = 0x9FEF, lineLength = 20;
//        int begin = 0x31C0, end = 0x31E3, lineLength = 20;
//        int begin = 0x3400, end = 0x4DB5, lineLength = 100;
//        printCharsBetween(begin, end, lineLength);
//        System.out.println(ofCodePoint(0x005c));
    }
}
