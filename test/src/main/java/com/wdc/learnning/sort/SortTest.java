package com.wdc.learnning.sort;

import org.junit.Test;

public class SortTest {
    @Test
    public void testInsertSort(){
        int[] arr = {4,5,3,2,1,6,7,10,8,9};
        for (int i= 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            for (; j>=0 && tmp > arr[j]; j--) {
                arr[j+1] = arr[j];
            }
            arr[j+1] = tmp;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
