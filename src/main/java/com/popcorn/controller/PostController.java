package com.popcorn.controller;

import com.popcorn.client.JsonPlaceHolderClient;
import com.popcorn.model.PostModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final JsonPlaceHolderClient jsonPlaceHolderClient;

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name = "json-placeholder-api", fallbackMethod = "jsonPlaceHolderApiFallbackMethod")
    public ResponseEntity<PostModel> getPost(@PathVariable("id") Integer id) {
        log.info("PostController::getPost");
        return ResponseEntity.status(HttpStatus.OK)
                .body(jsonPlaceHolderClient.getPost(id));
    }

    public ResponseEntity<Map<String, Object>> jsonPlaceHolderApiFallbackMethod(Throwable throwable) {
        log.info("PostController::jsonPlaceHolderApiFallbackMethod");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                        "message", "Json Placeholder Service is currently unavailable.",
                        "status", HttpStatus.SERVICE_UNAVAILABLE,
                        "timestamp", ZonedDateTime.now()
                ));
    }
}
