package com.yura.zeropark;

import com.yura.zeropark.model.Campaign;
import com.yura.zeropark.model.SetTargetBidResponse;
import com.yura.zeropark.model.Target;

import java.util.List;

import static com.yura.logging.Logger.LOGGER;

class ZeroparkApiLogging implements ZeroparkAPI {

    private ZeroparkAPI zeroparkApi;

    ZeroparkApiLogging(ZeroparkAPI zeroparkApi) {
        this.zeroparkApi = zeroparkApi;
    }

    @Override
    public List<Campaign> getCampaigns(String interval) {
        List<Campaign> campaigns = zeroparkApi.getCampaigns(interval);
        LOGGER.info("getCampaigns for interval " + interval);
        return campaigns;
    }

    @Override
    public List<Target> getTargets(String campaignId, String interval) {
        List<Target> targets = zeroparkApi.getTargets(campaignId, interval);
        LOGGER.info("getTargets for campaignId " + campaignId + ", interval " + interval);
        return targets;
    }

    @Override
    public SetTargetBidResponse setTargetBid(String campaignId, String targetId, double bid) {
        SetTargetBidResponse setTargetBidResponse = zeroparkApi.setTargetBid(campaignId, targetId, bid);
        LOGGER.info("setTargetBid for campaignId " + campaignId + ", targetId " + targetId + ", bid " + bid);
        return setTargetBidResponse;
    }

    @Override
    public void pauseTarget(String campaignId, String targetHash) {
        zeroparkApi.pauseTarget(campaignId, targetHash);
        LOGGER.info("pauseTarget for campaignId " + campaignId + ", targetHash " + targetHash);

    }

    @Override
    public void addTarget2Campaign(String campaignId, String target) {
        zeroparkApi.addTarget2Campaign(campaignId, target);
        LOGGER.info("addTarget2Campaign for campaignId " + campaignId + ", target " + target);
    }

    @Override
    public void setCampaignBid(String campaignId, double bid) {
        LOGGER.info("setCampaignBid for campaignId " + campaignId + ", bid " + bid);
        zeroparkApi.setCampaignBid(campaignId, bid);
    }

    @Override
    public void setAutoBid(String campaignId, String target) {
        zeroparkApi.setAutoBid(campaignId, target);
        LOGGER.info("setAutoBid for campaignId " + campaignId + ", target " + target);
    }

    @Override
    public void deleteTarget(String campaignId, String target) {
        zeroparkApi.deleteTarget(campaignId, target);
        LOGGER.info("deleteTarget for campaignId " + campaignId + ", target " + target);
    }


}
