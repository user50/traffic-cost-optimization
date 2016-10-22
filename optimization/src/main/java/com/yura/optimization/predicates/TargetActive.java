package com.yura.optimization.predicates;

import com.yura.optimization.OptimizationContext;

import java.util.function.Predicate;

public class TargetActive implements Predicate<OptimizationContext> {
    @Override
    public boolean test(OptimizationContext context) {
        return context.getTarget().getState().getState().equals("ACTIVE");
    }
}
