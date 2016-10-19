package com.yura.optimization;

import com.yura.zeropark.ZeroparkAPI;
import com.yura.zeropark.model.BidPosition;

public class OptimizeTopPosition implements TargetOperation  {

    private double minBidChange;
    private ZeroparkAPI zeroparkAPI;

    public OptimizeTopPosition(double minBidChange, ZeroparkAPI zeroparkAPI) {
        this.minBidChange = minBidChange;
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        BidPosition bidPosition = context.getTarget().getBidPosition();

        if (!context.getTarget().getBidPosition().getPosition().equals("1"))
            return;

        double outBid = context.getTarget().getBid().getValue();

        if (outBid - bidPosition.getTopBid() > minBidChange  )
            zeroparkAPI.setTargetBid(context.getCampaign().getId(), context.getTarget().getId(), outBid + minBidChange);

    }
}
