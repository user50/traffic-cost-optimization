package com.yura.zeropark;

import com.yura.http.HttpService;
import com.yura.zeropark.model.Campaign;
import com.yura.zeropark.model.Target;
import org.apache.http.Header;

import java.io.IOException;
import java.util.List;

public class ZeroparkAPI {

    private Header[] cookies;
    private HttpService httpService;

    public ZeroparkAPI(Header[] cookies, HttpService httpService) {
        this.cookies = cookies;
        this.httpService = httpService;
    }

    public List<Campaign> getCampaigns(String interval){
        try {
            return httpService.execute(new GetAllCompanies(cookies, interval), new CompaniesExtractor());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Target> getTargets(String campaignId, String interval )
    {
        try {
            return httpService.execute(new GetTargetsForCampaign(cookies, campaignId, interval), new TargetsResponseHandler());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTargetBid(String campaignId, String targetId, double bid)
    {
        try {
            httpService.execute(new SetTargetsBid(cookies, campaignId, targetId, bid), httpResponse -> null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
