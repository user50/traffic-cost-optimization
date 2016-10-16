package com.yura.zeropark;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

class ZeroparkGetRequest extends ZeroparkHttpsRequest {

    private URIBuilder builder;
    private Header[] cookies;

    ZeroparkGetRequest(URIBuilder builder, Header[] cookies) {
        this.builder = builder;
        this.cookies = cookies;
    }

    @Override
    public HttpRequestBase getRequest() {
        try {
            HttpGet post = new HttpGet(builder.build());
            post.setHeaders(cookies);

            return post;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
