package com.yura.zeropark;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;

class SignInRequest extends ZeroparkHttpsRequest {
    @Override
    public HttpRequestBase getRequest() {
        URIBuilder builder = new URIBuilder();
        builder.setPath("/api/signin")
                .setParameter("email", "shariki-za-roliki@yandex.ru")
                .setParameter("password", "KjEUP9VewD");

        try {
            return new HttpPost(builder.build());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
