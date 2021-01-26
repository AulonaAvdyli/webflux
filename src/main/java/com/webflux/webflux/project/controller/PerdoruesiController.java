package com.webflux.webflux.project.controller;

import com.webflux.webflux.project.dto.PerdoruesiPunaDTO;
import com.webflux.webflux.project.entity.Perdoruesi;
import com.webflux.webflux.project.service.PerdoruesiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/perdoruesit")
public class PerdoruesiController {

   private final PerdoruesiService userService;

    public PerdoruesiController(PerdoruesiService userService) {
        this.userService = userService;
    }

    @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public Mono<Perdoruesi> create(@RequestBody Perdoruesi user) {
       return userService.createUser(user);
   }

    @GetMapping
    public Flux<Perdoruesi> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<Perdoruesi>> getUserById(@PathVariable Integer userId){
        Mono<Perdoruesi> user = userService.findById(userId);
        return user.map( u -> ResponseEntity.ok(u))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}")
    public Mono<ResponseEntity<Perdoruesi>> updateUserById(@PathVariable Integer userId, @RequestBody Perdoruesi perdoruesi){
        return userService.updateUser(userId,perdoruesi)
                .map(updatedUser -> ResponseEntity.ok(updatedUser))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{userId}")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable Integer userId){
        return userService.deleteUser(userId)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @GetMapping("/mosha/{mosha}")
    public Flux<Perdoruesi> getUsersByAge(@PathVariable int mosha) {
        return userService.findUsersByMosha(mosha);
    }

    @PostMapping("/kerko/id")
    public Flux<Perdoruesi> fetchUsersByIds(@RequestBody List<Integer> ids) {
        return userService.fetchUsers(ids);
    }

    @GetMapping("/{userId}/puna")
    public Mono<PerdoruesiPunaDTO> fetchUserAndDepartment(@PathVariable Integer userId){
        return userService.fetchUserAndDepartment(userId);
    }

}


