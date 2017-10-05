package com.lottery.model;

import java.util.Comparator;

/**
 * Comparator a legygakoribb 5 szám sorbarendezéséhez
 */

public class MostFrequentComparator implements Comparator<Node> {

    @Override
    public int compare(Node nodeOne, Node nodeTwo) {
        if (nodeOne.getCount() < nodeTwo.getCount()) {
            return 1;
        }
        else if (nodeOne.getCount() > nodeTwo.getCount()) {
            return -1;
        }
        else {
            if (nodeOne.getValue() < nodeTwo.getValue()) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }
}
