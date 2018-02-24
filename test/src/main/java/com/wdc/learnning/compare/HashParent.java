package com.wdc.learnning.compare;

public class HashParent {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        System.out.println("super:" + super.hashCode());
        return 1;
    }
}
