package com.yura.zeropark;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

class GetAllCompanies extends ZeroparkHttpsRequest {

    private Header[] cookies;
    private String interval;

    public GetAllCompanies(Header[] cookies, String interval) {
        this.cookies = cookies;
        this.interval = interval;
    }

    @Override
    public HttpRequestBase getRequest() {
        HttpGet httpGet = new HttpGet("/api/stats/campaign/all?interval="+interval);
        httpGet.setHeaders(cookies);

        return httpGet;
    }
}
