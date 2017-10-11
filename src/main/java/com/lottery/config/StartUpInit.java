package com.lottery.config;

import com.lottery.model.Lottery;
import com.lottery.service.LotteryFileReader;
import com.lottery.service.LotteryFileReaderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;

@Component
public class StartUpInit {

    private Lottery lottery;
    private static final String FILE_PATH = "otos.csv";

    @Autowired
    public StartUpInit(Lottery lottery) {
        this.lottery = lottery;
    }

    @PostConstruct
    public void init() throws FileNotFoundException{
        this.lottery.setLotteryList();
        String fileName = getClass().getClassLoader().getResource(StartUpInit.FILE_PATH).getFile();
        LotteryFileReader lotteryFileReader = new LotteryFileReaderImpl(this.lottery);
        lotteryFileReader.readFromFile(fileName);
    }
}
