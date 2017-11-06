package com.lottery.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Heti kihúzott lottó számok osztálya húzás dátummal, találatokkal, nyeremények összegével és a kihúzott 5 számmal,
 * amit tömben tárolunk
 */
@Entity
@Table(name = "WEEKLY_DRAW")
public class WeeklyDraw implements Serializable {

    private Long id;
    private String redisId;
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

    public WeeklyDraw() {

    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DRAWDATE")
    public Date getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
    }

    @Column(name = "FIVE_MATCH")
    public Integer getFiveMatch() {
        return fiveMatch;
    }

    public void setFiveMatch(Integer fiveMatch) {
        this.fiveMatch = fiveMatch;
    }

    @Column(name = "FIVE_MATCH_PRIZE")
    public Long getFiveMatchPrize() {
        return fiveMatchPrize;
    }

    public void setFiveMatchPrize(Long fiveMatchPrize) {
        this.fiveMatchPrize = fiveMatchPrize;
    }

    @Column(name = "FOUR_MATCH")
    public Integer getFourMatch() {
        return fourMatch;
    }

    public void setFourMatch(Integer fourMatch) {
        this.fourMatch = fourMatch;
    }

    @Column(name = "FOUR_MATCH_PRIZE")
    public Long getFourMatchPrize() {
        return fourMatchPrize;
    }

    public void setFourMatchPrize(Long fourMatchPrize) {
        this.fourMatchPrize = fourMatchPrize;
    }

    @Column(name = "THREE_MATCH")
    public Integer getThreeMatch() {
        return threeMatch;
    }

    public void setThreeMatch(Integer threeMatch) {
        this.threeMatch = threeMatch;
    }

    @Column(name = "THREE_MATCH_PRIZE")
    public Long getThreeMatchPrize() {
        return threeMatchPrize;
    }

    public void setThreeMatchPrize(Long threeMatchPrize) {
        this.threeMatchPrize = threeMatchPrize;
    }

    @Column(name = "TWO_MATCH")
    public Integer getTwoMatch() {
        return twoMatch;
    }

    public void setTwoMatch(Integer twoMatch) {
        this.twoMatch = twoMatch;
    }

    @Column(name = "TWO_MATCH_PRIZE")
    public Long getTwoMatchPrize() {
        return twoMatchPrize;
    }

    public void setTwoMatchPrize(Long twoMatchPrize) {
        this.twoMatchPrize = twoMatchPrize;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "FIRST")
    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    @Column(name = "SECOND")
    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    @Column(name = "THIRD")
    public Integer getThird() {
        return third;
    }

    public void setThird(Integer third) {
        this.third = third;

    }

    @Column(name = "FOURTH")
    public Integer getFourth() {
        return fourth;
    }

    public void setFourth(Integer fourth) {
        this.fourth = fourth;

    }

    @Column(name = "FIFTH")
    public Integer getFifth() {
        return fifth;
    }

    public void setFifth(Integer fifth) {
        this.fifth = fifth;
    }

    @Column(name = "REDIS_ID")
    public String getRedisId() {
        return redisId;
    }

    public void setRedisId(String redisId) {
        this.redisId = redisId;
    }

    public Integer[] generateDrawnNumbers() {
        Integer[] drawnNumbers = new Integer[5];
        drawnNumbers[0] = this.first;
        drawnNumbers[1] = this.second;
        drawnNumbers[2] = this.third;
        drawnNumbers[3] = this.fourth;
        drawnNumbers[4] = this.fifth;

        return drawnNumbers;
    }

    public void fillDrawnNumbers(Integer[] numbersList) {

        this.first = numbersList[0];
        this.second = numbersList[1];
        this.third = numbersList[2];
        this.fourth = numbersList[3];
        this.fifth = numbersList[4];

    }

    @Override
    public String toString() {
        return "WeeklyDraw{" + ", redisId='" + redisId + '\'' + ", drawDate=" + drawDate + ", fiveMatch=" +
                fiveMatch + ", fiveMatchPrize=" + fiveMatchPrize + ", fourMatch=" + fourMatch + ", fourMatchPrize=" +
                fourMatchPrize + ", threeMatch=" + threeMatch + ", threeMatchPrize=" + threeMatchPrize + ", twoMatch=" +
                twoMatch + ", twoMatchPrize=" + twoMatchPrize + ", first=" + first + ", second=" + second + ", third=" +
                third + ", fourth=" + fourth + ", fifth=" + fifth + '}';
    }
}
