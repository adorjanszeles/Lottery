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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@Api(value = "/lottery", description = "Lottery Rules")
@RequestMapping(value = "/lottery")
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

    @GetMapping("/most-frequent-five-number")
    @ApiOperation(value = "GET most frequent five num", notes = "gets the five most frequently drawn numbers")
    public MostFrequentFiveNumberResult getMostFrequentFiveNumber() {
        return this.mostFrequentFiveNumberService.executeRule();
    }

    @GetMapping("/most-frequent-five-number/{from}/{to}")
    @ApiOperation(value = "GET most frequent five num filtered by date",
                  notes = "gets the five most frequently drawn numbers between given date")
    public MostFrequentFiveNumberResult getMostFrequentFiveNumberFiltered(
            @ApiParam(value = "filter date from", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to", required = true) @PathVariable("to") String to){
        return this.mostFrequentFiveNumberService.executeRuleFilterByDate(from, to);
    }

    @ApiOperation(value = "GET four match ratio to five",
                  notes = "gets the ratio of four matches compare to five matches")
    @GetMapping("/four-match-ratio-to-five-match")
    public FourMatchRatioToFiveMatchResult getFourMatchRatioToFiveMatch() {
        return this.fourMatchRatioToFiveService.executeRule();
    }

    @ApiOperation(value = "GET rearest five nums", notes = "gets the five rarest drawn numbers")
    @GetMapping("/rearest-five-number")
    public RearestFiveResult getRearestFiveNumber() {
        return this.rearestFiveService.executeRule();
    }

    @ApiOperation(value = "GET average num", notes = "gets the average of drawn numbers")
    @GetMapping("/average")
    public AverageResult getAverage() {
        return this.averageService.executeRule();
    }

    @ApiOperation(value = "GET most frequent pairs", notes = "gets number pairs that are most frequently drawn")
    @GetMapping("/most-frequently-occuring-pairs")
    public MostFrequentlyOccurringPairsResult getMostFrequentlyOccuringFiveNumber() {
        return this.mostFrequentlyOccuringPairsService.executeRule();
    }

    @ApiOperation(value = "GET average time between 5 matches",
                  notes = "gets the average time passed between two five matches")
    @GetMapping("/average-time-between-two-match-five-draws")
    public AverageTimeBetweenTwoMatchFiveDrawsResult getAverageTimeBetweenTwoMatchFiveDraws() {
        return this.averageTimeBetweenTwoMatchFiveDrawsService.executeRule();
    }

}
