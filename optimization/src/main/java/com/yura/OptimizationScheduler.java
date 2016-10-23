package com.yura;

import com.yura.optimization.TargetOptimizationService;
import com.yura.optimization.TargetOptimizationServiceProvider;
import com.yura.repository.ConfigRepository;
import com.yura.zeropark.ZeroparkAPIProvider;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Yevhen
 */
public class OptimizationScheduler {

    private ConfigRepository<CampaignConf> repository;

    public OptimizationScheduler(ConfigRepository<CampaignConf> repository) {
        this.repository = repository;
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void execute(){
        CompaignConfigManager manager = new CompaignConfigManager(repository, new ZeroparkAPIProvider().get());
        TargetOptimizationService service = new TargetOptimizationServiceProvider().get();

        manager.get().forEach(service::optimize);
    }
}
