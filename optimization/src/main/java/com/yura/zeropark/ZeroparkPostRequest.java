package com.yura.zeropark;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

class ZeroparkPostRequest extends ZeroparkHttpsRequest {

    private URIBuilder builder;
    private Header[] cookies;

    ZeroparkPostRequest(URIBuilder builder, Header[] cookies) {
        this.builder = builder;
        this.cookies = cookies;
    }

    @Override
    public HttpRequestBase getRequest() {
        try {
            HttpPost post = new HttpPost(builder.build());
            post.setHeaders(cookies);

            return post;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
