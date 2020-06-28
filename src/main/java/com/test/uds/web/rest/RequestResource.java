package com.test.uds.web.rest;

import com.test.uds.domain.Request;
import com.test.uds.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

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
     * @param request the activity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new activity,
     * or with status 400 (Bad Request) if the activity has already an ID
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
//
//    /**
//     * GET  /activities/:id : get the "id" activity.
//     *
//     * @param id the id of the activity to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the activity,
//     * or with status 404 (Not Found)
//     */
//    @GetMapping("/activities/{id}")
//    public ResponseEntity<Acai> getActivityById(@PathVariable Long id) {
//        log.debug("REST request to get Activity : {}", id);
//        Optional<Acai> result = requestService.getById(id);
//        return (!result.isPresent()) ?
//                ResponseEntity.notFound().build() :
//                ResponseEntity.ok().body(result.get());
//    }
//
//    /**
//     * PUT  /activities : Updates an existing activity.
//     *
//     * @param acai the activity to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated activity,
//     * or with status 400 (Bad Request) if the activity is not valid,
//     * or with status 500 (Internal Server Error) if the activity couldn't be updated
//     */
//    @PutMapping("/activities")
//    public ResponseEntity<Acai> updateActivity(
//            @RequestBody Acai acai) {
//
//        log.debug("REST request to update Activity : {}", acai);
//        Acai result = requestService.update(acai);
//        return ResponseEntity.ok()
//                .body(result);
//    }
//
//    /**
//     * DELETE  /activities/:id : delete the "id" activity.
//     *
//     * @param id the id of the activity to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/activities/{id}")
//    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
//        log.debug("REST request to delete Activity: {}", id);
//        requestService.delete(id);
//        return ResponseEntity.ok().build();
//    }


}
