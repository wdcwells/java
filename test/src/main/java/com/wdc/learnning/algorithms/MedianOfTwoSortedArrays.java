package com.wdc.learnning.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedianOfTwoSortedArrays {

    /**
     * 入参情况：
     * [1,2],[3,4]
     * [1,3],[2]
     * [1,4],[2,5]
     * [],[2]
     * 返回，排在中间两个数字的平均值
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;
        if (size1 == 0 && size2 == 0) return 0;
        int totalSize = size1 + size2;
        boolean hasTwoMedian = totalSize % 2 == 0;
        int firstMedian = hasTwoMedian ? totalSize / 2 : (totalSize + 1) / 2;
        //情况1：其中一个为空数组，取另一个数组的中间值
        int[] tmp = null;
        if (size1 == 0) {
            tmp = nums2;
        } else if (size2 == 0) {
            tmp = nums1;
        }
        if (null != tmp) {
            if (hasTwoMedian) {
                return (tmp[firstMedian] + tmp[firstMedian - 1]) / 2.0;
            } else {
                return tmp[firstMedian - 1];
            }
        } else {
            //情况2：两个都非空
            // 情况2.1：无交集/只交了相同的数，取两个数组中间值
            int minus = 0;
            if (nums1[nums1.length - 1] <= nums2[0]) {
                if (size1 < firstMedian) {
                    tmp = nums2;
                    minus = size1;
                } else {
                    if (size1 == size2) {
                        return (nums1[firstMedian - 1] + nums2[0]) / 2.0;
                    } else {
                        tmp = nums1;
                    }
                }
            } else if (nums2[nums2.length - 1] <= nums1[0]) {
                if (size2 < firstMedian) {
                    tmp = nums1;
                    minus = size2;
                } else {
                    if (size1 == size2) {
                        return (nums2[firstMedian - 1] + nums1[0]) / 2.0;
                    } else {
                        tmp = nums2;
                    }
                }
            }

            if (null != tmp) {
                if (hasTwoMedian) {
                    return (tmp[firstMedian - minus] + tmp[firstMedian - minus - 1]) / 2.0;
                } else {
                    return tmp[firstMedian - minus - 1];
                }
            }
            // 情况2.2：有交集，遍历、比较、得到中间值
            int[] outer = null;
            int[] inner = null;
            boolean isContain = false;
            List<Integer> addedResult = new ArrayList<>(totalSize);
            if (nums1[0] <= nums2[0]) {
                outer = nums1;
                inner = nums2;
                if (nums1[size1 - 1] >= nums2[size2 - 1]) isContain = true;
            } else {
                outer = nums2;
                inner = nums1;
                if (nums2[size2 - 1] >= nums1[size1 - 1]) isContain = true;
            }
            if (null != outer && null != inner) {
                //  情况2.2.1：包含关系，遍历、比较、得到中间值
                if (isContain) {
                    int innerCount = 0;
                    for (int i = 0; i < outer.length; i++) {
                        addedResult.add(outer[i]);
                        for (int j = innerCount; j < inner.length; j++) {
                            if (outer[i] < inner[j]) {
                                break;
                            } else {
                                addedResult.set(addedResult.size() - 1, inner[j]);
                                addedResult.add(outer[i]);
                                innerCount++;
                            }
                        }
                    }
                }
                //  情况2.2.2：交叉关系，遍历、比较、得到中间值
                else {
                    for (int i = 0; i < outer.length; i++) {
                        for (int j = inner.length - 1, k = 1; j > 0; j--, k++) {
                            int tmp1 = outer[i];
                            if (outer[i] <= inner[0]) {
                                addedResult.add(outer[i]);
                            } else {

                            }
                            if (inner[j] >= outer[outer.length - 1]) {
                                addedResult.set(totalSize - k, inner[j]);
                            }

                        }
                    }
                }
            }
            if (!addedResult.isEmpty()) {
                if (hasTwoMedian) {
                    return (addedResult.get(firstMedian - 1) + addedResult.get(firstMedian)) / 2.0;
                } else {
                    return addedResult.get(firstMedian - 1);
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays obj = new MedianOfTwoSortedArrays();
        System.out.println(obj.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println(obj.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4, 5}));
        System.out.println(obj.findMedianSortedArrays(new int[]{9, 10}, new int[]{3, 4, 5}));
        System.out.println(obj.findMedianSortedArrays(new int[]{}, new int[]{2}));
        System.out.println(obj.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(obj.findMedianSortedArrays(new int[]{1, 3, 4}, new int[]{2, 5}));
        System.out.println(obj.findMedianSortedArrays(new int[]{1, 6}, new int[]{2, 3, 4, 5}));
        System.out.println(obj.findMedianSortedArrays(new int[]{2}, new int[]{1, 3, 4}));
    }

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        double result = 0;
        double times = 0;
        if (nums1.length > 0) {
            times++;
            result = Arrays.stream(nums1).average().getAsDouble() / times;
        }
        if (nums2.length > 0) {
            times++;
            result = (result + Arrays.stream(nums2).average().getAsDouble()) / times;
        }
        return result;
    }
}
