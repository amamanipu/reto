package com.scotiabank.reto.repositories;

import com.scotiabank.reto.models.Alumno;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AlumnoRepositoryImpl implements AlumnoRepository {
    private final Map<String, Alumno> alumnos = new HashMap<>();

    @Override
    public Mono<Alumno> save(Alumno alumno) {
        alumnos.put(String.valueOf(alumno.getId()), alumno);
        return Mono.just(alumno);
    }

    @Override
    public Flux<Alumno> findAll() {
        return Flux.fromIterable(alumnos.values());
    }

    @Override
    public Mono<Boolean> existsById(Integer id) {
        return Mono.just(alumnos.values().stream().anyMatch(alumno -> alumno.getId().equals(id)));
    }
}
