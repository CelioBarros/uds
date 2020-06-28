package com.test.uds.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.test.uds.domain.Request;
import com.test.uds.domain.QRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RequestRepository extends
        JpaRepository<Request, Long>,
        QuerydslPredicateExecutor<Request>,
        QuerydslBinderCustomizer<QRequest> {

    @Override
    default void customize(QuerydslBindings bindings, QRequest root) {
        // Binding all Strings.
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }

    Optional<Request> findById(Long id);

}
