package model;

import java.util.List;

/**
 * Singleton lottó, ami egy listában tárolja a heti húzások objektumait
 */

public class Lottery {

    private static Lottery instance = null;
    private List<WeeklyDraw> lotteryList;

    private Lottery() {
    }

    public static Lottery getInstance(){
        if(Lottery.instance == null){
            Lottery.instance = new Lottery();
        }
        return Lottery.instance;
    }

    public List<WeeklyDraw> getLotteryList() {
        return this.lotteryList;
    }

    public void setLotteryList(List<WeeklyDraw> lotteryList) {
        this.lotteryList = lotteryList;
    }
}
