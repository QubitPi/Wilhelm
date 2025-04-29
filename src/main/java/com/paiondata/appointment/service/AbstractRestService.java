// Copyright 2025 Paion Data. All rights reserved.
package com.paiondata.appointment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * {@link AbstractRestService} abstracts away the HTTP request handling.
 */
abstract class AbstractRestService {

    protected static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    protected static final String FAILED_REQUEST_MESSAGE = "There is a backend error. Please contact backend team";

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRestService.class);

    /**
     * Send a POST with the specified API URL, payload, and headers.
     * <p>
     * This method also implicitly attach a {@code Content-Type: application/json} header to all outgoing requests.
     *
     * @param uri  The provided URL to send the request to
     * @param payload  The provided payload in {@link ObjectNode} representation. The JSON payload field name
     * corresponds to the {@link ObjectNode} field name and JSON field value to {@link ObjectNode} field value
     *
     * @return The POST response body represented by a {@link JsonNode}
     */
    protected static JsonNode post(final String uri, final Object payload) {
        HttpResponse<String> response = null;

        try {
            response = HTTP_CLIENT.send(
                    HttpRequest.newBuilder()
                            .uri(URI.create(uri))
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            (payload instanceof String)
                                                    ? (String) payload
                                                    : JSON_MAPPER.writeValueAsString(payload)
                                    )
                            )
                            .header("Content-Type", "application/json")
                            .build(),
                    HttpResponse.BodyHandlers.ofString()
            );
        } catch (final IOException exception) {
            final String errorMessage = String.format("I/O error: %s", exception.getMessage());
            LOG.error(errorMessage, exception);
            throw new IllegalStateException(FAILED_REQUEST_MESSAGE, exception);
        } catch (final InterruptedException exception) {
            final String errorMessage = String.format(
                    "Unexpected abort on API request: %s",
                    exception.getMessage()
            );
            LOG.error(errorMessage, exception);
            throw new IllegalStateException(FAILED_REQUEST_MESSAGE, exception);
        }

        if (response.statusCode() >= 400) {
            LOG.error(String.format("API request error: %s", response.body()));
            throw new IllegalStateException(FAILED_REQUEST_MESSAGE);
        }

        try {
            return JSON_MAPPER.readTree(response.body());
        } catch (final JsonProcessingException exception) {
            LOG.error(
                    String.format("Jackson deserialization error: %s", exception.getMessage()),
                    exception
            );
            throw new RuntimeException(FAILED_REQUEST_MESSAGE, exception);
        }
    }
}
