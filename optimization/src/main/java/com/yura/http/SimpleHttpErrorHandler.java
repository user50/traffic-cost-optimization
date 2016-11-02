package com.yura.http;

import com.yura.http.HttpResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SimpleHttpErrorHandler<T> implements HttpResponseHandler<T> {

    private HttpResponseHandler<T> handler;

    public SimpleHttpErrorHandler(HttpResponseHandler<T> handler) {
        this.handler = handler;
    }

    @Override
    public T handle(CloseableHttpResponse httpResponse) throws IOException {

        int code = httpResponse.getStatusLine().getStatusCode();

        if (String.valueOf(code).matches("2[0-9][0-9]"))
            return handler.handle(httpResponse);

        String message = EntityUtils.toString(httpResponse.getEntity());

        throw new Not200OkException("Response code: "+code+"; message: "+message);
    }
}
