package com.yura.optimization;

import com.yura.CampaignConf;
import org.junit.Test;

public class TargetOptimizationServiceProviderTest {
    @Test
    public void get() throws Exception {
        TargetOptimizationService service = new TargetOptimizationServiceProvider().get();

        CampaignConf conf = new CampaignConf();
        conf.setAutoOptimization(true);
        conf.setCampaignId("6e85b900-641f-11e6-9fa4-0ea7743a2ad5");
        conf.setMaxRedirects(300);
        conf.setMaxTestRedirect(300);
        conf.setPercentage(50);
        conf.setTestCampaignId("6f4b4380-5b7a-11e6-ae96-0e6b810b9917");

        service.optimize(conf);
    }

}