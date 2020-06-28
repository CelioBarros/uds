package com.test.uds.web.rest;

import com.querydsl.core.types.Predicate;
import com.test.uds.domain.Activity;
import com.test.uds.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ActivityResource {

    private final Logger log = LoggerFactory.getLogger(ActivityResource.class);


    private final ActivityService activityService;

    public ActivityResource(ActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * POST  /activities : Create a new activity.
     *
     * @param activity the activity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new activity,
     * or with status 400 (Bad Request) if the activity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/activities")
    public ResponseEntity<Activity> createActivity(
            @RequestBody Activity activity) throws URISyntaxException {

        log.debug("REST request to save Activity : {}", activity);
        Activity result = activityService.create(activity);
        return ResponseEntity.created(new URI("/api/activities/" + result.getId()))
                .body(result);
    }

    /**
     * GET  /activities/:id : get the "id" activity.
     *
     * @param id the id of the activity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the activity,
     * or with status 404 (Not Found)
     */
    @GetMapping("/activities/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        log.debug("REST request to get Activity : {}", id);
        Optional<Activity> result = activityService.getById(id);
        return (!result.isPresent()) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok().body(result.get());
    }

    /**
     * PUT  /activities : Updates an existing activity.
     *
     * @param activity the activity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated activity,
     * or with status 400 (Bad Request) if the activity is not valid,
     * or with status 500 (Internal Server Error) if the activity couldn't be updated
     */
    @PutMapping("/activities")
    public ResponseEntity<Activity> updateActivity(
            @RequestBody Activity activity) {

        log.debug("REST request to update Activity : {}", activity);
        Activity result = activityService.update(activity);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * DELETE  /activities/:id : delete the "id" activity.
     *
     * @param id the id of the activity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/activities/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        log.debug("REST request to delete Activity: {}", id);
        activityService.delete(id);
        return ResponseEntity.ok().build();
    }


}
