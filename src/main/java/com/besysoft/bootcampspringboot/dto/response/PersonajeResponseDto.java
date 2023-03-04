package com.besysoft.bootcampspringboot.dto.response;

import lombok.*;

import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeResponseDto {
    private Long id;
    private String nombre;
    private int edad;
    private double peso;
    private String historia;
}
