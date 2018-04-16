package com.wdc.learnning.classes.extend;

public final class FinalParent extends Object implements Cloneable{
    private String name;
    private InnerClassObj object;
    private FieldClass fieldClass;

    public FinalParent(String name) {
        this.name = name;
        this.object = new InnerClassObj("inner");
        this.fieldClass = new FieldClass("fieldClass");
    }

    public static void main(String[] args) {
        FinalParent wdc = new FinalParent("wdc");
        System.out.println(wdc);
        FinalParent clone = (FinalParent) wdc.clone();
        System.out.println(clone);
        System.out.println(clone.getObject().getInner());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InnerClassObj getObject() {
        return object;
    }

    public void setObject(InnerClassObj object) {
        this.object = object;
    }

    public FieldClass getFieldClass() {
        return fieldClass;
    }

    public void setFieldClass(FieldClass fieldClass) {
        this.fieldClass = fieldClass;
    }

    @Override
    protected Object clone() {
        try {
            FinalParent clone = (FinalParent) super.clone();
            clone.setObject((InnerClassObj) clone.getObject().clone());
            clone.setFieldClass((FieldClass) clone.getFieldClass().clone());
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "FinalParent{" +
                "name='" + name + '\'' +
                ", object=" + object +
                ", fieldClass=" + fieldClass +
                '}';
    }

    private static class InnerClassObj implements Cloneable{
        private String inner;

        public InnerClassObj(String inner) {
            this.inner = inner;
        }

        public String getInner() {
            return inner;
        }

        public void setInner(String inner) {
            this.inner = inner;
        }

        @Override
        protected Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
