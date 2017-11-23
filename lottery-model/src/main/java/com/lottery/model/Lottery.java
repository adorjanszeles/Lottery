package com.lottery.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton lottó, ami egy listában tárolja a heti húzások objektumait
 */
public class Lottery {
    private static final Object mutex = new Object();
    private List<WeeklyDraw> lotteryList;

    public List<WeeklyDraw> getLotteryList() {
        return this.lotteryList;
    }

    public void setLotteryList() {
        if (this.lotteryList == null) {
            synchronized (Lottery.mutex) {
                if (this.lotteryList == null) {
                    this.lotteryList = new ArrayList<>();
                }
            }
        }
    }

}
