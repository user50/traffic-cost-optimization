package com.yura.zeropark;

import com.yura.http.HttpRequestProvider;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpRequestBase;

import java.util.function.Supplier;

class ZeroparkHttpsRequest implements HttpRequestProvider {

    private Header[] cookies;
    private Supplier<HttpRequestBase> requestSupplier;

    public ZeroparkHttpsRequest(Header[] cookies, Supplier<HttpRequestBase> requestSupplier) {
        this.cookies = cookies;
        this.requestSupplier = requestSupplier;
    }

    @Override
    public HttpRequestBase getRequest() {

        HttpRequestBase request = requestSupplier.get();
        request.setHeaders(cookies);

        return request;
    }

    @Override
    public HttpHost getHost() {
        return new HttpHost("panel.zeropark.com", 443, "https");
    }
}
