package com.yura.optimization;

import com.yura.logging.Logger;
import com.yura.optimization.predicates.TargetActive;
import com.yura.optimization.predicates.TopPositionIsPossible;
import com.yura.optimization.predicates.UnknownTopBid;
import com.yura.optimization.predicates.ZeroPayout;
import com.yura.zeropark.ZeroparkAPI;
import com.yura.zeropark.model.TargetStats;

import java.util.function.Predicate;

class OptimizeUnknownTopBidCase implements TargetOperation {

    private ZeroparkAPI zeroparkAPI;

    OptimizeUnknownTopBidCase(ZeroparkAPI zeroparkAPI) {
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        Predicate<OptimizationContext> active = new TargetActive();
        Predicate<OptimizationContext> zeroPayout = new ZeroPayout();
        Predicate<OptimizationContext> unknownTopBid = new UnknownTopBid();

        Predicate<OptimizationContext> predicate = active.and(zeroPayout.negate()).and(unknownTopBid);

        if (!predicate.test(context))
            return;

        Logger.LOGGER.log("the case is recognized as "+getClass().getSimpleName());

        TargetStats stats = context.getTarget().getStats();

        double maxBid = stats.getPayout()/ stats.getRedirects() * (1 - context.getConf().getPercentage()/100);

        String campaignId = context.getConf().getCampaignId();
        String targetHash = context.getTarget().getTarget();

        zeroparkAPI.setTargetBid(campaignId, targetHash, maxBid).getBidPosition();
    }
}
