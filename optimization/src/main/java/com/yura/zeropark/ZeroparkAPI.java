package com.yura.zeropark;

import com.yura.zeropark.model.Campaign;
import com.yura.zeropark.model.SetTargetBidResponse;
import com.yura.zeropark.model.Target;

import java.util.List;

public interface ZeroparkAPI {

    List<Campaign> getCampaigns(String interval);

    List<Target> getTargets(String campaignId, String interval );

    SetTargetBidResponse setTargetBid(String campaignId, String targetId, double bid);

    void pauseTarget(String campaignId, String targetHash);

    void addTarget2Campaign(String campaignId, String target);

    void setCampaignBid(String campaignId, double bid);

    void setAutoBid(String campaignId, String target);

    void  deleteTarget(String campaignId, String target);

}
