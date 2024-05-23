package com.scotiabank.reto.services;

import com.scotiabank.reto.models.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {
    Mono<Void> crearAlumno(Alumno alumno);
    Flux<Alumno> obtenerAlumnos();
}
