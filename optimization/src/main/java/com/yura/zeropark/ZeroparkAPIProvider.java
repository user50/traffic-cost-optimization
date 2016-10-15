package com.yura.zeropark;

import com.yura.http.HttpService;
import org.apache.http.Header;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class ZeroparkAPIProvider {

    public ZeroparkAPI get()
    {
        HttpService httpService = new HttpService(HttpClients.createDefault());

        try {
            Header[] cookies = httpService.execute(new SignInRequest(), new CookieExtractor());

            return new ZeroparkAPI(cookies, httpService);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
