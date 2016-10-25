package com.yura.optimization;

import com.yura.optimization.predicates.TargetActive;
import com.yura.optimization.predicates.ZeroPayout;
import com.yura.zeropark.ZeroparkAPI;

import java.util.function.Predicate;

class DisableWithZeroPayout implements TargetOperation {

    private ZeroparkAPI zeroparkAPI;

    DisableWithZeroPayout(ZeroparkAPI zeroparkAPI) {
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        Predicate<OptimizationContext> active = new TargetActive();
        Predicate<OptimizationContext> zeroPayout = new ZeroPayout();
        Predicate<OptimizationContext> predicate = active.and(zeroPayout);

        if (!predicate.test(context))
            return;

        if (context.getTarget().getStats().getRedirects() > context.getConf().getMaxRedirects() )
            zeroparkAPI.pauseTarget(context.getConf().getCampaignId(), context.getTarget().getId());
    }
}
