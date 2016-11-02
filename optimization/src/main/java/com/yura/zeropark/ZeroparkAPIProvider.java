package com.yura.zeropark;

import com.yura.http.HttpService;
import org.apache.http.Header;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class ZeroparkAPIProvider {

    private static ZeroparkAPI INSTANCE;

    public ZeroparkAPI get()
    {

        if (INSTANCE == null) {
            HttpService httpService = new HttpService(HttpClients.createDefault());

            try {
                Header[] cookies = httpService.execute(new SignInRequest(), new CookieExtractor());

                INSTANCE = new ZeroparkApiLogging(new HttpZeroparkAPI(new ZeroparkHttpService(httpService, cookies)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return INSTANCE;
    }
}
