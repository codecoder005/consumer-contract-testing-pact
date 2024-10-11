package com.popcorn.controller;

import com.popcorn.client.JsonPlaceHolderClient;
import com.popcorn.model.PostModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final JsonPlaceHolderClient jsonPlaceHolderClient;

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostModel> getPost(@PathVariable("id") Integer id) {
        log.info("PostController::getPost");
        return ResponseEntity.status(HttpStatus.OK)
                .body(jsonPlaceHolderClient.getPost(id));
    }
}
