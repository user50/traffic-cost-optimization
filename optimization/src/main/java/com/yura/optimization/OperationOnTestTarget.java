package com.yura.optimization;

import com.yura.zeropark.ZeroparkAPI;
import com.yura.zeropark.model.Target;

class OperationOnTestTarget implements TargetOperation {

    private ZeroparkAPI zeroparkAPI;

    OperationOnTestTarget(ZeroparkAPI zeroparkAPI) {
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        Target target = context.getTarget();
        int maxRedirects = context.getConf().getMaxRedirects();

        if (target.getStats().getPayout() == 0 &&target.getStats().getRedirects() > maxRedirects)
            zeroparkAPI.pauseTarget(context.getConf().getTestCampaignId(), target.getTarget());

        if (target.getStats().getPayout() > 0 ) {
            zeroparkAPI.deleteTarget(context.getConf().getTestCampaignId(), target.getTarget());
            zeroparkAPI.addTarget2Campaign(context.getConf().getCampaignId(), target.getTarget());
        }

    }
}
