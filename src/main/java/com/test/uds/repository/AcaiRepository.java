package com.test.uds.repository;

import com.querydsl.core.types.dsl.StringPath;
import com.test.uds.domain.Acai;
import com.test.uds.domain.QAcai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface AcaiRepository extends
        JpaRepository<Acai, Long>,
        QuerydslPredicateExecutor<Acai>,
        QuerydslBinderCustomizer<QAcai> {

    @Override
    default void customize(QuerydslBindings bindings, QAcai root) {
        // Binding all Strings.
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }

    Optional<Acai> findById(Long id);

}
