package com.yura.optimization;

import com.yura.CampaignConf;
import com.yura.zeropark.model.Campaign;
import com.yura.zeropark.model.Target;

public class OptimizationContext {

    private CampaignConf conf;
    private Campaign campaign;
    private Target target;

    public OptimizationContext(CampaignConf conf, Campaign campaign, Target target) {
        this.conf = conf;
        this.campaign = campaign;
        this.target = target;
    }

    public CampaignConf getConf() {
        return conf;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public Target getTarget() {
        return target;
    }
}
