package com.lottery.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton lottó, ami egy listában tárolja a heti húzások objektumait
 */

public class Lottery {
    private static Lottery instance = null;
    private List<WeeklyDraw> lotteryList;
    private static Object mutex = new Object();

    private Lottery() {
        this.lotteryList = new ArrayList<WeeklyDraw>();
    }

    public static Lottery getInstance() {
        if (Lottery.instance == null) {
            synchronized (Lottery.mutex) {
                if(Lottery.instance == null) {
                    Lottery.instance = new Lottery();
                }
            }
        }
        return Lottery.instance;
    }

    public List<WeeklyDraw> getLotteryList() {
        return this.lotteryList;
    }

}
