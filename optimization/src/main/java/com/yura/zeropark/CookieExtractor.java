package com.yura.zeropark;

import com.yura.http.HttpResponseHandler;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

class CookieExtractor implements HttpResponseHandler<Header[]> {

    @Override
    public Header[] handle(CloseableHttpResponse httpResponse) throws IOException {
        return httpResponse.getHeaders("Set-Cookie");
    }
}
