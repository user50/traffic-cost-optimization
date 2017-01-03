package com.yura.zeropark;

import org.apache.http.Header;

import java.util.function.Supplier;

public class CookiesCache implements Supplier<Header[]> {

    private final Supplier<Header[]> supplier;
    private final long expiration;

    private Header[] cookie;
    private long timeOfGetting = 0;

    public CookiesCache(long expiration, Supplier<Header[]> supplier) {
        this.expiration = expiration;
        this.supplier = supplier;
    }


    @Override
    public Header[] get() {
        if (System.currentTimeMillis() - timeOfGetting > expiration)
            cookie = supplier.get();

        return cookie;
    }
}
