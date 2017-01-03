package com.yura.zeropark;

import com.yura.http.HttpService;
import org.apache.http.Header;
import org.apache.http.impl.client.HttpClients;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.function.Supplier;

public class ZeroparkAPIProvider {

    private static ZeroparkAPI INSTANCE;

    public ZeroparkAPI get()
    {

        if (INSTANCE == null) {
            HttpService httpService = new HttpService(HttpClients.createDefault());

            Properties conf = getCredentials();
            String user = conf.getProperty("user");
            String psw = conf.getProperty("password");

            Supplier<Header[]> cookieSupplier = new CookiesCache(1000 * 60 * 10, new CookiesSupplier(httpService, user, psw));

            INSTANCE = new ZeroparkApiLogging(new HttpZeroparkAPI(new ZeroparkHttpService(httpService, cookieSupplier)));

        }

        return INSTANCE;
    }

    private Properties getCredentials()
    {
        Properties prop = new Properties();
        try(InputStream input = new FileInputStream("config.properties"))
        {
            prop.load(input);

            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
