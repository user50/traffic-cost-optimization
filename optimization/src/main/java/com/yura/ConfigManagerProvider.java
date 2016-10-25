package com.yura;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yura.repository.ConfigRepository;
import com.yura.repository.JsonConfigRepository;
import com.yura.zeropark.ZeroparkAPI;
import com.yura.zeropark.ZeroparkAPIProvider;

public class ConfigManagerProvider {

    public CompaignConfigManager get(){
        ConfigRepository<CampaignConf> repository = new JsonConfigRepository<>("config/config.json", CampaignConf.class, new ObjectMapper());
        ZeroparkAPI zeroparkAPI = new ZeroparkAPIProvider().get();

        return new CompaignConfigManager(repository, zeroparkAPI);
    }
}
