package com.yura.zeropark;

import com.yura.http.HttpService;
import org.apache.http.Header;

import java.io.IOException;
import java.util.function.Supplier;

public class CookiesSupplier implements Supplier<Header[]> {

    private HttpService httpService;
    private String user;
    private String psw;

    public CookiesSupplier(HttpService httpService, String user, String psw) {
        this.httpService = httpService;
        this.user = user;
        this.psw = psw;
    }

    @Override
    public Header[] get() {
        try {
            return httpService.execute(new SignInRequest(user, psw), new CookieExtractor());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
