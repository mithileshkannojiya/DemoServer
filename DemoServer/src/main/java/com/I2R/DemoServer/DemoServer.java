package com.I2R.DemoServer;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoServer implements CommandLineRunner {

	@Autowired
	WireMockServer wireMockServer;

	public static void main(String[] args) {
		SpringApplication.run(DemoServer.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		wireMockServer.start();
	}
}
