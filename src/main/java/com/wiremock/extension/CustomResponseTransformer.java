package com.wiremock.extension;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ProtocolException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;
import org.json.JSONObject;
import org.json.XML;


@SuppressWarnings("deprecation")
public class CustomResponseTransformer extends ResponseTransformer {

    @Override
    public String getName() {
        return "custom-response-transformer";
    }

    @Override
    public boolean applyGlobally() {
        return true; // Set to true if you want this transformer to apply to all responses
    }

    @Override
    public Response transform(Request request, Response response, FileSource files, Parameters parameters) {
        String responseBody = "";
        CloseableHttpResponse response_new = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            WireMockServer wm = new WireMockServer(8096);
            wm.start();
            HttpGet request_new = new HttpGet("http://localhost:8096/callback");
            response_new = client.execute(request_new);
            responseBody = EntityUtils.toString(response_new.getEntity());
            wm.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String transformedBody = response.getBodyAsString();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode merged = mapper.createObjectNode();

        try {
            JSONObject xmltojson = XML.toJSONObject(responseBody);
            JsonNode jsonNode1 = mapper.readTree(String.valueOf(xmltojson));
            JsonNode jsonNode2 = mapper.readTree(transformedBody);
            merged.setAll((ObjectNode) jsonNode1);
//            merged.setAll((ObjectNode) jsonNode2);

            String transformedResponse = null;
            if (response_new.getHeader("Content-Type").getValue().contains("xml")){
                transformedResponse = responseBody;
            }
            else
                transformedResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(merged);
            return Response.Builder.like(response)
                    .but()
                    .body(transformedResponse)
                    .build();
        } catch (IOException | ProtocolException e) {
            e.printStackTrace();
        }
        return response;
    }
}
