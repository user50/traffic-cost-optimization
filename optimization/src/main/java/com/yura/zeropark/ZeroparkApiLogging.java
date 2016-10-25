package com.yura.zeropark;

import com.yura.zeropark.model.Campaign;
import com.yura.zeropark.model.SetTargetBidResponse;
import com.yura.zeropark.model.Target;

import java.util.List;

import static com.yura.logging.Logger.*;

class ZeroparkApiLogging implements ZeroparkAPI {

    private ZeroparkAPI zeroparkApi;

    public ZeroparkApiLogging(ZeroparkAPI zeroparkApi) {
        this.zeroparkApi = zeroparkApi;
    }

    @Override
    public List<Campaign> getCampaigns(String interval) {
        List<Campaign> campaigns = zeroparkApi.getCampaigns(interval);
        LOGGER.log("getCampaigns for interval " + interval);
        return campaigns;
    }

    @Override
    public List<Target> getTargets(String campaignId, String interval) {
        List<Target> targets = zeroparkApi.getTargets(campaignId, interval);
        LOGGER.log("getTargets for campaignId " + campaignId + ", interval " + interval);
        return targets;
    }

    @Override
    public SetTargetBidResponse setTargetBid(String campaignId, String targetId, double bid) {
        SetTargetBidResponse setTargetBidResponse = zeroparkApi.setTargetBid(campaignId, targetId, bid);
        LOGGER.log("setTargetBid for campaignId " + campaignId + ", targetId " + targetId + ", bid " + bid);
        return setTargetBidResponse;
    }

    @Override
    public void pauseTarget(String campaignId, String targetHash) {
        zeroparkApi.pauseTarget(campaignId, targetHash);
        LOGGER.log("pauseTarget for campaignId " + campaignId + ", targetHash " + targetHash);

    }

    @Override
    public void addTarget2Campaign(String campaignId, String target) {
        zeroparkApi.addTarget2Campaign(campaignId, target);
        LOGGER.log("addTarget2Campaign for campaignId " + campaignId + ", target " + target);
    }

    @Override
    public void setCampaignBid(String campaignId, double bid) {
        LOGGER.log("setCampaignBid for campaignId " + campaignId + ", bid " + bid);
        zeroparkApi.setCampaignBid(campaignId, bid);
    }


}
