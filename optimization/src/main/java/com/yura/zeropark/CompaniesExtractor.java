package com.yura.zeropark;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.yura.http.HttpResponseHandler;
import com.yura.zeropark.model.Campaign;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

class CompaniesExtractor implements HttpResponseHandler<List<Campaign>> {
    @Override
    public List<Campaign> handle(CloseableHttpResponse httpResponse) throws IOException {
        String json = EntityUtils.toString(httpResponse.getEntity());

        Configuration conf = Configuration
                .builder()
                .mappingProvider(new JacksonMappingProvider())
                .jsonProvider(new JacksonJsonProvider())
                .build();

        TypeRef<List<Campaign>> type = new TypeRef<List<Campaign>>(){};

        List<Campaign> campaigns = JsonPath
                .using(conf)
                .parse(json)
                .read("$.elements[*].details", type);

        return campaigns;
    }
}
