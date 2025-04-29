// Copyright 2025 Paion Data. All rights reserved.
package com.paiondata.appointment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

/**
 * Open API documentation integration tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SwaggerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Make sure Swagger UI loads.
     */
    @Test
    void uiTest() {
        assertThat(this.restTemplate.getForEntity("http://localhost:" + port + "/swagger-ui/index.html",
                String.class).getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
