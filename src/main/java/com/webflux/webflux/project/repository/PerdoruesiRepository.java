package com.webflux.webflux.project.repository;

import com.webflux.webflux.project.entity.Perdoruesi;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PerdoruesiRepository extends ReactiveCrudRepository<Perdoruesi,Long> {
    @Query("select * from perdoruesit where mosha >= $1")
    Flux<Perdoruesi> findByMosha(int mosha);

    Mono<Perdoruesi> findById(Integer userId);
}
