package com.webflux.webflux.project.service;

import com.webflux.webflux.project.dto.PerdoruesiPunaDTO;
import com.webflux.webflux.project.entity.Perdoruesi;
import com.webflux.webflux.project.entity.Puna;
import com.webflux.webflux.project.repository.PerdoruesiRepository;
import com.webflux.webflux.project.repository.PunaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.function.BiFunction;

@Service
@Slf4j
@Transactional
public class PerdoruesiService {

    @Autowired
    private PerdoruesiRepository perdoruesiRepository;

    @Autowired
    private PunaRepository punaRepository;

    public Mono<Perdoruesi> createUser(Perdoruesi user){
        return perdoruesiRepository.save(user);
    }

    public Flux<Perdoruesi> getAllUsers(){
        return perdoruesiRepository.findAll();
    }

    public Mono<Perdoruesi> findById(Integer userId){
        return perdoruesiRepository.findById(userId);
    }

    public Mono<Perdoruesi> updateUser(Integer userId, Perdoruesi user){
        return perdoruesiRepository.findById(userId)
                .flatMap(dbUser -> {
                    dbUser.setMosha(user.getMosha());
                    dbUser.setGjinia(user.getGjinia());
                    return perdoruesiRepository.save(dbUser);
                });
    }

    public Mono<Perdoruesi> deleteUser(Integer userId){
        return perdoruesiRepository.findById(userId)
                .flatMap(existingUser -> perdoruesiRepository.delete(existingUser)
                        .then(Mono.just(existingUser)));
    }

    public Flux<Perdoruesi> findUsersByMosha(int mosha){
        return perdoruesiRepository.findByMosha(mosha);
    }

    public Flux<Perdoruesi> fetchUsers(List<Integer> userIds) {
        return Flux.fromIterable(userIds)
                .parallel()
                .runOn(Schedulers.elastic())
                .flatMap(i -> findById(i))
                .ordered((u1, u2) -> u2.getId() - u1.getId());
    }

    private Mono<Puna> getPunaByUserId(Integer userId){
        return punaRepository.findByUserId(userId);
    }

    public Mono<PerdoruesiPunaDTO> fetchUserAndDepartment(Integer userId){
        Mono<Perdoruesi> user = findById(userId).subscribeOn(Schedulers.elastic());
        Mono<Puna> puna = getPunaByUserId(userId).subscribeOn(Schedulers.elastic());
        return Mono.zip(user, puna, perdoruesiPunaDTOBiFunction);
    }

    private BiFunction<Perdoruesi, Puna, PerdoruesiPunaDTO> perdoruesiPunaDTOBiFunction = (x1, x2) -> PerdoruesiPunaDTO.builder()
            .mosha(x1.getMosha())
            .punaId(x2.getId())
            .emriPunes(x2.getEmri())
            .emriPerdoruesit(x1.getEmri())
            .userId(x1.getId())
            .paga(x2.getPaga())
            .gjinia(x1.getGjinia()).build();

}







