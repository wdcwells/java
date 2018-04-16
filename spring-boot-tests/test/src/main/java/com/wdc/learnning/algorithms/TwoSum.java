package com.wdc.learnning.algorithms;

import java.util.*;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * @Example
 *      Given nums = [2, 7, 11, 15], target = 9,
 *      Because nums[0] + nums[1] = 2 + 7 = 9,
 *      return [0, 1].
 */
public class TwoSum {
    /**
     * 遍历nums
     * 1、记录：记录索引，target-num；
     * 2、比对：如果num = 记录中的某一个（target-num, 返回；否则继续1；
     * @param nums 数组
     * @param target 目标
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] two = new int[2];
        List<Integer> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int index;
            if ((index = list.indexOf(num)) >= 0) {
                two[0] = index;
                two[1] = i;
                return two;
            }
            list.add(target - num);
        }
        return two;
    }

    /**
     * 网络推荐-map
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static int[] twoSum1(int[] nums, int target) {
        int[] two = new int[2];
        HashMap<Integer, Integer> hashMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            Integer found = hashMap.get(nums[i]);
            if (null != found) {
                two[0] = found.intValue();
                two[1] = i;
                return two;
            }
            hashMap.put(target - nums[i], i);
        }
        return two;
    }

    public static void main(String[] args) {
        Arrays.stream(twoSum1(new int[]{2,1,9,4,4,56,90,3}, 60)).forEach(System.out::println);
    }
}
