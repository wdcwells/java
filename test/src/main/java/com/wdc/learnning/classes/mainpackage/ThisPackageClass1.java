package com.wdc.learnning.classes.mainpackage;

public class ThisPackageClass1 {
    private String privateStr;

    public String getPrivateStr() {
        return privateStr;
    }

    public void setPrivateStr(String privateStr) {
        this.privateStr = privateStr;
    }

    @Override
    public String toString() {
        return "ThisPackageClass1{" +
                "privateStr='" + privateStr + '\'' +
                '}';
    }
}
