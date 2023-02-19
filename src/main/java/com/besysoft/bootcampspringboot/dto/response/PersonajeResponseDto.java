package com.besysoft.bootcampspringboot.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
public class PersonajeResponseDto {
    private Long id;
    private String nombre;
    private int edad;
    private double peso;
    private String historia;
}
