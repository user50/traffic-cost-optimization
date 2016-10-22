package com.yura.zeropark;

import com.yura.http.HttpService;
import org.apache.http.Header;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class ZeroparkAPIProvider {

    private static ZeroparkApi INSTANCE;

    public ZeroparkApi get()
    {

        if (INSTANCE == null) {
            HttpService httpService = new HttpService(HttpClients.createDefault());

            try {
                Header[] cookies = httpService.execute(new SignInRequest(), new CookieExtractor());

                INSTANCE = new ZeroparkApiLogging(new HttpZeroparkAPI(cookies, httpService));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return INSTANCE;
    }
}
