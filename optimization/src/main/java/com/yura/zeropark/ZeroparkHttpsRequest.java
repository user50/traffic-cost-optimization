package com.yura.zeropark;

import com.yura.http.HttpRequestProvider;
import org.apache.http.HttpHost;

abstract class ZeroparkHttpsRequest implements HttpRequestProvider {

    @Override
    public HttpHost getHost() {
        return new HttpHost("panel.zeropark.com", 443, "https");
    }
}
