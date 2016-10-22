package com.yura.optimization;

import com.yura.zeropark.HttpZeroparkAPI;
import com.yura.zeropark.ZeroparkApi;
import com.yura.zeropark.model.BidPosition;

class OptimizeTopPosition implements TargetOperation {

    private double minBidChange;
    private ZeroparkApi zeroparkAPI;

    OptimizeTopPosition(double minBidChange, ZeroparkApi zeroparkAPI) {
        this.minBidChange = minBidChange;
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        BidPosition bidPosition = context.getTarget().getBidPosition();

        if (!bidPosition.getPosition().equals("1") && context.getTarget().getState().getState().equals("ACTIVE"))
            return;

        double ourBid = context.getTarget().getBid().getValue();
        double topBid = bidPosition.getTopBid();

        if (ourBid - topBid > minBidChange  )
            zeroparkAPI.setTargetBid(context.getConf().getCampaignId(), context.getTarget().getTarget(), topBid + minBidChange);

    }
}
