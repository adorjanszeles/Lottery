package com.lottery.model;

import com.lottery.model.validator.DrawnNumbers;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    private Long id;

    @Column(name = "REDIS_ID")
    private String redis_id;

    //    @NotNull(message = "Empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "DRAWDATE")
    private Date drawDate;

    @Column(name = "FIVE_MATCH")
    //    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Integer fiveMatch;

    @Column(name = "FIVE_MATCH_PRIZE")
    //    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Long fiveMatchPrize;

    @Column(name = "FOUR_MATCH")
    //    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Integer fourMatch;

    @Column(name = "FOUR_MATCH_PRIZE")
    //    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Long fourMatchPrize;

    @Column(name = "THREE_MATCH")
    //    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Integer threeMatch;

    @Column(name = "THREE_MATCH_PRIZE")
    //    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Long threeMatchPrize;

    @Column(name = "TWO_MATCH")
    //    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Integer twoMatch;

    @Column(name = "TWO_MATCH_PRIZE")
    //    @NotNull(message = "Empty")
    @PositiveOrZero(message = "enter positive numbers")
    private Long twoMatchPrize;

    //    @NotNull(message = "Empty")
//    @Size(message = "enter 5 numbers", min = 5, max = 5)
    //    @NotEmpty(message = "enter numbers")

    @Column(name = "FIRST")
    private Integer first;

    @Column(name = "SECOND")
    private Integer second;

    @Column(name = "THIRD")
    private Integer third;

    @Column(name = "FOURTH")
    private Integer fourth;

    @Column(name = "FIFTH")
    private Integer fifth;

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
        Integer[] drawnNumbers = new Integer[5];
        drawnNumbers[0] = this.first;
        drawnNumbers[1] = this.second;
        drawnNumbers[2] = this.third;
        drawnNumbers[3] = this.fourth;
        drawnNumbers[4] = this.fifth;

        return drawnNumbers;
    }

    public void setDrawnNumbers(Integer[] numbersList){

        this.first = numbersList[0];
        this.second = numbersList[1];
        this.third = numbersList[2];
        this.fourth = numbersList[3];
        this.fifth = numbersList[4];

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getThird() {
        return third;
    }

    public void setThird(Integer third) {
        this.third = third;

    }

    public Integer getFourth() {
        return fourth;
    }

    public void setFourth(Integer fourth) {
        this.fourth = fourth;

    }

    public Integer getFifth() {
        return fifth;
    }

    public void setFifth(Integer fifth) {
        this.fifth = fifth;
    }

    public String getRedisId() {
        return redis_id;
    }

    public void setRedisId(String redis_id) {
        this.redis_id = redis_id;
    }

    @Override
    public String toString() {
        return "WeeklyDraw{" + "dbId=" + id + ", id='" + id + '\'' + ", drawDate=" + drawDate + ", fiveMatch=" +
               fiveMatch + ", fiveMatchPrize=" + fiveMatchPrize + ", fourMatch=" + fourMatch + ", fourMatchPrize=" +
               fourMatchPrize + ", threeMatch=" + threeMatch + ", threeMatchPrize=" + threeMatchPrize + ", twoMatch=" +
               twoMatch + ", twoMatchPrize=" + twoMatchPrize + ", first=" + first + ", second=" + second + ", third=" +
               third + ", fourth=" + fourth + ", fifth=" + fifth + '}';
    }
}
