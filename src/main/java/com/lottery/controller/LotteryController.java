package com.lottery.controller;


import com.lottery.model.MostFrequentFiveNumberResult;
import com.lottery.service.MostFrequentFiveNumberService;
import com.lottery.service.MostFrequentFiveNumberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/lottery")
@RestController
public class LotteryController {

    private MostFrequentFiveNumberService mostFrequentFiveNumberService;

    @Autowired
    public LotteryController(MostFrequentFiveNumberService mostFrequentFiveNumberService) {
        this.mostFrequentFiveNumberService = mostFrequentFiveNumberService;
    }

    @RequestMapping("/most-frequent-five-number")
    public MostFrequentFiveNumberResult getMostFrequentFiveNumber() {
        MostFrequentFiveNumberResult mostFrequentFiveNumberResult = this.mostFrequentFiveNumberService.executeRule();
        return mostFrequentFiveNumberResult;
    }

    @RequestMapping("/four-match-ratio-to-five-match")
    public String getFourMatchRatioToFiveMatch() {
        //TODO

        return "success";
    }

    @RequestMapping("/rearest-five-number")
    public String getRearestFiveNumber() {
        //TODO

        return "success";
    }

    @RequestMapping("/average")
    public String getAverage() {
        //TODO

        return "success";
    }

    @RequestMapping("/most-frequently-occuring-five-number")
    public String getMostFrequentlyOccuringFiveNumber() {
        //TODO

        return "success";
    }

    @RequestMapping("/average-time-between-two-match-five-draws")
    public String getAverageTimeBetweenTwoMatchFiveDraws() {
        //TODO

        return "success";
    }

}
