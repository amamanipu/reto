package com.scotiabank.reto.services;

import com.scotiabank.reto.models.Alumno;
import com.scotiabank.reto.repositories.AlumnoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlumnoServiceTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        alumnoService = new AlumnoServiceImpl(alumnoRepository);
    }

    @Test
    public void testCrearAlumno_Correctamente() {
        Alumno alumno = new Alumno(1, "Angel", "Mamani", 25, true);

        when(alumnoRepository.existsById(1)).thenReturn(Mono.just(false));
        when(alumnoRepository.save(alumno)).thenReturn(Mono.just(alumno));

        Mono<Void> saved = alumnoService.crearAlumno(alumno);

        StepVerifier.create(saved)
                .verifyComplete();

        verify(alumnoRepository, times(1)).save(alumno);
    }

    @Test
    public void testCrearAlumno_Existe() {
        Alumno alumno = new Alumno(1, "Angel", "Mamani", 25, true);

        when(alumnoRepository.existsById(1)).thenReturn(Mono.just(true));

        assertThrows(ResponseStatusException.class, () -> {
            alumnoService.crearAlumno(alumno).block();
        });
        verify(alumnoRepository, never()).save(alumno);
    }

    @Test
    public void testObtenerAlumnos() {
        Alumno alumno1 = new Alumno(1, "Angel", "Mamani", 25, true);
        Alumno alumno2 = new Alumno(2, "Jose", "Campos", 40, false);

        when(alumnoRepository.findAll()).thenReturn(Flux.just(alumno1, alumno2));

        Flux<Alumno> result = alumnoService.obtenerAlumnos();

        StepVerifier.create(result)
                .expectNext(alumno1, alumno2)
                .verifyComplete();
    }
}
