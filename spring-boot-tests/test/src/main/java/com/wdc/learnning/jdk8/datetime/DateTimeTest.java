package com.wdc.learnning.jdk8.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeTest {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println(today);
        System.out.println(today.atStartOfDay());
        System.out.println(Instant.now());
        System.out.println(LocalDateTime.now());
    }
}
