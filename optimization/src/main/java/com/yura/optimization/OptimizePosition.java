package com.yura.optimization;

import com.yura.logging.Logger;
import com.yura.optimization.predicates.TargetActive;
import com.yura.optimization.predicates.TopPositionIsPossible;
import com.yura.optimization.predicates.ZeroPayout;
import com.yura.zeropark.ZeroparkAPI;
import com.yura.zeropark.model.TargetStats;

import java.util.function.Predicate;

class OptimizePosition implements TargetOperation {

    private double minRelativeBidChange;
    private ZeroparkAPI zeroparkAPI;

    OptimizePosition(double minRelativeBidChange, ZeroparkAPI zeroparkAPI) {
        this.minRelativeBidChange = minRelativeBidChange;
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        Predicate<OptimizationContext> active = new TargetActive();
        Predicate<OptimizationContext> zeroPayout = new ZeroPayout();
        Predicate<OptimizationContext> possibleTopPosition = new TopPositionIsPossible();

        Predicate<OptimizationContext> predicate = active.and(zeroPayout.negate()).and(possibleTopPosition.negate());

        if (!predicate.test(context))
            return;

        TargetStats stats = context.getTarget().getStats();

        double maxBid = stats.getPayout()/ stats.getRedirects() * (1 - context.getConf().getPercentage()/100);

        if (maxBid == 0)
            return;

        String campaignId = context.getConf().getCampaignId();
        String targetHash = context.getTarget().getTarget();
        String maxPosition = zeroparkAPI.setTargetBid(campaignId, targetHash, maxBid).getBidPosition();

        Logger.LOGGER.log(" maximal position for target "+context.getTarget().getTarget()+" is "+maxPosition+"; maximal bid "+maxBid);

        if (maxPosition.equals("<5"))
            return;

        double optimalBid = maxBid;

        do {
            optimalBid *= 1-minRelativeBidChange;
        }while (zeroparkAPI.setTargetBid(campaignId, targetHash, optimalBid).getBidPosition().equals(maxPosition));

        zeroparkAPI.setTargetBid(campaignId, targetHash, optimalBid + minRelativeBidChange);
    }
}
