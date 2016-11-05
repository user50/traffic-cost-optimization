package com.yura;


import com.yura.optimization.TargetOptimizationService;
import com.yura.optimization.TargetOptimizationServiceProvider;
import com.yura.repository.ConfigRepository;

public class Main {

    public static void main(String[] args) {
        CompaignConfigManager manager = new ConfigManagerProvider().get();
        ConfigRepository<CampaignConf> repository = new ConfigRepositoryProvider().get();
        TargetOptimizationService service = new TargetOptimizationServiceProvider(repository).get();

        manager.get().forEach(service::optimize);
    }
}
