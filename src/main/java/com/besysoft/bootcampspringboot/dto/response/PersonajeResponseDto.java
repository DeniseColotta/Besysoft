package com.besysoft.bootcampspringboot.dto.response;

import lombok.*;

import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeResponseDto {
    private Long id;
    private String nombre;
    private int edad;
    private double peso;
    private String historia;
}
