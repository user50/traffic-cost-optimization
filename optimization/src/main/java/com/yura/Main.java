package com.yura;


import com.yura.optimization.TargetOptimizationService;
import com.yura.optimization.TargetOptimizationServiceProvider;

public class Main {

    public static void main(String[] args) {
        CompaignConfigManager manager = new ConfigManagerProvider().get();
        TargetOptimizationService service = new TargetOptimizationServiceProvider().get();

        manager.get().forEach(service::optimize);
    }
}
