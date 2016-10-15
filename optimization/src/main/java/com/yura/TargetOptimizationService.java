package com.yura;

import com.yura.zeropark.ZeroparkAPI;
import com.yura.zeropark.model.Target;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.stream.Collectors;

public class TargetOptimizationService {

    public static final String LAST_7_DAYS_INTERVAL = "LAST_7_DAYS";

    private ZeroparkAPI zeroparkAPI;
    private ConfigRepository<CampaignConf> repository;

    public TargetOptimizationService(ZeroparkAPI zeroparkAPI, ConfigRepository<CampaignConf> repository) {
        this.zeroparkAPI = zeroparkAPI;
        this.repository = repository;
    }

    public void optimize()
    {
        List<CampaignConf> withOptitmization = repository.list().stream().filter(CampaignConf::isAutoOptimization).collect(Collectors.toList());

        for (CampaignConf campaignConf : withOptitmization) {
            List<Target> targets = zeroparkAPI.getTargets(campaignConf.getCampaingId(), LAST_7_DAYS_INTERVAL);
            for (Target target : targets) {
                throw new NotImplementedException();
            }
        }

    }

}
