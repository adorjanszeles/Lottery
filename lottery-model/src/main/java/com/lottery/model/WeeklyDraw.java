package com.lottery.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Heti kihúzott lottó számok osztálya húzás dátummal, találatokkal, nyeremények összegével és a kihúzott 5 számmal,
 * amit tömben tárolunk
 */

public class WeeklyDraw implements Serializable {

    private String id;

    @NotNull(message = "Empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date drawDate;

    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")

    private Integer fiveMatch;

    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Long fiveMatchPrize;

    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Integer fourMatch;

    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Long fourMatchPrize;

    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Integer threeMatch;

    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Long threeMatchPrize;

    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Integer twoMatch;

    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Long twoMatchPrize;

    @NotNull(message = "Empty")
    @Size(message = "enter 5 numbers", min = 5, max = 5)
    @NotEmpty(message = "enter numbers")
    private Integer[] drawnNumbers;

    public WeeklyDraw() {
    }

    public Date getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(Date drawDate) {
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
        return "WeeklyDraw{" + "id='" + this.id + '\'' + ", date='" + this.drawDate + '}';
    }
}
