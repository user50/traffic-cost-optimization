package com.yura.zeropark;

import com.yura.http.HttpService;
import com.yura.zeropark.model.Campaign;
import com.yura.zeropark.model.SetTargetBidResponse;
import com.yura.zeropark.model.Target;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

class HttpZeroparkAPI implements ZeroparkAPI {

    private ZeroparkHttpService httpService;

    HttpZeroparkAPI(ZeroparkHttpService httpService) {
        this.httpService = httpService;
    }

    public List<Campaign> getCampaigns(String interval){
        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/stats/campaign/all")
                .setParameter("interval", interval);

        try {
            HttpGet post = new HttpGet(builder.build());

            return httpService.execute(() -> post, new CompaniesExtractor());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Target> getTargets(String campaignId, String interval )
    {
        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/stats/campaign/" + campaignId +"/targets")
                .setParameter("interval", interval)
                .setParameter("limit", "10000");

        try {
            HttpGet post = new HttpGet(builder.build());

            return httpService.execute(() -> post, new TargetsResponseHandler());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public SetTargetBidResponse setTargetBid(String campaignId, String targetId, double bid)
    {
        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/campaign/"+campaignId+"/target/bid")
                .setParameter("campaignId", campaignId)
                .setParameter("hash", targetId)
                .setParameter("bid", String.valueOf(bid));

        try {
            HttpPost post = new HttpPost(builder.build());

            return httpService.execute(() -> post, new SetTargetsBidResponseHandler());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void pauseTarget(String campaignId, String targetHash)
    {
        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/campaign/"+campaignId+"/target/pause")
                .setParameter("campaignId", campaignId)
                .setParameter("hash", targetHash);

        try {
            HttpPost post = new HttpPost(builder.build());

            httpService.execute(() -> post, resp -> null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTarget2Campaign(String campaignId, String target)
    {
        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/campaign/"+campaignId+"/target/add")
                .setParameter("campaignId", campaignId)
                .setParameter("hash", target);

        try {
            HttpPost post = new HttpPost(builder.build());

            httpService.execute(() -> post, resp -> null);
        } catch (URISyntaxException e) {
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
            HttpPost post = new HttpPost(builder.build());

            httpService.execute(() -> post, resp -> null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void setAutoBid(String campaignId, String target) {
        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/campaign/"+campaignId+"/target/autobid")
                .setParameter("campaignId", campaignId)
                .setParameter("hash", target);

        try {
            HttpPost post = new HttpPost(builder.build());

            httpService.execute(() -> post, resp -> null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTarget(String campaignId, String target) {


        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/campaign/"+campaignId+"/targets/delete")
                .setParameter("campaignId", campaignId)
                .setParameter("hash", target);

        try {
            HttpPost post = new HttpPost(builder.build());

            httpService.execute(() -> post, resp -> null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


}
