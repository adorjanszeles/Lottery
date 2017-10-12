package com.lottery.controller;

import com.lottery.model.AverageResult;
import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;
import com.lottery.model.FourMatchRatioToFiveMatchResult;
import com.lottery.model.MostFrequentFiveNumberResult;
import com.lottery.model.MostFrequentlyOccurringPairsResult;
import com.lottery.model.RearestFiveResult;
import com.lottery.service.AverageService;
import com.lottery.service.AverageTimeBetweenTwoMatchFiveDrawsService;
import com.lottery.service.FourMatchRatioToFiveService;
import com.lottery.service.MostFrequentFiveNumberService;
import com.lottery.service.MostFrequentlyOccuringPairsService;
import com.lottery.service.RearestFiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lottery", produces = MediaType.APPLICATION_JSON_VALUE)
public class LotteryController {

    private MostFrequentFiveNumberService mostFrequentFiveNumberService;
    private FourMatchRatioToFiveService fourMatchRatioToFiveService;
    private RearestFiveService rearestFiveService;
    private AverageService averageService;
    private MostFrequentlyOccuringPairsService mostFrequentlyOccuringPairsService;
    private AverageTimeBetweenTwoMatchFiveDrawsService averageTimeBetweenTwoMatchFiveDrawsService;

    @Autowired
    public LotteryController(MostFrequentFiveNumberService mostFrequentFiveNumberService,
                             FourMatchRatioToFiveService fourMatchRatioToFiveService,
                             RearestFiveService rearestFiveService,
                             AverageService averageService,
                             MostFrequentlyOccuringPairsService mostFrequentlyOccuringPairsService,
                             AverageTimeBetweenTwoMatchFiveDrawsService averageTimeBetweenTwoMatchFiveDrawsService) {
        this.mostFrequentFiveNumberService = mostFrequentFiveNumberService;
        this.fourMatchRatioToFiveService = fourMatchRatioToFiveService;
        this.rearestFiveService = rearestFiveService;
        this.averageService = averageService;
        this.mostFrequentlyOccuringPairsService = mostFrequentlyOccuringPairsService;
        this.averageTimeBetweenTwoMatchFiveDrawsService = averageTimeBetweenTwoMatchFiveDrawsService;
    }

    @RequestMapping("/most-frequent-five-number")
    public MostFrequentFiveNumberResult getMostFrequentFiveNumber() {
        return this.mostFrequentFiveNumberService.executeRule();
    }

    @RequestMapping("/four-match-ratio-to-five-match")
    public FourMatchRatioToFiveMatchResult getFourMatchRatioToFiveMatch() {
        return this.fourMatchRatioToFiveService.executeRule();
    }

    @RequestMapping("/rearest-five-number")
    public RearestFiveResult getRearestFiveNumber() {
        return this.rearestFiveService.executeRule();
    }

    @RequestMapping("/average")
    public AverageResult getAverage() {
        return this.averageService.executeRule();
    }

    @RequestMapping("/most-frequently-occuring-pairs")
    public MostFrequentlyOccurringPairsResult getMostFrequentlyOccuringFiveNumber() {
        return this.mostFrequentlyOccuringPairsService.executeRule();
    }

    @RequestMapping("/average-time-between-two-match-five-draws")
    public AverageTimeBetweenTwoMatchFiveDrawsResult getAverageTimeBetweenTwoMatchFiveDraws() {
        return this.averageTimeBetweenTwoMatchFiveDrawsService.executeRule();
    }

}
