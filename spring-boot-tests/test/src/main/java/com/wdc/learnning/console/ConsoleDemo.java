package com.wdc.learnning.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author wdc
 * @date 2018/4/19
 */
public class ConsoleDemo {
    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = bf.readLine()) != null) {
                if (line.equals("exit")) break;
                System.out.println(">>>" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
