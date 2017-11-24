package com.lottery.model;

/**
 * Segédosztály a lottószámok sorbarendezésének használatára value : lottószám, count : lottószám előfordulása
 */
public class Node implements Comparable<Node> {

    private Integer value;
    private Integer count;

    public Node() {
    }

    @Override
    public int compareTo(Node node) {
        if (this.count < node.getCount()) {
            return -1;
        } else if (this.count > node.getCount()) {
            return 1;
        } else {
            if (this.value < node.getValue()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}