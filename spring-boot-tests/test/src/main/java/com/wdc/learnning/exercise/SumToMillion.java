package com.wdc.learnning.exercise;

public class SumToMillion {
    public static int addAndSum(int addFrom, int addTo, int increment, int sum) {
        if (addFrom == (addTo + increment)) return sum;
        return addAndSum(addFrom + increment, addTo, increment, addFrom + sum);
    }

    public static void main(String[] args) {
        System.out.println(addAndSum(1, 10000, 1, 0));
//        System.out.println(addAndSum(1, 100000, 1, 0));
    }
}
