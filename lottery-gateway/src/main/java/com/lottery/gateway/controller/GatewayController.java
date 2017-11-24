package com.lottery.gateway.controller;

import com.lottery.gateway.exceptions.InvalidDateException;
import com.lottery.gateway.service.AddingWeeklyDrawService;
import com.lottery.gateway.service.AuthService;
import com.lottery.gateway.service.AverageService;
import com.lottery.gateway.service.AverageTimeBetweenTwoMatchFiveDrawsService;
import com.lottery.gateway.service.CheckDateFromToService;
import com.lottery.gateway.service.CheckDateFromToServiceImpl;
import com.lottery.gateway.service.DateIntervalsService;
import com.lottery.gateway.service.FourMatchRatioToFiveService;
import com.lottery.gateway.service.MinDateService;
import com.lottery.gateway.service.MostFrequentFiveNumberService;
import com.lottery.gateway.service.MostFrequentPairsService;
import com.lottery.gateway.service.RarestFiveService;
import com.lottery.model.AverageResult;
import com.lottery.model.AverageTimeBetweenTwoMatchFiveDrawsResult;
import com.lottery.model.FourMatchRatioToFiveMatchResult;
import com.lottery.model.MostFrequentFiveNumberResult;
import com.lottery.model.RearestFiveResult;
import com.lottery.model.WeeklyDrawDTO;
import com.lottery.model.mfop_utils.DrawsInTwoDimension;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;

/**
 * Lottery REST végpontokat tartalmazó Controller osztály
 */
@RestController
@Api(value = "/gateway", description = "Lottery Rules Gateway")
@RequestMapping(value = "/gateway")
@PropertySource("classpath:lottery-web.properties")
public class GatewayController {

    private AverageService averageService;
    private AverageTimeBetweenTwoMatchFiveDrawsService averageTimeBetweenTwoMatchFiveDrawsService;
    private MostFrequentFiveNumberService mostFrequentFiveNumberService;
    private MostFrequentPairsService mostFrequentPairsService;
    private FourMatchRatioToFiveService fourMatchRatioToFiveService;
    private RarestFiveService rarestFiveService;
    private DateIntervalsService dateIntervalsService;
    private AddingWeeklyDrawService addingWeeklyDrawService;
    private MinDateService minDateService;
    private CheckDateFromToService checkDateFromToService;

    private Environment env;

    @Autowired
    public GatewayController(AverageService averageService,
                             AverageTimeBetweenTwoMatchFiveDrawsService averageTimeBetweenTwoMatchFiveDrawsService,
                             MostFrequentFiveNumberService mostFrequentFiveNumberService,
                             MostFrequentPairsService mostFrequentPairsService,
                             FourMatchRatioToFiveService fourMatchRatioToFiveService,
                             RarestFiveService rarestFiveService,
                             DateIntervalsService dateIntervalsService,
                             AddingWeeklyDrawService addingWeeklyDrawService,
                             MinDateService minDateService,
                             CheckDateFromToService checkDateFromToService,
                             Environment env) {
        this.averageService = averageService;
        this.averageTimeBetweenTwoMatchFiveDrawsService = averageTimeBetweenTwoMatchFiveDrawsService;
        this.mostFrequentFiveNumberService = mostFrequentFiveNumberService;
        this.mostFrequentPairsService = mostFrequentPairsService;
        this.fourMatchRatioToFiveService = fourMatchRatioToFiveService;
        this.rarestFiveService = rarestFiveService;
        this.dateIntervalsService = dateIntervalsService;
        this.addingWeeklyDrawService = addingWeeklyDrawService;
        this.minDateService = minDateService;
        this.checkDateFromToService = checkDateFromToService;
        this.env = env;
    }

    // ####################### AVERAGE ##############################

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET average num", notes = "GET average num")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    @GetMapping("/average")
    public AverageResult getAverage(@RequestHeader("Authorization") String access_token) {
        String url = this.env.getProperty("lottery-web.average");
        return this.averageService.getAverageResult(url, access_token);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/average/{from}/{to}")
    @ApiOperation(value = "GET average num filtered by date", notes = "gets average num between given date")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    public AverageResult getAverageFiltered(
            @ApiParam(value = "filter date from (yyyy-mm-dd)", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to (yyyy-mm-dd)", required = true) @PathVariable("to") String to,
            @RequestHeader("Authorization") String access_token) throws ParseException, InvalidDateException {
        this.checkDateFromToService.checkFromTo(from, to, access_token);
        String url = this.env.getProperty("lottery-web.average") + String.format("/%s/%s", from, to);
        return this.averageService.getAverageResult(url, access_token);
    }

    // ####################### MOST FREQUENT FIVE ##############################

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET average num", notes = "GET average num")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    @GetMapping("/most-frequent-five-number")
    public MostFrequentFiveNumberResult getMostFrequentFive(@RequestHeader("Authorization") String access_token) {
        String url = this.env.getProperty("lottery-web.most-frequent-five-number");
        return this.mostFrequentFiveNumberService.getMostFrequentFiveNumbers(url, access_token);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/most-frequent-five-number/{from}/{to}")
    @ApiOperation(value = "GET most frequent five number filtered by date",
                  notes = "gets most frequent five numbers num between given date")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    public MostFrequentFiveNumberResult getMostFrequentFiveFiltered(
            @ApiParam(value = "filter date from (yyyy-mm-dd)", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to (yyyy-mm-dd)", required = true) @PathVariable("to") String to,
            @RequestHeader("Authorization") String access_token) throws ParseException, InvalidDateException {
        this.checkDateFromToService.checkFromTo(from, to, access_token);
        String url = this.env.getProperty("lottery-web.most-frequent-five-number") + String.format("/%s/%s", from, to);
        return this.mostFrequentFiveNumberService.getMostFrequentFiveNumbers(url, access_token);
    }

    // ####################### FOUR MATCH RATIO TO FIVE ##############################

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET four match ratio to five",
                  notes = "gets the ratio of four matches compare to five matches")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    @GetMapping("/four-match-ratio-to-five-match")
    public FourMatchRatioToFiveMatchResult getFourMatchRatioToFive(
            @RequestHeader("Authorization") String access_token) {
        String url = this.env.getProperty("lottery-web.four-match-ratio-to-five-match");
        return this.fourMatchRatioToFiveService.getFourMatchRatioToFive(url, access_token);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/four-match-ratio-to-five-match/{from}/{to}")
    @ApiOperation(value = "GET four match ratio to five filtered by date",
                  notes = "gets the ratio of four matches compare to five matches between given date")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    public FourMatchRatioToFiveMatchResult getFourMatchRatioToFiveFiltered(
            @ApiParam(value = "filter date from (yyyy-mm-dd)", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to (yyyy-mm-dd)", required = true) @PathVariable("to") String to,
            @RequestHeader("Authorization") String access_token) throws ParseException, InvalidDateException {
        this.checkDateFromToService.checkFromTo(from, to, access_token);
        String url =
                this.env.getProperty("lottery-web.four-match-ratio-to-five-match") + String.format("/%s/%s", from, to);
        return this.fourMatchRatioToFiveService.getFourMatchRatioToFive(url, access_token);
    }

    // ####################### RAREST FIVE NUMBER ##############################

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET rearest five nums", notes = "gets the five rarest drawn numbers")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    @GetMapping("/rearest-five-number")
    public RearestFiveResult getRearestFiveNumber(@RequestHeader("Authorization") String access_token) {
        String url = this.env.getProperty("lottery-web.rearest-five-number");
        return this.rarestFiveService.getRearestFiveNumber(url, access_token);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/rearest-five-number/{from}/{to}")
    @ApiOperation(value = "GET rearest five nums filtered by date",
                  notes = "gets the five rarest drawn numbers between given date")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    public RearestFiveResult getRearestFiveNumberFiltered(
            @ApiParam(value = "filter date from (yyyy-mm-dd)", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to (yyyy-mm-dd)", required = true) @PathVariable("to") String to,
            @RequestHeader("Authorization") String access_token) throws ParseException, InvalidDateException {
        this.checkDateFromToService.checkFromTo(from, to, access_token);
        String url = this.env.getProperty("lottery-web.rearest-five-number") + String.format("/%s/%s", from, to);
        return this.rarestFiveService.getRearestFiveNumber(url, access_token);
    }

    // ####################### MOST FREQUENT PAIRS ##############################

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET most frequent pairs", notes = "gets number pairs that are most frequently drawn")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    @GetMapping("/most-frequently-occuring-pairs")
    public DrawsInTwoDimension getMostFrequentPairs(@RequestHeader("Authorization") String access_token) {
        String url = this.env.getProperty("lottery-web.most-frequently-occuring-pairs");
        return this.mostFrequentPairsService.getMostFrequentPairs(url, access_token);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/most-frequently-occuring-pairs/{from}/{to}")
    @ApiOperation(value = "GET most frequent pairs filtered by date",
                  notes = "gets number pairs that are most frequently drawn between given date")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    public DrawsInTwoDimension getMostFrequentPairsFiltered(
            @ApiParam(value = "filter date from (yyyy-mm-dd)", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to (yyyy-mm-dd)", required = true) @PathVariable("to") String to,
            @RequestHeader("Authorization") String access_token) throws ParseException, InvalidDateException {
        this.checkDateFromToService.checkFromTo(from, to, access_token);
        String url =
                this.env.getProperty("lottery-web.most-frequently-occuring-pairs") + String.format("/%s/%s", from, to);
        return this.mostFrequentPairsService.getMostFrequentPairs(url, access_token);
    }

    // ####################### AVERAGE TIME BETWEEN FIVE MATCHES ##############################

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET average time between 5 matches",
                  notes = "gets the average time passed between two five matches")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    @GetMapping("/average-time-between-two-match-five-draws")
    public AverageTimeBetweenTwoMatchFiveDrawsResult getAverageTimeBetweenTwoMatchFiveDraws(
            @RequestHeader("Authorization") String access_token) {
        String url = this.env.getProperty("lottery-web.average-time-between-two-match-five-draws");
        return this.averageTimeBetweenTwoMatchFiveDrawsService.getAverageTimeBetweenTwoMatchFiveDraws(url,
                                                                                                      access_token);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/average-time-between-two-match-five-draws/{from}/{to}")
    @ApiOperation(value = "GET average time between 5 matches filtered by date",
                  notes = "gets the average time passed between two five matches between given date")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    public AverageTimeBetweenTwoMatchFiveDrawsResult getAverageTimeBetweenTwoMatchFiveDrawsFiltered(
            @ApiParam(value = "filter date from (yyyy-mm-dd)", required = true) @PathVariable("from") String from,
            @ApiParam(value = "filter date to (yyyy-mm-dd)", required = true) @PathVariable("to") String to,
            @RequestHeader("Authorization") String access_token) throws ParseException, InvalidDateException {
        this.checkDateFromToService.checkFromTo(from, to, access_token);
        String url = this.env.getProperty("lottery-web.average-time-between-two-match-five-draws") +
                     String.format("/%s/%s", from, to);
        return this.averageTimeBetweenTwoMatchFiveDrawsService.getAverageTimeBetweenTwoMatchFiveDraws(url,
                                                                                                      access_token);
    }

    // ####################### GET DATE INTERVALS ##############################

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "GET min/max dates", notes = "getting earliest and latest dates")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    @GetMapping("/get-date-intervals")
    public String getDateIntervals(@RequestHeader("Authorization") String access_token) {
        String url = this.env.getProperty("lottery-web.get-date-intervals");
        return this.dateIntervalsService.getDateIntervals(url, access_token);
    }

    @GetMapping("/get-min-date")
    public String getMinDate(@RequestHeader("Authorization") String access_token) {
        String url = this.env.getProperty("lottery-web.get-min-date");
        return this.minDateService.getMinDate(url, access_token);
    }

    // ####################### ADD NEW WEEKLYDRAW ##############################

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @ApiOperation(value = "POST new draw", notes = "adding new weekly draw results")
    @ApiImplicitParam(name = "Authorization",
                      value = "Authorization",
                      required = true,
                      dataType = "string",
                      paramType = "header")
    @PostMapping("/add-new-weeklydraw")
    public WeeklyDrawDTO addNewWeeklyDraw(@RequestHeader("Authorization") String access_token,
                                          @ApiParam(value = "weeklyDraw object with fields", required = true)
                                          @RequestBody WeeklyDrawDTO input) {
        String url = this.env.getProperty("lottery-web.add-new-weeklydraw");
        return this.addingWeeklyDrawService.addNewWeeklyDraw(url, access_token, input);
    }

}