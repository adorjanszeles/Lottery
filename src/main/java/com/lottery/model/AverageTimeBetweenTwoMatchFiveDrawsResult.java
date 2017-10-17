package com.lottery.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Average Time Between Five Matches Result")
public class AverageTimeBetweenTwoMatchFiveDrawsResult {

    @ApiModelProperty(value = "number of weeks passed by within two five match", required = true)
    private Float result;

    public Float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }
}
