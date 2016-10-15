package com.yura.zeropark;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.yura.http.HttpResponseHandler;
import com.yura.zeropark.model.Campaign;
import com.yura.zeropark.model.Target;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

class TargetsResponseHandler implements HttpResponseHandler<List<Target>> {
    @Override
    public List<Target> handle(CloseableHttpResponse httpResponse) throws IOException {
        String json = EntityUtils.toString(httpResponse.getEntity());

        Configuration conf = Configuration
                .builder()
                .mappingProvider(new JacksonMappingProvider())
                .jsonProvider(new JacksonJsonProvider())
                .build();

        TypeRef<List<Target>> type = new TypeRef<List<Target>>(){};

        List<Target> targets = JsonPath
                .using(conf)
                .parse(json)
                .read("$.elements", type);

        return targets;
    }
}
