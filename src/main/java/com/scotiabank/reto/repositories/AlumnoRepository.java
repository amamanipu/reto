package com.scotiabank.reto.repositories;

import com.scotiabank.reto.models.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoRepository {
    Mono<Alumno> save(Alumno user);
    Flux<Alumno> findAll();
    Mono<Boolean> existsById(Integer id);
}
