package com.wdc.learnning.classes.mainpackage;

/**
 * can not be extended
 */
public class ThisPackagePrivateConstruct {
    private static final ThisPackagePrivateConstruct instance = new ThisPackagePrivateConstruct();
    private String name;
    private ThisPackagePrivateConstruct() {
        this.name = "default";
    }

    ThisPackagePrivateConstruct(String name) {
        this.name = name;
    }

    public static ThisPackagePrivateConstruct getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "ThisPackagePrivateConstruct{" +
                "name='" + name + '\'' +
                '}';
    }
}
