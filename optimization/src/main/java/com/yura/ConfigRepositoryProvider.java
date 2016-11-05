package com.yura;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yura.repository.ConfigRepository;
import com.yura.repository.JsonConfigRepository;

/**
 * @author Yevhen
 */
public class ConfigRepositoryProvider {

    public ConfigRepository<CampaignConf> get(){
        return new JsonConfigRepository<>("config/config.json", CampaignConf.class, new ObjectMapper());
    }
}
