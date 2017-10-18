package com.lottery.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * A négyes találatok aránya az ötöshöz szabály eredményének wrapper osztálya
 */
@ApiModel("Four Match Ratio Result")
public class FourMatchRatioToFiveMatchResult {

    @ApiModelProperty(value = "ratio of four matches compared to five matches", required = true)
    private Float result;

    public FourMatchRatioToFiveMatchResult() {
    }

    public Float getResult() {
        return result;
    }

    public void setResult(Float result) {
        this.result = result;
    }
}
