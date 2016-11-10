package com.yura.optimization;

import com.yura.logging.Logger;
import com.yura.optimization.predicates.TargetActive;
import com.yura.optimization.predicates.TopPositionIsPossible;
import com.yura.optimization.predicates.UnknownTopBid;
import com.yura.optimization.predicates.ZeroPayout;
import com.yura.zeropark.ZeroparkAPI;
import com.yura.zeropark.model.Intervals;
import com.yura.zeropark.model.TargetStats;

import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        Predicate<OptimizationContext> unknownTopBid = new UnknownTopBid();


        Predicate<OptimizationContext> predicate = active.and(zeroPayout.negate()).and(unknownTopBid.negate()).and(possibleTopPosition.negate());

        if (!predicate.test(context))
            return;

        Logger.LOGGER.info("the case is recognized as "+getClass().getSimpleName());

        TargetStats stats = context.getTarget().getStats();

        double maxBid = stats.getPayout()/ stats.getRedirects() * (1 - context.getConf().getPercentage()/100);

        if (maxBid == 0)
            return;

        String campaignId = context.getConf().getCampaignId();
        String targetHash = context.getTarget().getTarget();

        zeroparkAPI.setTargetBid(campaignId, targetHash, maxBid).getBidPosition();
        String maxPosition = getTargetPosition(campaignId, targetHash);

        Logger.LOGGER.info(" maximal position for target "+context.getTarget().getTarget()+" is "+maxPosition+"; maximal bid "+maxBid);

        if (maxPosition.equals("<5"))
            return;

        double optimalBid = maxBid;

        do {
            optimalBid *= 1-minRelativeBidChange;
            zeroparkAPI.setTargetBid(campaignId, targetHash, optimalBid);
        }while (getTargetPosition(campaignId, targetHash).equals(maxPosition));

        zeroparkAPI.setTargetBid(campaignId, targetHash, optimalBid * (1+minRelativeBidChange));
    }

    private String getTargetPosition(String campaignId, String target)
    {
        return zeroparkAPI.getTargets(campaignId, Intervals.LAST_7_DAYS.name()).stream()
                .filter(t -> t.getTarget().equals(target))
                .collect(Collectors.toList())
                .get(0).getBidPosition().getPosition();
    }
}
