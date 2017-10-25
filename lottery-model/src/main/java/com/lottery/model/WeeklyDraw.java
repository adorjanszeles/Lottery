package com.lottery.model;

import com.lottery.model.validator.DrawnNumbers;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Entity
@Table(name = "WEEKLY_DRAW")
public class WeeklyDraw implements Serializable {
    //TODO fields for refactor
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dbId;

    @Transient
    private String id;

    @Transient
    @NotNull(message = "Empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date drawDate;

    @Column(name = "five_match")
    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Integer fiveMatch;

    @Column(name = "five_match_prize")
    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Long fiveMatchPrize;

    @Column(name = "four_match")
    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Integer fourMatch;

    @Column(name = "four_match_prize")
    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Long fourMatchPrize;

    @Column(name = "three_match")
    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Integer threeMatch;

    @Column(name = "three_match_prize")
    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Long threeMatchPrize;

    @Column(name = "two_match")
    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Integer twoMatch;

    @Column(name = "two_match_prize")
    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Long twoMatchPrize;

    @NotNull(message = "Empty")
    @Size(message = "enter 5 numbers", min = 5, max = 5)
    @NotEmpty(message = "enter numbers")

    @Transient
    @DrawnNumbers
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
