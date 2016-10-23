package com.yura.controller;

import com.yura.CampaignConf;
import com.yura.CompaignConfigManager;
import com.yura.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yevhen
 */
@RestController
@RequestMapping("/configs")
public class CampaignConfController {
    @Autowired
    private ConfigRepository<CampaignConf> configRepository;
    @Autowired
    private CompaignConfigManager configManager;

    @RequestMapping(method = RequestMethod.GET)
    public List<CampaignConf> list() {
        return configManager.get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CampaignConf get(@PathVariable String id) {
        return configRepository.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public CampaignConf save(@PathVariable String id, @RequestBody CampaignConf config) {
        configRepository.save(config);
        return configRepository.get(config.getId());
    }

    @RequestMapping(method = RequestMethod.POST)
    public CampaignConf create(@RequestBody CampaignConf config) {
        configRepository.create(config);
        return configRepository.get(config.getId());
    }
}
