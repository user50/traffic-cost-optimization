package com.yura.optimization;

import com.yura.optimization.predicates.TargetActive;
import com.yura.optimization.predicates.TopPositionIsPossible;
import com.yura.optimization.predicates.ZeroPayout;
import com.yura.zeropark.ZeroparkAPIProvider;
import com.yura.zeropark.ZeroparkAPI;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TargetOptimizationServiceProvider implements Supplier<TargetOptimizationService>{

    @Override
    public TargetOptimizationService get() {
        ZeroparkAPI zeroparkAPI = new ZeroparkAPIProvider().get();
        double minBidChange = 0.1;


        Consumer<OptimizationContext> disableTargetWithoutProfit = new DisableWithZeroPayout(zeroparkAPI);
        Consumer<OptimizationContext> optimizeTopPosition = new OptimizeTopPosition(minBidChange, zeroparkAPI);
        Consumer<OptimizationContext> optimizePosition =new OptimizePosition(minBidChange, zeroparkAPI);

        Consumer<OptimizationContext> optimizationStrategy = disableTargetWithoutProfit
                                                            .andThen(optimizeTopPosition)
                                                            .andThen(optimizePosition);

        TargetOperation operationOnTestTarget = new OperationOnTestTarget(zeroparkAPI);

        return new TargetOptimizationService(zeroparkAPI, optimizationStrategy::accept, operationOnTestTarget);
    }
}
