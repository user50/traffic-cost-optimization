package com.yura.zeropark;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yura.http.HttpResponseHandler;
import com.yura.zeropark.model.SetTargetBidResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

class SetTargetsBidResponseHandler implements HttpResponseHandler<SetTargetBidResponse> {
    @Override
    public SetTargetBidResponse handle(CloseableHttpResponse httpResponse) throws IOException {

        String json = EntityUtils.toString(httpResponse.getEntity());

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, SetTargetBidResponse.class );
    }
}
