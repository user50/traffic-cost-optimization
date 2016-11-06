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

    private CompaignConfigManager manager;
    private TargetOptimizationService service;

    public OptimizationScheduler(CompaignConfigManager manager, TargetOptimizationService service) {
        this.manager = manager;
        this.service = service;
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void execute(){
        manager.get().forEach(service::optimize);
    }
}
