package com.yura;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yura.repository.ConfigRepository;
import com.yura.repository.JsonConfigRepository;
import com.yura.zeropark.ZeroparkAPIProvider;
import com.yura.zeropark.ZeroparkApi;

public class ConfigManagerProvider {

    public CompaignConfigManager get(){
        ConfigRepository<CampaignConf> repository = new JsonConfigRepository<>("config.json", CampaignConf.class, new ObjectMapper());
        ZeroparkApi zeroparkAPI = new ZeroparkAPIProvider().get();

        return new CompaignConfigManager(repository, zeroparkAPI);
    }
}
