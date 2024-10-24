package com.popcorn.controller;

import com.popcorn.client.DummyClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dummy-api")
@RequiredArgsConstructor
@Slf4j
public class DummyController {
    private final DummyClient dummyClient;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @CircuitBreaker(name = "dummy-client", fallbackMethod = "dummyClientFallbackMethod")
    public ResponseEntity<Object> get() {
        log.info("DummyController::get");
        return ResponseEntity.ok(dummyClient.get());
    }

    public ResponseEntity<Map<String, Object>> dummyClientFallbackMethod() {
        log.info("DummyController::dummyClientFallbackMethod");
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Map.of(
                        "message", "Service Unavailable",
                        "status", HttpStatus.SERVICE_UNAVAILABLE,
                        "fallbackMessage", "Service Unavailable",
                        "fallbackStatus", HttpStatus.SERVICE_UNAVAILABLE,
                        "timestamp", ZonedDateTime.now()
                ));
    }
}
