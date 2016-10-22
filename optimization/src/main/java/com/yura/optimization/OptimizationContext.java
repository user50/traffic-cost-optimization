package com.yura.optimization;

import com.yura.CampaignConf;
import com.yura.zeropark.model.Campaign;
import com.yura.zeropark.model.Target;

public class OptimizationContext {

    private CampaignConf conf;
    private Target target;

    OptimizationContext(CampaignConf conf, Target target) {
        this.conf = conf;
        this.target = target;
    }

    public CampaignConf getConf() {
        return conf;
    }

    public Target getTarget() {
        return target;
    }
}
