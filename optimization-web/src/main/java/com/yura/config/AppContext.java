package com.yura.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yura.CampaignConf;
import com.yura.CompaignConfigManager;
import com.yura.OptimizationScheduler;
import com.yura.optimization.TargetOptimizationService;
import com.yura.optimization.TargetOptimizationServiceProvider;
import com.yura.repository.ConfigRepository;
import com.yura.repository.JsonConfigRepository;
import com.yura.zeropark.ZeroparkAPIProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Yevhen
 */
@Configuration
@EnableScheduling
public class AppContext {

    @Bean
    public ConfigRepository<CampaignConf> configRepository(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return new JsonConfigRepository<>("config/config.json", CampaignConf.class,mapper);
    }

    @Bean
    public CompaignConfigManager compaignConfigManager(ConfigRepository<CampaignConf> repository){
        return new CompaignConfigManager(repository, new ZeroparkAPIProvider().get());
    }
    @Bean
    public OptimizationScheduler optimizationSchedule(CompaignConfigManager configManager, ConfigRepository<CampaignConf> configRepository){
        return new OptimizationScheduler(configManager, new TargetOptimizationServiceProvider(configRepository).get());
    }
}
