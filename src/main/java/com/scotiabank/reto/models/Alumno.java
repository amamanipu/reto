package com.scotiabank.reto.models;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
    @NotNull
    private Integer id;
    @NotNull
    private String nombre;
    private String apellido;
    @NotNull
    private Integer edad;
    @NotNull
    private Boolean estado;
}
