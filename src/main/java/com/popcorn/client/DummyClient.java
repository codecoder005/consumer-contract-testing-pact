package com.popcorn.client;

import com.popcorn.model.ComplexRequestObject;
import com.popcorn.model.ComplexResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "dummy-client",
        url = "${dummy-client.url}"
)
public interface DummyClient {
    @PutMapping(
            path = "/dummy-client/update",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    ComplexResponseObject update(@RequestBody ComplexRequestObject request);

    @GetMapping(path = "/dummy-client/get", produces = {MediaType.APPLICATION_JSON_VALUE})
    ComplexResponseObject get();
}
