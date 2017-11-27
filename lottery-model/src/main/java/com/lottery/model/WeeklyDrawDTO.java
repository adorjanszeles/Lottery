package com.lottery.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

/**
 * WeeklyDraw Data Transfer Object osztály. Az instance-aiból validált Weeklydraw Entity instance-ok lesznek.
 */
public class WeeklyDrawDTO {

    private Date drawDate;
    private Integer fiveMatch;
    private Long fiveMatchPrize;
    private Integer fourMatch;
    private Long fourMatchPrize;
    private Integer threeMatch;
    private Long threeMatchPrize;
    private Integer twoMatch;
    private Long twoMatchPrize;
    private Integer first;
    private Integer second;
    private Integer third;
    private Integer fourth;
    private Integer fifth;

    public WeeklyDrawDTO() {
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public @NotNull(message = "Empty") @Past Date getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
    }

    public @NotNull(message = "Empty") @PositiveOrZero(message = "enter positive numbers") Integer getFiveMatch() {
        return fiveMatch;
    }

    public void setFiveMatch(Integer fiveMatch) {
        this.fiveMatch = fiveMatch;
    }

    public @NotNull(message = "Empty") @PositiveOrZero(message = "enter positive numbers") Long getFiveMatchPrize() {
        return fiveMatchPrize;
    }

    public void setFiveMatchPrize(Long fiveMatchPrize) {
        this.fiveMatchPrize = fiveMatchPrize;
    }

    public @NotNull(message = "Empty") @PositiveOrZero(message = "enter positive numbers") Integer getFourMatch() {
        return fourMatch;
    }

    public void setFourMatch(Integer fourMatch) {
        this.fourMatch = fourMatch;
    }

    public @NotNull(message = "Empty") @PositiveOrZero(message = "enter positive numbers") Long getFourMatchPrize() {
        return fourMatchPrize;
    }

    public void setFourMatchPrize(Long fourMatchPrize) {
        this.fourMatchPrize = fourMatchPrize;
    }

    public @NotNull(message = "Empty") @PositiveOrZero(message = "enter positive numbers") Integer getThreeMatch() {
        return threeMatch;
    }

    public void setThreeMatch(Integer threeMatch) {
        this.threeMatch = threeMatch;
    }

    public @NotNull(message = "Empty") @PositiveOrZero(message = "enter positive numbers") Long getThreeMatchPrize() {
        return threeMatchPrize;
    }

    public void setThreeMatchPrize(Long threeMatchPrize) {
        this.threeMatchPrize = threeMatchPrize;
    }

    public @NotNull(message = "Empty") @PositiveOrZero(message = "enter positive numbers") Integer getTwoMatch() {
        return twoMatch;
    }

    public void setTwoMatch(Integer twoMatch) {
        this.twoMatch = twoMatch;
    }

    public @NotNull(message = "Empty") @PositiveOrZero(message = "enter positive numbers") Long getTwoMatchPrize() {
        return twoMatchPrize;
    }

    public void setTwoMatchPrize(Long twoMatchPrize) {
        this.twoMatchPrize = twoMatchPrize;
    }

    public @NotNull(message = "Empty") @Min(value = 1) @Max(value = 90) Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public @NotNull(message = "Empty") @Min(value = 1) @Max(value = 90) Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public @NotNull(message = "Empty") @Min(value = 1) @Max(value = 90) Integer getThird() {
        return third;
    }

    public void setThird(Integer third) {
        this.third = third;
    }

    public @NotNull(message = "Empty") @Min(value = 1) @Max(value = 90) Integer getFourth() {
        return fourth;
    }

    public void setFourth(Integer fourth) {
        this.fourth = fourth;
    }

    public @NotNull(message = "Empty") @Min(value = 1) @Max(value = 90) Integer getFifth() {
        return fifth;
    }

    public void setFifth(Integer fifth) {
        this.fifth = fifth;
    }

    @Override
    public String toString() {
        return "WeeklyDrawDTO{" + "drawDate=" + drawDate + ", fiveMatch=" + fiveMatch + ", fiveMatchPrize=" +
               fiveMatchPrize + ", fourMatch=" + fourMatch + ", fourMatchPrize=" + fourMatchPrize + ", threeMatch=" +
               threeMatch + ", threeMatchPrize=" + threeMatchPrize + ", twoMatch=" + twoMatch + ", twoMatchPrize=" +
               twoMatchPrize + ", first=" + first + ", second=" + second + ", third=" + third + ", fourth=" + fourth +
               ", fifth=" + fifth + '}';
    }
}
