package com.lottery.model;

import com.lottery.service.LotteryFileReaderImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton lottó, ami egy listában tárolja a heti húzások objektumait
 */
@Component
public class Lottery {
    private static Object mutex = new Object();
    private List<WeeklyDraw> lotteryList;
    private LotteryFileReaderImpl lotteryFileReaderImpl;

    public List<WeeklyDraw> getLotteryList() {
        return this.lotteryList;
    }

    public void setLotteryList(String filePath) {
        if (this.lotteryList == null) {
            synchronized (Lottery.mutex) {
                if (this.lotteryList == null) {
                    this.lotteryList = new ArrayList<>();
                    this.lotteryFileReaderImpl = new LotteryFileReaderImpl(this);
                    String fileName = getClass().getClassLoader().getResource(filePath).getFile();
                    this.lotteryFileReaderImpl.readFromFile(fileName);
                }
            }
        }
    }

}
