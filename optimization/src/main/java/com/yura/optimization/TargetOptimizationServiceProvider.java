package com.yura.optimization;

import com.yura.zeropark.ZeroparkAPI;
import com.yura.zeropark.ZeroparkAPIProvider;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TargetOptimizationServiceProvider implements Supplier<TargetOptimizationService>{

    @Override
    public TargetOptimizationService get() {
        ZeroparkAPI zeroparkAPI = new ZeroparkAPIProvider().get();
        double minRelativeBidChange = 0.1;
        double minBidChange = 0.0001;

        Consumer<OptimizationContext> disableTargetWithoutProfit = new DisableWithZeroPayout(zeroparkAPI);
        Consumer<OptimizationContext> optimizeTopPosition = new OptimizeTopPosition(minBidChange, zeroparkAPI);
        Consumer<OptimizationContext> optimizePosition = new OptimizePosition(minRelativeBidChange, zeroparkAPI);
        Consumer<OptimizationContext> unknownTopBidCase = new OptimizeUnknownTopBidCase(zeroparkAPI);
        Consumer<OptimizationContext> withLowRedirects = new OptimizeWithLowRedirects(zeroparkAPI);

        Consumer<OptimizationContext> optimizationStrategy = disableTargetWithoutProfit
                                                            .andThen(optimizeTopPosition)
                                                            .andThen(optimizePosition)
                                                            .andThen(withLowRedirects)
                                                            .andThen(unknownTopBidCase);

        TargetOperation operationOnTestTarget = new OperationOnTestTarget(zeroparkAPI);

        return new TargetOptimizationService(zeroparkAPI, optimizationStrategy::accept, operationOnTestTarget);
    }
}
