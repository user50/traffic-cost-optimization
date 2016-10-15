package com.yura.zeropark.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bid {

    private double value;
    private boolean autoBid;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isAutoBid() {
        return autoBid;
    }

    public void setAutoBid(boolean autoBid) {
        this.autoBid = autoBid;
    }
}
