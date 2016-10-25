package com.yura.optimization;

import com.yura.CampaignConf;
import com.yura.zeropark.ZeroparkAPI;
import com.yura.zeropark.ZeroparkAPIProvider;
import com.yura.zeropark.model.Campaign;
import com.yura.zeropark.model.Intervals;
import com.yura.zeropark.model.Target;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    public void name() throws Exception {

        ZeroparkAPI api = new ZeroparkAPIProvider().get();

        List<Campaign> campaigns = api.getCampaigns(Intervals.LAST_7_DAYS.name());

        Campaign campaign = campaigns.stream()
                .filter(c ->  c.getName().equals("AV-Domain-3G-White-KE"))
                .collect(Collectors.toList()).get(0);

        Target target = api.getTargets(campaign.getId(), Intervals.LAST_7_DAYS.name()).stream()
                .filter(t -> t.getTarget().equals("uniform-wan-qaeMDnId"))
                .collect(Collectors.toList())
                .get(0);

        String position = target.getBidPosition().getPosition();

        String responsePosition = api.setTargetBid(campaign.getId(), target.getTarget(), 0.011).getBidPosition();

        int iteration = 0;
        long start = System.currentTimeMillis();
        while (true)
        {

            Thread.sleep(200);
            target = api.getTargets(campaign.getId(), Intervals.LAST_7_DAYS.name()).stream()
                    .filter(t -> t.getTarget().equals("uniform-wan-qaeMDnId"))
                    .collect(Collectors.toList())
                    .get(0);

            iteration++;
            if (!target.getBidPosition().getPosition().equals(position)) {
                System.out.println((System.currentTimeMillis() - start)+" iteration "+iteration);
                return;
            }
        }


    }
}