package com.scotiabank.reto.controllers;

import com.scotiabank.reto.models.Alumno;
import com.scotiabank.reto.services.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/alumnos")
@Validated
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping
    public Mono<Void> crearAlumno(@Valid @RequestBody Alumno alumno) {
        return alumnoService.crearAlumno(alumno);
    }

    @GetMapping
    public Flux<Alumno> obtenerAlumnos() {
        return alumnoService.obtenerAlumnos();
    }
}
