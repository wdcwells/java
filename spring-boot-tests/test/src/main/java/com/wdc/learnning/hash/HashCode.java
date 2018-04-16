package com.wdc.learnning.hash;

public class HashCode {
    public static void main(String[] args) {
        HashCode obj = new HashCode();
        System.out.println(obj.hashCode());
        System.out.println(System.identityHashCode(obj));
    }

    @Override
    public int hashCode() {
        System.out.println("原：" + super.hashCode());
        return 1;
    }
}
