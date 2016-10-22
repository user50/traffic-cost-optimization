package com.yura.optimization;

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
        double ourBid = context.getTarget().getBid().getValue();
        double topBid = context.getTarget().getBidPosition().getTopBid();

        if (ourBid - topBid > minBidChange  )
            zeroparkAPI.setTargetBid(context.getConf().getCampaignId(), context.getTarget().getTarget(), topBid + minBidChange);

    }
}
