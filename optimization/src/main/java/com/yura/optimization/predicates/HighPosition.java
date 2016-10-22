package com.yura.optimization.predicates;

import com.yura.optimization.OptimizationContext;
import com.yura.zeropark.model.BidPosition;

import java.util.function.Predicate;

public class HighPosition implements Predicate<OptimizationContext> {
    @Override
    public boolean test(OptimizationContext context) {
        BidPosition bidPosition = context.getTarget().getBidPosition();

        return !bidPosition.getPosition().equals("1") && !bidPosition.getPosition().equals("<5");
    }
}
