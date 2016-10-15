package com.yura.zeropark.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BidPosition {

    private double topBid;
    private String position;

    public double getTopBid() {
        return topBid;
    }

    public void setTopBid(double topBid) {
        this.topBid = topBid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
