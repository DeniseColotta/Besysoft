package com.besysoft.bootcampspringboot.utilidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Fecha {

    public static LocalDate formatear(String fecha){

        DateTimeFormatter formateador = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("ddMMyyyy"))
                .toFormatter();

        return LocalDate.parse(fecha, formateador);
}}
