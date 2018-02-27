package com.wdc.learnning.algorithms;

import java.util.Arrays;

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
            int[] result = new int[totalSize];
            result = handleArrays(result, nums1, nums2, 0, totalSize - 1);
            if (result.length > 0) {
                if (hasTwoMedian) {
                    return (result[firstMedian - 1] + result[firstMedian]) / 2.0;
                } else {
                    return result[firstMedian - 1];
                }
            }
        }
        return 0;
    }

    private int[] handleArrays(int[] result, int[] nums1, int[] nums2, int begin, int end) {
        if (nums1.length == 0 && nums2.length == 0) return result;
        int[] tmp = null;
        if (nums1.length == 0) {
            tmp = nums2;
        } else if (nums2.length == 0) {
            tmp = nums1;
        } else {
            int[] first;
            int[] second;
            boolean isContain = false;
            if (nums1[0] <= nums2[0]) {
                first = nums1;
                second = nums2;
            } else {
                first = nums2;
                second = nums1;
            }
            if (first[first.length - 1] >= second[second.length - 1]) isContain = true;
            int[] nums11 = null, nums12 = null;
            if (isContain) {
                int i = 0, j = first.length - 1;
                while (i < first.length && first[i] <= second[0]) {
                    result[begin++] = first[i++];
                }
                while (j >= i && first[j] >= second[second.length - 1]) {
                    result[end--] = first[j--];
                }
                if (j < i) nums11 = new int[0];
                else nums11 = Arrays.copyOfRange(first, i, j+1);
                nums12 = second;
            } else {
                int i = 0, j = second.length - 1;
                while (i < first.length && first[i] <= second[0]) {
                    result[begin++] = first[i++];
                }
                for (; j >= 0 && second[j] >= first[first.length - 1] && begin <= end; j--) {
                    result[end--] = second[j];
                }
                nums11 = i == first.length ? new int[0] : Arrays.copyOfRange(first, i, first.length);
                nums12 = j == -1 ? new int[0] : Arrays.copyOfRange(second, 0, j + 1);
            }
            return handleArrays(result,
                    nums11,
                    nums12,
                    begin,
                    end
            );
        }
        if (null != tmp) {
            int i = 0;
            while (begin <= end && i < tmp.length) {
                result[begin++] = tmp[i++];
            }
        }
        return result;
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
        System.out.println(obj.findMedianSortedArrays(new int[]{4}, new int[]{1, 2, 3, 5}));
        System.out.println(obj.findMedianSortedArrays(new int[]{2, 4}, new int[]{1, 3, 5}));
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
