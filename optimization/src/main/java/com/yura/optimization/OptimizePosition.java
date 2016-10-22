package com.yura.optimization;

import com.yura.zeropark.HttpZeroparkAPI;
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
        BidPosition bidPosition = context.getTarget().getBidPosition();

        if  (!bidPosition.getPosition().equals("1")  && context.getTarget().getState().getState().equals("ACTIVE"))
            return;

        TargetStats stats = context.getTarget().getStats();

        double maxBid = stats.getPayout()/ stats.getRedirects() * (1 - context.getConf().getPercentage()/100);

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
