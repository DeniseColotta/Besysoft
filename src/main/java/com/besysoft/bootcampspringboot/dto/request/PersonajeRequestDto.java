package com.besysoft.bootcampspringboot.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeRequestDto {
    private Long id;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "solamente permite caracteres de la A - Z y números")
    @Size(min = 1, max = 50)

    private String nombre;
    private Integer edad;
    private Double peso;

    @Size(min = 10, max = 200)
    private String historia;
}
