package com.mommin.system.account.service.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.AssertProvider;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContentAssert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readString;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONCompareMode.LENIENT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;

@Tag("component")
@TestPropertySource(locations = "/application-test.properties")
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9100", "port=9100"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void happyPath() {
        var customerId = createCustomer();

        findCustomer(customerId);

        findUnknownCustomer(randomUUID());
    }

    UUID createCustomer() {
        var headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);

        var createRequest = new HttpEntity<>(new ClassPathResource("payloads/request-create-customer.json"), headers);

        var location = restTemplate.postForLocation("/v1/customers", createRequest);

        assertThat(location)
                .as("check location")
                .isNotNull();

        return UUID.fromString(fromUri(location).build().getPathSegments().get(2));
    }

    void findUnknownCustomer(UUID customerId) {
        var response = restTemplate.getForEntity("/v1/customers/" + customerId, String.class);

        assertThat(response.getStatusCode())
                .as("check http status")
                .isEqualTo(NOT_FOUND);
    }

    @SneakyThrows
    void findCustomer(UUID customerId) {
        var response = restTemplate.getForEntity("/v1/customers/" + customerId, String.class);

        assertThat(response.getStatusCode())
                .as("check http status")
                .isEqualTo(OK);

        var resource = new ClassPathResource("payloads/response-get-customer.json");
        var expectedBody = readString(resource.getFile().toPath(), UTF_8)
                .replace("${customerId}", customerId.toString());

        assertThat(forJson(response.getBody()))
                .as("check response body")
                .isEqualToJson(expectedBody, LENIENT);
    }

    AssertProvider<JsonContentAssert> forJson(String json) {
        return () -> new JsonContentAssert(getClass(), json);
    }
}
