package com.yura.http;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

public interface HttpResponseHandler<T> {

    T handle(CloseableHttpResponse httpResponse) throws IOException;

}
