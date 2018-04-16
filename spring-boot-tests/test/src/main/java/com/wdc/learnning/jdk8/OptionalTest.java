package com.wdc.learnning.jdk8;

import java.util.Optional;

/**
 * @author wdc
 * @date 2018/3/21
 */
public class OptionalTest {
    public static void main(String[] args) {
        System.out.println(Optional.ofNullable(null).isPresent());
    }
}
