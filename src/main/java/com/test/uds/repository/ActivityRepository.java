package com.test.uds.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.test.uds.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ActivityRepository extends
        JpaRepository<Activity, Long>,
        QuerydslPredicateExecutor<Activity> {

}
