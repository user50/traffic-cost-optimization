package com.yura;

import com.yura.repository.ConfigRepository;
import com.yura.zeropark.ZeroparkAPI;
import com.yura.zeropark.ZeroparkAPIProvider;

public class ConfigManagerProvider {

    public CompaignConfigManager get(){
        ConfigRepository<CampaignConf> repository = new ConfigRepositoryProvider().get();
        ZeroparkAPI zeroparkAPI = new ZeroparkAPIProvider().get();

        return new CompaignConfigManager(repository, zeroparkAPI);
    }
}
