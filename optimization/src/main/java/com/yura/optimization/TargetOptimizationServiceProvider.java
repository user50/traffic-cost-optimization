package com.yura.optimization;

import com.yura.optimization.predicates.TargetActive;
import com.yura.optimization.predicates.TopPositionIsPossible;
import com.yura.optimization.predicates.ZeroPayout;
import com.yura.zeropark.ZeroparkAPIProvider;
import com.yura.zeropark.ZeroparkApi;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TargetOptimizationServiceProvider implements Supplier<TargetOptimizationService>{

    @Override
    public TargetOptimizationService get() {
        ZeroparkApi zeroparkAPI = new ZeroparkAPIProvider().get();
        double minBidChange = 0.0001;

        Predicate<OptimizationContext> active = new TargetActive();
        Predicate<OptimizationContext> zeroPayout = new ZeroPayout();
        Predicate<OptimizationContext> possibleTopPosition = new TopPositionIsPossible();

        Consumer<OptimizationContext> disableTargetWithoutProfit =
                new ConditionalTargetOperation(active.and(zeroPayout) , new DisableWithZeroPayout(zeroparkAPI) );

        Consumer<OptimizationContext> optimizeTopPosition =
                new ConditionalTargetOperation(active.and(zeroPayout.negate()).and(possibleTopPosition) , new OptimizeTopPosition(minBidChange, zeroparkAPI));

        Consumer<OptimizationContext> optimizePosition =
                new ConditionalTargetOperation(active.and(zeroPayout.negate()).and(possibleTopPosition.negate()) , new OptimizePosition(minBidChange, zeroparkAPI));

        Consumer<OptimizationContext> optimizationStrategy = disableTargetWithoutProfit
                                                            .andThen(optimizeTopPosition)
                                                            .andThen(optimizePosition);

        TargetOperation operationOnTestTarget = new OperationOnTestTarget(zeroparkAPI);

        return new TargetOptimizationService(zeroparkAPI, optimizationStrategy::accept, operationOnTestTarget);
    }
}
