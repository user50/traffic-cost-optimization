package com.yura.zeropark.model;

public class SetTargetBidResponse {

    private String bidPosition;
    private double topBid;
    private double bid;

    public String getBidPosition() {
        return bidPosition;
    }

    public void setBidPosition(String bidPosition) {
        this.bidPosition = bidPosition;
    }

    public double getTopBid() {
        return topBid;
    }

    public void setTopBid(double topBid) {
        this.topBid = topBid;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }
}
