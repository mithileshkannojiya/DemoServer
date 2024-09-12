package com.I2R.DemoServer.ServerConfiguration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    @Bean
    public ModelMapper getModalMapper() {
        return new ModelMapper();
    }

    @Bean
    public WireMockServer wireMockServer() {
        return new WireMockServer(WireMockConfiguration.wireMockConfig()
                .withRootDirectory("src/main/resources")
                .port(8080));
    }

}
