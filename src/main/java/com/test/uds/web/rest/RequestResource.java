package com.test.uds.web.rest;

import com.test.uds.domain.Request;
import com.test.uds.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RequestResource {

    private final Logger log = LoggerFactory.getLogger(RequestResource.class);


    private final RequestService requestService;

    public RequestResource(RequestService requestService) {
        this.requestService = requestService;
    }

    /**
     * POST  /requests : Create a new request.
     *
     * @param request the request to create
     * @return the ResponseEntity with status 201 (Created) and with body the new request,
     * or with status 400 (Bad Request) if the request has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/requests")
    public ResponseEntity<Request> createRequest(
            @RequestBody Request request) throws URISyntaxException {

        log.debug("REST request to save Request : {}", request);
        Request result = requestService.create(request);
        return ResponseEntity.created(new URI("/api/requests/" + result.getId()))
                .body(result);
    }

    /**
     * GET  /requests/:id : get the "id" request.
     *
     * @param id the id of the request to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the request,
     * or with status 404 (Not Found)
     */
    @GetMapping("/requests/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        log.debug("REST request to get Request : {}", id);
        Optional<Request> result = requestService.getById(id);
        return (!result.isPresent()) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok().body(result.get());
    }

}
