package com.yura.zeropark;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

class SetTargetsBid extends ZeroparkHttpsRequest {

    private Header[] cookies;
    private String campaignId;
    private String target;
    private double bid;

    public SetTargetsBid(Header[] cookies, String campaignId, String target, double bid) {
        this.cookies = cookies;
        this.campaignId = campaignId;
        this.target = target;
        this.bid = bid;
    }

    @Override
    public HttpRequestBase getRequest() {
        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/campaign/"+campaignId+"/target/bid")
                .setParameter("campaignId", campaignId)
                .setParameter("hash", target)
                .setParameter("bid", String.valueOf(bid));

        try {
            HttpPost post = new HttpPost(builder.build());
            post.setHeaders(cookies);

            return post;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
