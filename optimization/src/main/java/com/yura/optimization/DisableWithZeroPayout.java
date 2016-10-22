package com.yura.optimization;

import com.yura.zeropark.ZeroparkApi;

class DisableWithZeroPayout implements TargetOperation {

    private ZeroparkApi zeroparkAPI;

    DisableWithZeroPayout(ZeroparkApi zeroparkAPI) {
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        if (context.getTarget().getStats().getRedirects() > context.getConf().getMaxRedirects() )
            zeroparkAPI.pauseTarget(context.getConf().getCampaignId(), context.getTarget().getId());
    }
}
