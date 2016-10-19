package com.yura.optimization;

import com.yura.zeropark.ZeroparkAPI;

public class DisableTargetWithZeroPayout implements TargetOperation {

    private ZeroparkAPI zeroparkAPI;

    public DisableTargetWithZeroPayout(ZeroparkAPI zeroparkAPI) {
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        if (context.getTarget().getStats().getPayout() == 0)
            zeroparkAPI.pauseTarget(context.getConf().getCampaingId(), context.getTarget().getId());

    }
}
