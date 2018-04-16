package com.wdc.learnning.classes.extend;

public class Parent{
    private Integer id;

    public Parent(Integer id) {
        this.id = id;
        this.id = doubleId();
        this.id = tripleId();
    }

    protected Integer doubleId() {
        return id * 2;
    }

    final Integer tripleId() {
        return id * 3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
