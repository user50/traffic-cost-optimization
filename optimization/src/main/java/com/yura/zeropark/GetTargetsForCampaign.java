package com.yura.zeropark;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

class GetTargetsForCampaign extends ZeroparkHttpsRequest {

    private Header[] cookies;
    private String campaignId;
    private String interval;

    public GetTargetsForCampaign(Header[] cookies, String campaignId, String interval) {
        this.cookies = cookies;
        this.campaignId = campaignId;
        this.interval = interval;
    }

    @Override
    public HttpRequestBase getRequest() {
        HttpGet httpGet = new HttpGet("/api/stats/campaign/" + campaignId +"/targets?interval=" + interval);
        httpGet.setHeaders(cookies);
        return httpGet;
    }
}
