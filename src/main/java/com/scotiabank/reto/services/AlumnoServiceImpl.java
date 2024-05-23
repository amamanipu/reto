package com.scotiabank.reto.services;

import com.scotiabank.reto.models.Alumno;
import com.scotiabank.reto.repositories.AlumnoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public Mono<Void> crearAlumno(Alumno alumno) {
        return alumnoRepository.existsById(alumno.getId())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID ingresado ya se encuentra registrado"));
                    } else {
                        alumnoRepository.save(alumno);
                        return Mono.empty();
                    }
                });
    }

    @Override
    public Flux<Alumno> obtenerAlumnos() {
        return alumnoRepository.findAll();
    }
}
