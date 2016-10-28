package com.yura.optimization;

import com.yura.CampaignConf;
import com.yura.zeropark.ZeroparkAPI;
import com.yura.zeropark.model.Intervals;
import com.yura.zeropark.model.Target;

import java.util.List;

public class TargetOptimizationService {


    private ZeroparkAPI zeroparkAPI;
    private TargetOperation strategy;
    private TargetOperation operationOnTestTarget;

    public TargetOptimizationService(ZeroparkAPI zeroparkAPI, TargetOperation strategy, TargetOperation operationOnTestTarget) {
        this.zeroparkAPI = zeroparkAPI;
        this.strategy = strategy;
        this.operationOnTestTarget = operationOnTestTarget;
    }

    public void optimize(CampaignConf conf)
    {
        if (!conf.isAutoOptimization())
            return;

        List<Target> targets = zeroparkAPI.getTargets(conf.getCampaignId(), Intervals.LAST_7_DAYS.name());
        targets.forEach(target -> strategy.accept(new OptimizationContext(conf, target)));

        if (conf.getTestCampaignId().isEmpty())
            return;

        List<Target> testTargets = zeroparkAPI.getTargets(conf.getTestCampaignId(), Intervals.LAST_7_DAYS.name());
        testTargets.forEach(target -> operationOnTestTarget.accept(new OptimizationContext(conf, target)));
    }


}
