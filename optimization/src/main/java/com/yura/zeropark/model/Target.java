package com.yura.zeropark.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Target {

    private String id;
    private String target;
    private Bid bid;
    private BidPosition bidPosition;
    private TargetStats stats;
    private State state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public BidPosition getBidPosition() {
        return bidPosition;
    }

    public void setBidPosition(BidPosition bidPosition) {
        this.bidPosition = bidPosition;
    }

    public TargetStats getStats() {
        return stats;
    }

    public void setStats(TargetStats stats) {
        this.stats = stats;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
