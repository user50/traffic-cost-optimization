package com.yura.optimization;

import com.yura.logging.Logger;
import com.yura.optimization.predicates.TargetActive;
import com.yura.optimization.predicates.TopPositionIsPossible;
import com.yura.optimization.predicates.UnknownTopBid;
import com.yura.optimization.predicates.ZeroPayout;
import com.yura.zeropark.ZeroparkAPI;

import java.util.function.Predicate;

class OptimizeTopPosition implements TargetOperation {

    private double minBidChange;
    private ZeroparkAPI zeroparkAPI;

    OptimizeTopPosition(double minBidChange, ZeroparkAPI zeroparkAPI) {
        this.minBidChange = minBidChange;
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        Predicate<OptimizationContext> active = new TargetActive();
        Predicate<OptimizationContext> zeroPayout = new ZeroPayout();
        Predicate<OptimizationContext> possibleTopPosition = new TopPositionIsPossible();
        Predicate<OptimizationContext> unknownTopBid = new UnknownTopBid();

        Predicate<OptimizationContext> predicate = active.and(zeroPayout.negate()).and(unknownTopBid.negate()).and(possibleTopPosition);

        if (!predicate.test(context))
            return;

        Logger.LOGGER.log("the case is recognized as "+getClass().getSimpleName());

        double ourBid = context.getTarget().getBid().getValue();
        double topBid = context.getTarget().getBidPosition().getTopBid();

        Logger.LOGGER.log("the current position for target "+context.getTarget().getTarget()+" is 1");

        zeroparkAPI.setTargetBid(context.getConf().getCampaignId(), context.getTarget().getTarget(), topBid + minBidChange);

    }
}
