package com.yura;

import com.yura.repository.ConfigRepository;
import com.yura.zeropark.ZeroparkAPI;
import com.yura.zeropark.model.Campaign;
import com.yura.zeropark.model.Intervals;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CompaignConfigManager {
    private ConfigRepository<CampaignConf> repository;
    private ZeroparkAPI zeroparkApi;

    public CompaignConfigManager(ConfigRepository<CampaignConf> repository, ZeroparkAPI zeroparkApi) {
        this.repository = repository;
        this.zeroparkApi = zeroparkApi;
    }

    public List<CampaignConf> get(){
        List<Campaign> campaigns = zeroparkApi.getCampaigns(Intervals.LAST_7_DAYS.name());
        List<CampaignConf> confs = repository.list();

        Set<String> existingCampaign =  campaigns.stream().map(Campaign::getId).collect(Collectors.toSet());

        confs.stream()
                .filter(conf -> !existingCampaign.contains(conf.getCampaignId()))
                .forEach(conf -> repository.delete(conf.getId()));


        Set<String> confCompaigns = confs.stream().map(CampaignConf::getCampaignId).collect(Collectors.toSet());

        campaigns.stream()
                .filter(campaign -> !confCompaigns.contains(campaign.getId()))
                .forEach(campaign -> {
                    repository.create(new CampaignConf(campaign.getId(), campaign.getName(), false, 0, 0, "", "", 0));
                });

        return repository.list();
    }
}
