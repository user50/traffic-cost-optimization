package com.yura.optimization;

import com.yura.zeropark.ZeroparkApi;
import com.yura.zeropark.model.Target;

class OperationOnTestTarget implements TargetOperation {

    private ZeroparkApi zeroparkAPI;

    OperationOnTestTarget(ZeroparkApi zeroparkAPI) {
        this.zeroparkAPI = zeroparkAPI;
    }

    @Override
    public void accept(OptimizationContext context) {
        Target target = context.getTarget();
        int maxRedirects = context.getConf().getMaxRedirects();

        if (target.getState().getState().equals("ACTIVE") && target.getStats().getPayout() == 0 &&target.getStats().getRedirects() > maxRedirects)
            zeroparkAPI.pauseTarget(context.getConf().getTestCampaignId(), target.getTarget());

        if (target.getState().getState().equals("ACTIVE") && target.getStats().getPayout() > 0 ) {
            zeroparkAPI.pauseTarget(context.getConf().getTestCampaignId(), target.getTarget());
            zeroparkAPI.addTarget2Campaign(context.getConf().getCampaignId(), target.getTarget());
        }

    }
}
