package com.yura.optimization;

import com.yura.zeropark.HttpZeroparkAPI;
import com.yura.zeropark.ZeroparkApi;
import com.yura.zeropark.model.Target;

class DisableWithZeroPayout implements TargetOperation {

    private ZeroparkApi zeroparkAPI;

    DisableWithZeroPayout(ZeroparkApi zeroparkAPI) {
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        Target target = context.getTarget();

        if (target.getStats().getPayout() == 0 && target.getState().getState().equals("ACTIVE"))
            zeroparkAPI.pauseTarget(context.getConf().getCampaignId(), context.getTarget().getId());

    }
}
