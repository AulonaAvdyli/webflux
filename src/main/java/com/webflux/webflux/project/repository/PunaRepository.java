package com.webflux.webflux.project.repository;

import com.webflux.webflux.project.entity.Puna;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PunaRepository extends ReactiveCrudRepository<Puna,Integer> {
    Mono<Puna> findByUserId(Integer userId);
}