package com.test.uds.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.test.uds.domain.QSize;
import com.test.uds.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface SizeRepository extends
        JpaRepository<Size, Long>,
        QuerydslPredicateExecutor<Size>,
        QuerydslBinderCustomizer<QSize> {

    @Override
    default void customize(QuerydslBindings bindings, QSize root) {
        // Binding all Strings.
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }

    Optional<Size> findById(Long id);

}
