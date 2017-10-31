package com.lottery.controller;

import com.lottery.model.AverageResult;
import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;
import com.lottery.model.FourMatchRatioToFiveMatchResult;
import com.lottery.model.Lottery;
import com.lottery.model.MostFrequentFiveNumberResult;
import com.lottery.model.MostFrequentlyOccurringPairsResult;
import com.lottery.model.RearestFiveResult;
import com.lottery.model.WeeklyDraw;
import com.lottery.model.WeeklyDrawList;
import com.lottery.model.mfop_utils.DrawsInTwoDimension;
import com.lottery.repository.UserJPARepository;
import com.lottery.repository.WeeklyDrawJPARepository;
import com.lottery.service.AddingWeeklyDrawService;
import com.lottery.service.AverageService;
import com.lottery.service.AverageTimeBetweenTwoMatchFiveDrawsService;
import com.lottery.service.PersistFromCsv;
import com.lottery.service.DateIntervalService;
import com.lottery.service.FourMatchRatioToFiveService;
import com.lottery.service.MostFrequentFiveNumberService;
import com.lottery.service.MostFrequentlyOccuringPairsService;
import com.lottery.service.RearestFiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 * Lottery REST végpontokat tartalmazó Controller osztály
 */
@RestController
@Api(value = "/lottery", description = "Lottery Rules")
@RequestMapping(value = "/lottery")
public class LotteryController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

    private MostFrequentFiveNumberService mostFrequentFiveNumberService;
    private FourMatchRatioToFiveService fourMatchRatioToFiveService;
    private RearestFiveService rearestFiveService;
    private AverageService averageService;
    private MostFrequentlyOccuringPairsService mostFrequentlyOccuringPairsService;
    private AverageTimeBetweenTwoMatchFiveDrawsService averageTimeBetweenTwoMatchFiveDrawsService;
    private AddingWeeklyDrawService addingWeeklyDrawService;
    private DateIntervalService dateIntervalService;
    private WeeklyDrawJPARepository weeklyDrawJPARepository;
    private UserJPARepository userJPARepository;

    @Autowired
    public LotteryController(MostFrequentFiveNumberService mostFrequentFiveNumberService,
                             FourMatchRatioToFiveService fourMatchRatioToFiveService,
                             RearestFiveService rearestFiveService,
                             AverageService averageService,
                             MostFrequentlyOccuringPairsService mostFrequentlyOccuringPairsService,
                             AverageTimeBetweenTwoMatchFiveDrawsService averageTimeBetweenTwoMatchFiveDrawsService,
                             AddingWeeklyDrawService addingWeeklyDrawService,
                             DateIntervalService dateIntervalService,
                             WeeklyDrawJPARepository weeklyDrawJPARepository,
                             UserJPARepository userJPARepository) {
        this.mostFrequentFiveNumberService = mostFrequentFiveNumberService;
        this.fourMatchRatioToFiveService = fourMatchRatioToFiveService;
        this.rearestFiveService = rearestFiveService;
        this.averageService = averageService;
        this.mostFrequentlyOccuringPairsService = mostFrequentlyOccuringPairsService;
        this.averageTimeBetweenTwoMatchFiveDrawsService = averageTimeBetweenTwoMatchFiveDrawsService;
        this.addingWeeklyDrawService = addingWeeklyDrawService;
        this.dateIntervalService = dateIntervalService;
        this.weeklyDrawJPARepository = weeklyDrawJPARepository;
        this.userJPARepository = userJPARepository;
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/most-frequent-five-number")
    @ApiOperation(value = "GET most frequent five num", notes = "gets the five most frequently drawn numbers")
    public MostFrequentFiveNumberResult getMostFrequentFiveNumber() {
        return this.mostFrequentFiveNumberService.executeRule();
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/most-frequent-five-number/{from}/{to}")
    @ApiOperation(value = "GET most frequent five num filtered by date",
                  notes = "gets the five most frequently drawn numbers between given date")
    public MostFrequentFiveNumberResult getMostFrequentFiveNumberFiltered(
            @ApiParam(value = "filter date from (yyyy-mm-dd)", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to (yyyy-mm-dd)", required = true) @PathVariable("to") String to) {
        return this.mostFrequentFiveNumberService.executeRuleFilterByDate(from, to);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET four match ratio to five",
                  notes = "gets the ratio of four matches compare to five matches")
    @GetMapping("/four-match-ratio-to-five-match")
    public FourMatchRatioToFiveMatchResult getFourMatchRatioToFiveMatch() {
        return this.fourMatchRatioToFiveService.executeRule();
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/four-match-ratio-to-five-match/{from}/{to}")
    @ApiOperation(value = "GET four match ratio to five filtered by date",
                  notes = "gets the ratio of four matches compare to five matches between given date")
    public FourMatchRatioToFiveMatchResult getFourMatchRatioToFiveMatchFiltered(
            @ApiParam(value = "filter date from (yyyy-mm-dd)", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to (yyyy-mm-dd)", required = true) @PathVariable("to") String to) {
        return this.fourMatchRatioToFiveService.executeRuleFilterByDate(from, to);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET rearest five nums", notes = "gets the five rarest drawn numbers")
    @GetMapping("/rearest-five-number")
    public RearestFiveResult getRearestFiveNumber() {
        return this.rearestFiveService.executeRule();
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/rearest-five-number/{from}/{to}")
    @ApiOperation(value = "GET rearest five nums filtered by date",
                  notes = "gets the five rarest drawn numbers between given date")
    public RearestFiveResult getRearestFiveNumberFiltered(
            @ApiParam(value = "filter date from (yyyy-mm-dd)", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to (yyyy-mm-dd)", required = true) @PathVariable("to") String to) {
        return this.rearestFiveService.executeRuleFilterByDate(from, to);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET average num", notes = "GET average num")
    @GetMapping("/average")
    public AverageResult getAverage() {
        return this.averageService.executeRule();
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/average/{from}/{to}")
    @ApiOperation(value = "GET average num filtered by date", notes = "gets average num between given date")
    public AverageResult getAverageFiltered(
            @ApiParam(value = "filter date from (yyyy-mm-dd)", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to (yyyy-mm-dd)", required = true) @PathVariable("to") String to) {
        return this.averageService.executeRuleFilterByDate(from, to);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET most frequent pairs", notes = "gets number pairs that are most frequently drawn")
    @GetMapping("/most-frequently-occuring-pairs")
    public DrawsInTwoDimension getMostFrequentlyOccuringFiveNumber() {
        return this.mostFrequentlyOccuringPairsService.executeRule();
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/most-frequently-occuring-pairs/{from}/{to}")
    @ApiOperation(value = "GET most frequent pairs filtered by date",
                  notes = "gets number pairs that are most frequently drawn between given date")
    public DrawsInTwoDimension getMostFrequentlyOccuringFiveNumberFiltered(
            @ApiParam(value = "filter date from (yyyy-mm-dd)", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to (yyyy-mm-dd)", required = true) @PathVariable("to") String to) {
        return this.mostFrequentlyOccuringPairsService.executeRuleFilterByDate(from, to);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET average time between 5 matches",
                  notes = "gets the average time passed between two five matches")
    @GetMapping("/average-time-between-two-match-five-draws")
    public AverageTimeBetweenTwoMatchFiveDrawsResult getAverageTimeBetweenTwoMatchFiveDraws() {
        return this.averageTimeBetweenTwoMatchFiveDrawsService.executeRule();
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/average-time-between-two-match-five-draws/{from}/{to}")
    @ApiOperation(value = "GET average time between 5 matches filtered by date",
                  notes = "gets the average time passed between two five matches between given date")
    public AverageTimeBetweenTwoMatchFiveDrawsResult getAverageTimeBetweenTwoMatchFiveDrawsFiltered(
            @ApiParam(value = "filter date from (yyyy-mm-dd)", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to (yyyy-mm-dd)", required = true) @PathVariable("to") String to) {
        return this.averageTimeBetweenTwoMatchFiveDrawsService.executeRuleFilterByDate(from, to);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @PostMapping("/add-new-weeklydraw")
    @ApiOperation(value = "POST new draw", notes = "adding new weekly draw results")
    public WeeklyDraw addingNewWeeklyDraw(
            @ApiParam(value = "weeklyDraw object with fields", required = true) @Valid @RequestBody WeeklyDraw input){
        this.addingWeeklyDrawService.AddNewWeeklyDraw(input);
        return input;
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET min/max dates", notes = "getting earliest and latest dates")
    @GetMapping("/get-date-intervals")
    public String getDateIntervals() {
        Date firstDate = this.dateIntervalService.getStart();
        Date lastDate = this.dateIntervalService.getEnd();
        return "Lottery weekly draws are available from: " + firstDate + " to: " + lastDate;
    }

    @ApiOperation(value = "GET table from csv", notes = "Make weekly draw table from csv! Restart server after this!")
    @GetMapping("/table_from_csv")
    public String tableFromCsv() {
        PersistFromCsv persistFromCsv = new PersistFromCsv(weeklyDrawJPARepository);
        persistFromCsv.readAndPersist();
        Lottery lottery = new Lottery();
        lottery.setLotteryList();
        lottery.getLotteryList().addAll(weeklyDrawJPARepository.findAll());
        return "OK";
    }
}


