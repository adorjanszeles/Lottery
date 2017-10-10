package com.lottery.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/lottery")
@RestController
public class LotteryController {

    @RequestMapping("/most-frequent-five-number")
    @ResponseBody
    public String getMostFrequentFiveNumber() {
        //TODO

        return "success";
    }

    @RequestMapping("/four-match-ratio-to-five-match")
    @ResponseBody
    public String getFourMatchRatioToFiveMatch() {
        //TODO

        return "success";
    }

    @RequestMapping("/rearest-five-number")
    @ResponseBody
    public String getRearestFiveNumber() {
        //TODO

        return "success";
    }

    @RequestMapping("/average")
    @ResponseBody
    public String getAverage() {
        //TODO

        return "success";
    }

    @RequestMapping("/most-frequently-occuring-five-number")
    @ResponseBody
    public String getMostFrequentlyOccuringFiveNumber() {
        //TODO

        return "success";
    }

    @RequestMapping("/average-time-between-two-match-five-draws")
    @ResponseBody
    public String getAverageTimeBetweenTwoMatchFiveDraws() {
        //TODO

        return "success";
    }

}
