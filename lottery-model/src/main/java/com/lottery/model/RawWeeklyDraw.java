package com.lottery.model;

/**
 * A CSV-ből beolvasott kezeletlen instance-okat leíró osztály
 */
public class RawWeeklyDraw {

    private String year;
    private String week;
    private String drawDate;
    private String fiveMatch;
    private String fiveMatchPrize;
    private String fourMatch;
    private String fourMatchPrize;
    private String threeMatch;
    private String threeMatchPrize;
    private String twoMatch;
    private String twoMatchPrize;
    private String first;
    private String second;
    private String third;
    private String fourth;
    private String fifth;

    protected RawWeeklyDraw(){}

    public RawWeeklyDraw(String year,
                         String week,
                         String drawDate,
                         String fiveMatch,
                         String fiveMatchPrize,
                         String fourMatch,
                         String fourMatchPrize,
                         String threeMatch,
                         String threeMatchPrize,
                         String twoMatch,
                         String twoMatchPrize,
                         String first,
                         String second,
                         String third,
                         String fourth,
                         String fifth) {
        this.year = year;
        this.week = week;
        this.drawDate = drawDate;
        this.fiveMatch = fiveMatch;
        this.fiveMatchPrize = fiveMatchPrize;
        this.fourMatch = fourMatch;
        this.fourMatchPrize = fourMatchPrize;
        this.threeMatch = threeMatch;
        this.threeMatchPrize = threeMatchPrize;
        this.twoMatch = twoMatch;
        this.twoMatchPrize = twoMatchPrize;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public String getFifth() {
        return fifth;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }

    public String getFiveMatch() {
        return fiveMatch;
    }

    public void setFiveMatch(String fiveMatch) {
        this.fiveMatch = fiveMatch;
    }

    public String getFiveMatchPrize() {
        return fiveMatchPrize;
    }

    public void setFiveMatchPrize(String fiveMatchPrize) {
        this.fiveMatchPrize = fiveMatchPrize;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFourMatch() {
        return fourMatch;
    }

    public void setFourMatch(String fourMatch) {
        this.fourMatch = fourMatch;
    }

    public String getFourMatchPrize() {
        return fourMatchPrize;
    }

    public void setFourMatchPrize(String fourMatchPrize) {
        this.fourMatchPrize = fourMatchPrize;
    }

    public String getThreeMatch() {
        return threeMatch;
    }

    public void setThreeMatch(String threeMatch) {
        this.threeMatch = threeMatch;
    }

    public String getThreeMatchPrize() {
        return threeMatchPrize;
    }

    public void setThreeMatchPrize(String threeMatchPrize) {
        this.threeMatchPrize = threeMatchPrize;
    }

    public String getTwoMatch() {
        return twoMatch;
    }

    public void setTwoMatch(String twoMatch) {
        this.twoMatch = twoMatch;
    }

    public String getTwoMatchPrize() {
        return twoMatchPrize;
    }

    public void setTwoMatchPrize(String twoMatchPrize) {
        this.twoMatchPrize = twoMatchPrize;
    }

    @Override
    public String toString() {
        return "RawWeeklyDraw{" + '\'' + ", year='" + year + '\'' + ", week=" + week + ", date=" + drawDate + '}';
    }
}
