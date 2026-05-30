package com.example.sigmacapi.cucumber.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import com.example.sigmacapi.SigmacApiApplication;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { SigmacApiApplication.class })
public class SpringIntegrationTest {

    @LocalServerPort
    protected int port;

    public String getBaseUrl() {
        return "http://localhost:" + port;
    }
}