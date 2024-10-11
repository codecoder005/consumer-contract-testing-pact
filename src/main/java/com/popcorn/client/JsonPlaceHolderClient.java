package com.popcorn.client;

import com.popcorn.model.PostModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "json-place-holder",
        url = "${json-place-holder.url}"
)
public interface JsonPlaceHolderClient {
    @GetMapping(value = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    PostModel getPost(@PathVariable(name = "id") Integer id);
}
