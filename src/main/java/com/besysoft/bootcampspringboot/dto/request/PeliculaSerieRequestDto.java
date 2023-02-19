package com.besysoft.bootcampspringboot.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
public class PeliculaSerieRequestDto {
    private Long id;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]+$", message = "solamente permite caracteres de la A - Z")
    @Size(min = 1, max = 50)
    private String titulo;

    private LocalDate fechaDeCreacion;

    @Min(value=1, message="El valor no puede ser menor a 1")
    @Max(value=5,message="El valor no puede ser mayor a 5")
    private int calificacion;
}
