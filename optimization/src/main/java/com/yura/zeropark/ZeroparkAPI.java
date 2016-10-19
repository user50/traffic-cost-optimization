package com.yura.zeropark;

import com.yura.http.HttpService;
import com.yura.zeropark.model.Campaign;
import com.yura.zeropark.model.Target;
import org.apache.http.Header;
import org.apache.http.client.utils.URIBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/stats/campaign/all")
                .setParameter("interval", interval);

        try {
            return httpService.execute(new ZeroparkGetRequest(builder, cookies), new CompaniesExtractor());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Target> getTargets(String campaignId, String interval )
    {
        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/stats/campaign/" + campaignId +"/targets")
                .setParameter("interval", interval);

        try {
            return httpService.execute(new ZeroparkGetRequest(builder, cookies), new TargetsResponseHandler());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTargetBid(String campaignId, String targetId, double bid)
    {
        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/campaign/"+campaignId+"/target/bid")
                .setParameter("campaignId", campaignId)
                .setParameter("hash", targetId)
                .setParameter("bid", String.valueOf(bid));

        try {
            httpService.execute(new ZeroparkPostRequest(builder, cookies), httpResponse -> null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void pauseTarget(String campaignId, String targetHash)
    {
        throw new NotImplementedException();
    }

    private void addTarget2Campaign(String campaignId, String target)
    {
        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/campaign/"+campaignId+"/target/add")
                .setParameter("campaignId", campaignId)
                .setParameter("hash", target);

        try {
            httpService.execute(new ZeroparkPostRequest(builder, cookies), resp -> null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCampaignBid(String campaignId, double bid)
    {
        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/campaign/"+campaignId+"/bid")
                .setParameter("campaignId", campaignId)
                .setParameter("bid", String.valueOf(bid));

        try {
            httpService.execute(new ZeroparkPostRequest(builder, cookies), resp -> null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




}
