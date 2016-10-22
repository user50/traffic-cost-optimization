package com.yura.optimization;

import com.yura.optimization.predicates.TargetActive;
import com.yura.zeropark.HttpZeroparkAPI;
import com.yura.zeropark.ZeroparkAPIProvider;
import com.yura.zeropark.ZeroparkApi;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TargetOptimizationServiceProvider implements Supplier<TargetOptimizationService>{

    @Override
    public TargetOptimizationService get() {
        ZeroparkApi zeroparkAPI = new ZeroparkAPIProvider().get();
        double minBidChange = 0.0001;

        Consumer<OptimizationContext> optimizationStrategy =
                new ConditionalTargetOperation(new TargetActive(), new DisableWithZeroPayout(zeroparkAPI) )
                .andThen(new OptimizeTopPosition(minBidChange, zeroparkAPI))
                .andThen(new OptimizePosition(minBidChange, zeroparkAPI));

        TargetOperation operationOnTestTarget = new OperationOnTestTarget(zeroparkAPI);

        return new TargetOptimizationService(zeroparkAPI, optimizationStrategy::accept, operationOnTestTarget);
    }
}
