package com.wdc.learnning.DataStructs;

/**
 * Created by rachel on 2017/6/16.
 */
public class MockDataStructs {
    //数据节点
    private static class DataNode {
        private String name;

        public DataNode() {
        }

        public DataNode(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    //顺序存储结构
    private static class OrderedArray {
        private DataNode[] dataNodes;
        private int totalSize;
        private int curSize = 0;

        public OrderedArray() {
            this(10);
        }

        public OrderedArray(int totalSize) {
            this.totalSize = totalSize;
            dataNodes = new DataNode[this.totalSize];
        }

        public DataNode get(int index){
            if (index < 0 || index > totalSize-1) return null;
            return dataNodes[index];
        }

        //插入
        public void insert(int index, DataNode dataNode) {
            if (index < 0 || index > totalSize-1) return;

            if (null != this.get(index)) {
                int mvSize = index;
                while (++mvSize < totalSize) {
                    if (null == dataNodes[mvSize]) break;
                }
                for (int i = mvSize; i > index ; i--) {
                    dataNodes[mvSize] = dataNodes[mvSize-1];
                }
            }
            dataNodes[index] = dataNode;
            curSize++;
        }

        public void add(DataNode dataNode){
            if (curSize == totalSize) return;
            if (null != dataNodes[totalSize-1]){
                int mvSize = totalSize-1;
                while (--mvSize > 0) {
                    if (null == dataNodes[mvSize]) break;
                }
                for (int i = mvSize; i < totalSize-1; i++) {
                    dataNodes[mvSize] = dataNodes[mvSize+1];
                }
            }
            int addIndex = totalSize;
            while (--addIndex >0) {
                if (null != dataNodes[addIndex]) {
                    dataNodes[addIndex+1] = dataNode;
                    break;
                }
            }
        }

    }
}
