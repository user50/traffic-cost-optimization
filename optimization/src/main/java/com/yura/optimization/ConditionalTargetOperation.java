package com.yura.optimization;

import java.util.function.Consumer;
import java.util.function.Predicate;

class ConditionalTargetOperation implements TargetOperation {

    private Predicate<OptimizationContext> condition;
    private Consumer<OptimizationContext> operation;

    public ConditionalTargetOperation(Predicate<OptimizationContext> condition, Consumer<OptimizationContext> operation) {
        this.condition = condition;
        this.operation = operation;
    }

    @Override
    public void accept(OptimizationContext context) {

        if (condition.test(context))
            operation.accept(context);

    }
}
