package com.besysoft.bootcampspringboot.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaSerieResponseDto {

    private Long id;
    private String titulo;
    private LocalDate fechaDeCreacion;
    private int calificacion;
}
