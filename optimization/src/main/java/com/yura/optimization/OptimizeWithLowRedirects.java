package com.yura.optimization;

import com.yura.optimization.predicates.*;
import com.yura.zeropark.ZeroparkAPI;

import java.util.function.Predicate;

class OptimizeWithLowRedirects implements TargetOperation {

    private ZeroparkAPI zeroparkAPI;

    OptimizeWithLowRedirects(ZeroparkAPI zeroparkAPI) {
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        Predicate<OptimizationContext> active = new TargetActive();
        Predicate<OptimizationContext> zeroPayout = new ZeroPayout();
        Predicate<OptimizationContext> lowRedirects = new LowRedirects();

        Predicate<OptimizationContext> predicate = active.and(zeroPayout).and(lowRedirects);

        if (!predicate.test(context))
            return;

        zeroparkAPI.setAutoBid(context.getConf().getCampaignId(), context.getTarget().getTarget());
    }
}
