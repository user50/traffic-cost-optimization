package com.yura.zeropark;

import com.yura.http.HttpRequestProvider;
import com.yura.http.HttpResponseHandler;
import com.yura.http.HttpService;
import com.yura.http.SimpleHttpErrorHandler;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.IOException;
import java.util.function.Supplier;

public class ZeroparkHttpService {

    private HttpService httpService;
    private Supplier<Header[]> cookies;

    public ZeroparkHttpService(HttpService httpService, Supplier<Header[]> cookies) {
        this.httpService = httpService;
        this.cookies = cookies;
    }

    public <T> T execute(Supplier<HttpRequestBase> requestSupplier, HttpResponseHandler<T> responseHandler)
    {
        HttpRequestProvider requestProvider = new ZeroparkHttpsRequest(cookies.get(), requestSupplier);

        try {
            return httpService.execute(requestProvider, new SimpleHttpErrorHandler<T>(responseHandler));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
