package com.besysoft.bootcampspringboot.repositorios.database;

import com.besysoft.bootcampspringboot.modelos.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPersonajeRepository extends JpaRepository<Personaje, Long> {

    @Query("select pr from Personaje pr where pr.nombre = :nombre")
    Optional<Personaje> findPersonajeByNombre(String nombre);

    @Query("select pr from Personaje pr where pr.edad = :edad")
    List<Personaje> findPersonajesByEdad(int edad);

    @Query("select pr from Personaje pr where pr.edad between :desde and :hasta")
    List<Personaje> findPersonajesByRangoEdadBetween(int desde, int hasta);


}
