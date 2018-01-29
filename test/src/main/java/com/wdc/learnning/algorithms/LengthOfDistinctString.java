package com.wdc.learnning.algorithms;

public class LengthOfDistinctString {
    public int lengthOfLongestSubstring(String s) {
        char[] charArr = s.toCharArray();
        StringBuilder result = new StringBuilder();
        for(int i=0; i< charArr.length; i++) {
            if(result.indexOf(String.valueOf(charArr[i])) < 0){
                result.append(charArr[i]);
            } else {
                result.append("-");
            }

        }
        return result.length();
    }

    public static void main(String[] args) {
        LengthOfDistinctString obj = new LengthOfDistinctString();
        System.out.println(obj.lengthOfLongestSubstring("pwwkew"));
    }
}
