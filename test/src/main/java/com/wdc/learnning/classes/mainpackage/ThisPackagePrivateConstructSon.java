package com.wdc.learnning.classes.mainpackage;

public class ThisPackagePrivateConstructSon extends ThisPackagePrivateConstruct {
    public ThisPackagePrivateConstructSon(String name) {
        super(name);
        System.out.println("son");
    }

    public static void main(String[] args) {
        System.out.println(new ThisPackagePrivateConstructSon("test"));
    }
}
