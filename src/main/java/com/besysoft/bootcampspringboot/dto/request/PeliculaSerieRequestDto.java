package com.besysoft.bootcampspringboot.dto.request;


import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeliculaSerieRequestDto {
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "solamente permite caracteres de la A - Z y números")
    @Size(min = 1, max = 50)
    private String titulo;

    private LocalDate fechaDeCreacion;

    @Min(value=1, message="El valor no puede ser menor a 1")
    @Max(value=5,message="El valor no puede ser mayor a 5")
    private Integer calificacion;
}
