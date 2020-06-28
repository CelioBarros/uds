package com.test.uds.service;

import com.querydsl.core.types.Predicate;
import com.test.uds.domain.Activity;
import com.test.uds.repository.ActivityRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity create(Activity activity) {

        return activityRepository.save(activity);
    }

    public Optional<Activity> getById(Long id) {
        return activityRepository.findById(id);
    }

    public Activity update(Activity activity) {
        return activityRepository.save(activity);
    }

    public void delete(Long id) {
        activityRepository.deleteById(id);
    }
}
