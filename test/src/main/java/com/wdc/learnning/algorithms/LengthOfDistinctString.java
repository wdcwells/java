package com.wdc.learnning.algorithms;

public class LengthOfDistinctString {
    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) return 0;
        int begin = 0, end = 1, maxLenth = 1;
        String subStr = s.substring(begin, end);
        while (end < s.length()) {
            char curChar = s.charAt(end);
            int foundIndex = subStr.indexOf(curChar);
            if (foundIndex == -1) {
            } else {
                begin = begin + foundIndex + 1;
            }
            end++;
            subStr = s.substring(begin, end);
            if ((end - begin) > maxLenth) maxLenth = end - begin;
        }
        return maxLenth;
    }

    public static void main(String[] args) {
        LengthOfDistinctString obj = new LengthOfDistinctString();
        System.out.println(obj.lengthOfLongestSubstring(""));
        System.out.println(obj.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(obj.lengthOfLongestSubstring("bbbbb"));
        System.out.println(obj.lengthOfLongestSubstring("pwwkew"));
    }
}
