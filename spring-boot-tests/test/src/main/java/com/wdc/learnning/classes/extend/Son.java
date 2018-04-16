package com.wdc.learnning.classes.extend;

public class Son extends Parent implements Cloneable{
    private String name;
    final Object object;

    public Son(Integer id, String name) {
        super(id);
        this.name = name;
        this.object = new Object();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Son wdc = new Son(1, "wdc");
        System.out.println(wdc);
        Son clone = (Son) wdc.clone();
        System.out.println(clone);
        System.out.println(new FinalParent("wqh").clone());
    }

    @Override
    protected Integer doubleId() {
        return getId() * 20;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() {
        try {
            Son clone = (Son) super.clone();
            //final域只能在定义或者构造时赋值
//        clone.object = new Object();
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException("克隆失败");
        }
    }

    @Override
    public String toString() {
        return "Son{" +
                "name='" + name + '\'' +
                ", object=" + object +
                '}';
    }
}
