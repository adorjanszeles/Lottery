package com.lottery.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Result segédosztály a Drools működésének könnyítéséhez, amely a kihúzott számok átlagát tartalmazza
 */
@ApiModel("Average Number Result")
public class AverageResult {

    @ApiModelProperty(value = "average number", required = true)
    private Float result;

    public AverageResult() {
        this.result = null;
    }

    public Float getResult() {
        return this.result;
    }

    public void setResult(Float result) {
        this.result = result;
    }
}
