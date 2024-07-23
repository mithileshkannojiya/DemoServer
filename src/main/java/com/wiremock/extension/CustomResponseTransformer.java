package com.wiremock.extension;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;



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
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            WireMockServer wm = new WireMockServer(8080);
            wm.start();
            HttpGet request_new = new HttpGet("http://localhost:8084/callback_new");
            HttpResponse response_new = client.execute(request_new);
            responseBody = EntityUtils.toString(response_new.getEntity());
            wm.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        String transformedBody = response.getBodyAsString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode merged = mapper.createObjectNode();
        
        try {
            JsonNode jsonNode1 = mapper.readTree(responseBody);
            JsonNode jsonNode2 = mapper.readTree(transformedBody);
            ((ObjectNode) merged).setAll((ObjectNode) jsonNode1);
            ((ObjectNode) merged).setAll((ObjectNode) jsonNode2);
            return Response.Builder.like(response).but().body(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(merged)).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
