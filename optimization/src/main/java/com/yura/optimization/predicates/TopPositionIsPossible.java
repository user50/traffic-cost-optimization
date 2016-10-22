package com.yura.optimization.predicates;

import com.yura.optimization.OptimizationContext;
import com.yura.zeropark.model.Target;

import java.util.function.Predicate;

public class TopPositionIsPossible implements Predicate<OptimizationContext> {
    @Override
    public boolean test(OptimizationContext context) {
        Target target = context.getTarget();
        double topBid = target.getBidPosition().getTopBid();
        double maxBid = target.getStats().getPayout() / target.getStats().getRedirects() * (1 - context.getConf().getPercentage()/100);

        return maxBid > topBid;
    }
}
