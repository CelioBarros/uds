package com.test.uds.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.test.uds.domain.Flavor;
import com.test.uds.domain.QFlavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface FlavorRepository extends
        JpaRepository<Flavor, Long>,
        QuerydslPredicateExecutor<Flavor>,
        QuerydslBinderCustomizer<QFlavor> {

    @Override
    default void customize(QuerydslBindings bindings, QFlavor root) {
        // Binding all Strings.
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }

    Optional<Flavor> findById(Long id);

}
