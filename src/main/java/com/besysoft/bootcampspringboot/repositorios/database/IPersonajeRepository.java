package com.besysoft.bootcampspringboot.repositorios.database;

import com.besysoft.bootcampspringboot.Entidades.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPersonajeRepository extends JpaRepository<Personaje, Long> {

    @Query("select pr from Personaje pr where pr.nombre = :nombre")
    Optional<Personaje> filtrarPersonajePorNombre(String nombre);

    @Query("select pr from Personaje pr where pr.edad = :edad")
    List<Personaje> filtrarPersonajesPorEdad(int edad);

    @Query("select pr from Personaje pr where pr.edad between :desde and :hasta")
    List<Personaje> filtrarPersonajesPorRangoEdad(int desde, int hasta);


}
