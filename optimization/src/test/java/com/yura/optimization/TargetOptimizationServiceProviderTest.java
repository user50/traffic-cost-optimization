package com.yura.optimization;

import com.yura.CampaignConf;
import org.junit.Test;

public class TargetOptimizationServiceProviderTest {
    @Test
    public void get() throws Exception {
        TargetOptimizationService service = new TargetOptimizationServiceProvider().get();

        CampaignConf conf = new CampaignConf();
        conf.setAutoOptimization(true);
        conf.setCampaignId("a881bfc0-6422-11e6-9fa4-0ea7743a2ad5");
        conf.setMaxRedirects(100);
        conf.setPercentage(0.5);

        service.optimize(conf);
    }

}