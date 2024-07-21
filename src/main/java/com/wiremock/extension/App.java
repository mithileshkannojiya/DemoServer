package com.wiremock.extension;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
public class App 
{
    public static void main( String[] args )
    {
        WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig()
            .extensions(new CustomResponseTransformer()).port(8090));
            wireMockServer.start();
    }
    
}
