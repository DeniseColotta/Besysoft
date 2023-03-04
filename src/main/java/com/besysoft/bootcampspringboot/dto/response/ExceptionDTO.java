package com.besysoft.bootcampspringboot.dto.response;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDTO {
    private int estado;
    private String mensaje;
    private Map<String, String> detalle;
}
