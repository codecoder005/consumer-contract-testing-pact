package com.popcorn.contract.consumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactBuilder;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.popcorn.client.DummyClient;
import com.popcorn.model.ComplexRequestObject;
import com.popcorn.model.ComplexResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonBody;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(
        providerName = DummyClientConsumerContractTest.PROVIDER,
        hostInterface = "localhost",
        port = "18090"
)
@SpringBootTest
@ActiveProfiles(profiles = {"contract-test"})
@Slf4j
public class DummyClientConsumerContractTest {
    public static final String PROVIDER = "provider-app";
    private static final String CONSUMER = "consumer-app";

    private static final String USER_ID = "2109897817";

    private static final String DUMMY_CLIENT_API_BASE_PATH = "/dummy-client/update";

    private static final String JWT_EXAMPLE_TOKEN = "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.jYW04zLDHfR1v7xdrW3lCGZrMIsVe0vWCfVkN2DRns2c3MN-mcp_-RE6TN9umSBYoNV-mnb31wFf8iun3fB6aDS6m_OXAiURVEKrPFNGlR38JSHUtsFzqTOj-wFrJZN4RwvZnNGSMvK3wzzUriZqmiNLsG8lktlEn6KA4kYVaM61_NpmPHWAjGExWv7cjHYupcjMSmR8uMTwN5UuAwgW6FRstCJEfoxwb0WKiyoaSlDuIiHZJ0cyGhhEmmAPiCwtPAwGeaL1yZMcp0p82cpTQ5Qb-7CtRov3N4DcOHgWYk6LomPR5j5cCkePAz87duqyzSMpCB0mCOuE3CU2VMtGeQ";

    @Autowired
    private DummyClient dummyClient;

    @Pact(consumer = CONSUMER, provider = PROVIDER)
    public V4Pact updateComplexData(PactBuilder pactBuilder) {
        return pactBuilder
                .usingLegacyDsl()
                .given("update data using complex request object")
                .uponReceiving("update data using complex request object")
                .method(HttpMethod.PUT.name())
                .path(DUMMY_CLIENT_API_BASE_PATH+"/"+USER_ID)
                .headers(requestHeaders())
                .query("sortBy=age")
                .matchHeader(HttpHeaders.AUTHORIZATION, "^Bearer [A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+$", "Bearer "+JWT_EXAMPLE_TOKEN)
                .matchHeader("custom-header", "custom-header-value")
                .body(newJsonBody(object -> {
                    object.numberType("age", (byte) 25);  // Byte
                    object.numberType("salary", (short) 5000);  // Short
                    object.booleanType("married", true);  // Boolean
                    object.stringType("gender", "M");  // Character
                    object.integerType("count", 100);  // Integer
                    object.numberType("height", 5.9f);  // Float
                    object.numberType("serialNumber", 123456789L);  // Long
                    object.decimalType("amount", 2500.75);  // Double
                    object.stringType("name", "John Doe");  // String
                    object.stringType("uuid", UUID.randomUUID().toString());  // UUID
                    object.decimalType("revenue", new BigDecimal("100000.50"));  // BigDecimal
                    object.object("additionalProperties", (addProps) -> {});
                }).build())
                .willRespondWith()
                .status(HttpStatus.ACCEPTED.value())
                .headers(responseHeaders())
                .body(newJsonBody(object -> {
                    object.numberType("age", (byte) 25);  // Byte
                    object.numberType("salary", (short) 5000);  // Short
                    object.booleanType("married", true);  // Boolean
                    object.stringType("gender", "M");  // Character
                    object.integerType("count", 100);  // Integer
                    object.numberType("height", 5.9f);  // Float
                    object.numberType("serialNumber", 123456789L);  // Long
                    object.decimalType("amount", 2500.75);  // Double
                    object.stringType("name", "John Doe");  // String
                    object.uuid("uuid", UUID.fromString("fb427153-cdb0-471f-aa39-59f0fb458d45"));  // UUID
                    object.decimalType("revenue", new BigDecimal("100000.50"));  // BigDecimal
                    object.object("additionalProperties", (addProps) -> {});
                }).build())
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethod = "updateComplexData")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void pactTestMethodForUpdateComplexData(MockServer mockServer) {
        log.info("executing pactTestMethodForUpdateComplexData");
        log.info("Mock Provider Endpoint: {}", mockServer.getUrl());
        ComplexRequestObject requestObject = ComplexRequestObject.builder()
                .age((byte) 25)
                .salary((short) 5000)
                .married(true)
                .gender('M')
                .count(100)
                .height(5.9f)
                .serialNumber(123456789L)
                .amount(2500.75)
                .name("John Doe")
                .uuid(UUID.fromString("fb427153-cdb0-471f-aa39-59f0fb458d45"))
                .revenue(new BigDecimal("100000.50"))
                .additionalProperties(Map.of())
                .build();

        // ComplexResponseObject response = this.dummyClient.update(requestObject);

        RestTemplate restTemplate = getRestTemplate();
        final String QUERY_PARAMS = "?sortBy=age";
        URI uri = UriComponentsBuilder
                .fromUriString(mockServer.getUrl()+DUMMY_CLIENT_API_BASE_PATH+"/"+USER_ID+QUERY_PARAMS)
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add("custom-header", "custom-header-value");
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + JWT_EXAMPLE_TOKEN);

        ResponseEntity<ComplexResponseObject> response = restTemplate.exchange(
                uri,
                HttpMethod.PUT,
                new HttpEntity<>(requestObject, headers),
                ComplexResponseObject.class
        );

        assertNotNull(response.getBody());
        var complexResponseObject = response.getBody();
        assertEquals(123456789L, complexResponseObject.getSerialNumber());
    }

    private Map<String, String> requestHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
    private Map<String, String> responseHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    private RestTemplate getRestTemplate() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        factory.setConnectTimeout(Duration.ofSeconds(10));
        return new RestTemplate(factory);
    }
}
