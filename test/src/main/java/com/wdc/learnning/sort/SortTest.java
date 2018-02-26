package com.wdc.learnning.sort;

import org.junit.Test;

public class SortTest {
    @Test
    public void testInsertSort() {
        int[] arr = {4, 5, 3, 2, 1, 6, 7, 10, 8, 9};
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i - 1;
            for (; j >= 0 && tmp > arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void testShellSort() {

        int[] a = {1, 54, 6, 3, 78, 34, 12, 45, 56};

        double d1 = a.length;

        int temp = 0;

        while (true) {

            d1 = Math.ceil(d1 / 2);

            int d = (int) d1;

            for (int x = 0; x < d; x++) {

                for (int i = x + d; i < a.length; i += d) {

                    int j = i - d;

                    temp = a[i];

                    for (; j >= 0 && temp < a[j]; j -= d) {

                        a[j + d] = a[j];

                    }

                    a[j + d] = temp;

                }

            }

            if (d == 1)

                break;

        }

        for (int i = 0; i < a.length; i++)

            System.out.println(a[i]);
    }

    @Test
    public void testInsertSortMax(){
        int[] a = {9,8,7,6,5,4,3,2,1};
        int count = 0;
        for (int i = 1; i < a.length ; i++) {
            int tmp = a[i];
            int j = i - 1;
            for (;  j >= 0 && tmp < a[j]  ; j--) {
                a[j+1] = a[j];
                count++;
            }
            a[j+1] = tmp;
        }

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        System.out.println(count);//内层循环了36次……外层8次
    }

    @Test
    public void testShellSortMax(){
        int[] a = {2,3,1,4,8,6,5,7,9};
        int pad = a.length/2;

    }
}
