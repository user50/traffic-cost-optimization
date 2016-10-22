package com.yura.optimization;

import com.yura.logging.Logger;
import com.yura.zeropark.ZeroparkApi;
import com.yura.zeropark.model.BidPosition;
import com.yura.zeropark.model.TargetStats;

class OptimizePosition implements TargetOperation {

    private double minBidChange;
    private ZeroparkApi zeroparkAPI;

    OptimizePosition(double minBidChange, ZeroparkApi zeroparkAPI) {
        this.minBidChange = minBidChange;
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        TargetStats stats = context.getTarget().getStats();

        double maxBid = stats.getPayout()/ stats.getRedirects() * (1 - context.getConf().getPercentage()/100);

        if (maxBid == 0)
            return;

        String campaignId = context.getConf().getCampaignId();
        String targetHash = context.getTarget().getTarget();
        String maxPosition = zeroparkAPI.setTargetBid(campaignId, targetHash, maxBid).getBidPosition();

        if (maxPosition.equals("<5"))
            return;

        double optimalBid = maxBid;

        do {
            optimalBid -= minBidChange;
        }while (zeroparkAPI.setTargetBid(campaignId, targetHash, optimalBid).getBidPosition().equals(maxPosition));

        zeroparkAPI.setTargetBid(campaignId, targetHash, optimalBid + minBidChange);
    }
}
