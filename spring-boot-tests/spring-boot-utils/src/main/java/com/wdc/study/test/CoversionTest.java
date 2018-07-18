package com.wdc.study.test;

/**
 * @author wdc
 * @date 2018/7/17
 */
public class CoversionTest {
    public static void main(String[] args) {
//        widdening();
//        sfp();
//        nonSfp();
//        floatToInt();
//        floatStoreDetail();
        floatMaxCalculate();
    }

    private static void floatMaxCalculate() {
        System.out.println(Float.MAX_VALUE);
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(Float.MAX_VALUE)));
        //1111111011111111111111111111111
        int m = (int) (Math.pow(2, 24) - 1);
        System.out.println(m);
        System.out.println((float) (m * Math.pow(2, (Math.pow(2, 8) - 1 - 1 - 127 - 23))));
    }

    /**
     * IEEE 754对有效数字M和指数E，还有一些特别规定。
     * 前面说过，1≤M<2，也就是说，M可以写成1.xxxxxx的形式，其中xxxxxx表示小数部分。IEEE 754规定，在计算机内部保存M时，默认这个数的第一位总是1，因此可以被舍去，只保存后面的xxxxxx部分。比如保存1.01的时候，只保存01，等到读取的时候，再把第一位的1加上去。这样做的目的，是节省1位有效数字。以32位浮点数为例，留给M只有23位，将第一位的1舍去以后，等于可以保存24位有效数字。
     * 至于指数E，情况就比较复杂。
     * 首先，E为一个无符号整数（unsigned int）。这意味着，如果E为8位，它的取值范围为0~255；如果E为11位，它的取值范围为0~2047。但是，我们知道，科学计数法中的E是可以出现负数的，所以IEEE 754规定，E的真实值必须再减去一个中间数，对于8位的E，这个中间数是127；对于11位的E，这个中间数是1023。
     * 比如，2^10的E是10，所以保存成32位浮点数时，必须保存成10+127=137，即10001001。
     * 然后，指数E还可以再分成三种情况：
     * （1）E不全为0或不全为1。这时，浮点数就采用上面的规则表示，即指数E的计算值减去127（或1023），得到真实值，再将有效数字M前加上第一位的1。
     * （2）E全为0。这时，浮点数的指数E等于1-127（或者1-1023），有效数字M不再加上第一位的1，而是还原为0.xxxxxx的小数。这样做是为了表示±0，以及接近于0的很小的数字。
     * （3）E全为1。这时，如果有效数字M全为0，表示±无穷大（正负取决于符号位s）；如果有效数字M不全为0，表示这个数不是一个数（NaN）。
     */
    private static void floatStoreDetail() {
        int value = 0;
        float fValue = 2147483647f;
        System.out.println(fValue);
        String storeBits = Integer.toBinaryString(Float.floatToIntBits(fValue));
        System.out.println(storeBits + "\n除去符号位，共" + storeBits.length() + "位");
        String exponentBits = storeBits.substring(0, 8);
        int exponentDecimal = Integer.parseUnsignedInt(exponentBits, 2);
        System.out.println("8位无符号指数位：" + exponentBits + "(" + exponentDecimal + ")");

        String m = storeBits.substring(9);
        int mDecimal = Integer.parseUnsignedInt(m, 2);
        System.out.println("23位无符号有效小数位：" + m + "(" + mDecimal + ")");

        int exponentReal = exponentDecimal - 127;
        value += (mDecimal + 1) * Math.pow(2, exponentReal);
        System.out.println("真实值：" + value);
    }

    private strictfp static void floatToInt() {
        System.out.println(Float.floatToIntBits(9f));//1091567616
        System.out.println(Float.intBitsToFloat(9));//0
        System.out.println(Integer.toBinaryString(1091567616));
        float max = Float.MAX_VALUE;
        System.out.println(max);

        int iMax = (int) 2147483647f;
        System.out.println(iMax);
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(2147483647f)));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
    }

    private strictfp static void sfp() {
        nonSfp();
    }

    private static void nonSfp() {
        float aFloat = 0.6710339f;
        double aDouble = 0.04150553411984792d;
        double sum = aFloat + aDouble;
        float quotientF = (float)(aFloat / aDouble);
        double quotientD = aFloat / aDouble;
        System.out.println("float: " + aFloat);
        System.out.println("double: " + aDouble);
        System.out.println("sum: " + sum);
        System.out.println("quotientF: " + quotientF);
        System.out.println("quotientD: " + quotientD);
    }

    private static void widdening() {
        int i = 99999628;
        float f = i;
        double d = i;
        System.out.println(i - (int) f);
        System.out.println(i - (int) d);
    }
}