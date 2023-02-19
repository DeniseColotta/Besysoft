package com.besysoft.bootcampspringboot.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PeliculaSerieResponseDto {

    private Long id;
    private String titulo;
    private LocalDate fechaDeCreacion;
    private int calificacion;
}
