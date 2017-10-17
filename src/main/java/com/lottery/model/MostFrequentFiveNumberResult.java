package com.lottery.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * A leggyakoribb öt lottószám szabály eredményének wrapper osztálya
 */
@ApiModel("Most Frequent Five Number Result")
public class MostFrequentFiveNumberResult {
    @ApiModelProperty(value = "list of frequently drawn numbers", required = true)
    private List<Integer> result;

    public MostFrequentFiveNumberResult() {
        this.result = new ArrayList<Integer>();
    }

    public List<Integer> getResult() {
        return result;
    }

    public void setResult(List<Integer> result) {
        this.result = result;
    }
}
