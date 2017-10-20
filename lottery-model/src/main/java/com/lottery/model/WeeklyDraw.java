package com.lottery.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Heti kihúzott lottó számok osztálya húzás dátummal, találatokkal, nyeremények összegével és a kihúzott 5 számmal,
 * amit tömben tárolunk
 */
public class WeeklyDraw implements Serializable {

    private static final long serialVersionUID = 1L;

    //TODO Entity gyártás
    private SimpleDateFormat drawDate;
    private String id;
    private Integer year;
    private Integer week;
    private Integer fiveMatch;
    private Long fiveMatchPrize;
    private Integer fourMatch;
    private Long fourMatchPrize;
    private Integer threeMatch;
    private Long threeMatchPrize;
    private Integer twoMatch;
    private Long twoMatchPrize;
    private Integer[] drawnNumbers;

    public WeeklyDraw() {
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public SimpleDateFormat getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(SimpleDateFormat drawDate) {
        this.drawDate = drawDate;
    }

    public Integer getFiveMatch() {
        return fiveMatch;
    }

    public void setFiveMatch(Integer fiveMatch) {
        this.fiveMatch = fiveMatch;
    }

    public Long getFiveMatchPrize() {
        return fiveMatchPrize;
    }

    public void setFiveMatchPrize(Long fiveMatchPrize) {
        this.fiveMatchPrize = fiveMatchPrize;
    }

    public Integer getFourMatch() {
        return fourMatch;
    }

    public void setFourMatch(Integer fourMatch) {
        this.fourMatch = fourMatch;
    }

    public Long getFourMatchPrize() {
        return fourMatchPrize;
    }

    public void setFourMatchPrize(Long fourMatchPrize) {
        this.fourMatchPrize = fourMatchPrize;
    }

    public Integer getThreeMatch() {
        return threeMatch;
    }

    public void setThreeMatch(Integer threeMatch) {
        this.threeMatch = threeMatch;
    }

    public Long getThreeMatchPrize() {
        return threeMatchPrize;
    }

    public void setThreeMatchPrize(Long threeMatchPrize) {
        this.threeMatchPrize = threeMatchPrize;
    }

    public Integer getTwoMatch() {
        return twoMatch;
    }

    public void setTwoMatch(Integer twoMatch) {
        this.twoMatch = twoMatch;
    }

    public Long getTwoMatchPrize() {
        return twoMatchPrize;
    }

    public void setTwoMatchPrize(Long twoMatchPrize) {
        this.twoMatchPrize = twoMatchPrize;
    }

    public Integer[] getDrawnNumbers() {
        return drawnNumbers;
    }

    public void setDrawnNumbers(Integer[] drawnNumbers) {
        this.drawnNumbers = drawnNumbers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WeeklyDraw{" + "id='" + id + '\'' + ", year='" + year + '\'' + ", week=" + week + '}';
    }
}
