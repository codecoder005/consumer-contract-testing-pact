package com.popcorn.contract.consumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactBuilder;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.popcorn.client.JsonPlaceHolderClient;
import com.popcorn.model.PostModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonBody;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(PactConsumerTestExt.class)
//@PactTestFor(
//        providerName = JsonPlaceHolderClientConsumerContractTest.PROVIDER,
//        hostInterface = "localhost",
//        port = "8090"
//)
//@SpringBootTest
//@ActiveProfiles(profiles = {"contract-test"})
@Slf4j
public class JsonPlaceHolderClientConsumerContractTest {
//    static final String PROVIDER = "json-place-holder";
//    private static final String CONSUMER = "112059__codecoder005__consumer-contract-testing-pact";
//
//    @Autowired
//    private JsonPlaceHolderClient jsonPlaceHolderClient;
//
//    @Pact(consumer = CONSUMER, provider = PROVIDER)
//    public V4Pact getPostById(PactBuilder pactBuilder) {
//        return pactBuilder
//                .usingLegacyDsl()
//                .given("get post by id")
//                .uponReceiving("get post by id")
//                .method("GET")
//                .path("/posts/1")
//                .willRespondWith()
//                .status(200)
//                .headers(responseHeaders())
//                .body(newJsonBody(object -> {
//                    object.integerType("id", 1);
//                    object.integerType("userId", 1);
//                    object.stringType("title", "some title");
//                    object.stringType("description", "some description");
//                }).build())
//                .toPact(V4Pact.class);
//    }
//
//    @Test
//    @PactTestFor(pactMethod = "getPostById")
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
//    void pactTestMethodForGetPostById(MockServer mockServer) {
//        log.info("executing pactTestMethodForGetPostById");
//        log.info("Mock Provider Endpoint: {}", mockServer.getUrl());
//        final Integer POST_ID = 1;
//        PostModel postModel = this.jsonPlaceHolderClient.getPost(POST_ID);
//        assertEquals(POST_ID, postModel.getId());
//    }
//
//    private Map<String, String> responseHeaders() {
//        Map<String, String> headers = new HashMap<>();
//        headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        return headers;
//    }
}
