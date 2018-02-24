package com.wdc.learnning.compare;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

public class HashSon extends HashParent{
    private List<JavaItem> items;
    public static void main(String[] args) {
        LinkedHashSet<HashSon> hashSons = new LinkedHashSet<>();
        HashSon hashSon = new HashSon();
        ArrayList<JavaItem> items = new ArrayList<>();
        items.add(new JavaItem(12));
        hashSon.setItems(items);
        hashSons.add(hashSon);
    }

    public List<JavaItem> getItems() {
        return items;
    }

    public void setItems(List<JavaItem> items) {
        this.items = items;
    }

    private static class JavaItem {
        private int id;

        public JavaItem(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            JavaItem javaItem = (JavaItem) o;
            return id == javaItem.id;
        }

        @Override
        public int hashCode() {
            System.out.println(1);
            return Objects.hash(id);
        }
    }
}
