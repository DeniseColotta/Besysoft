package com.besysoft.bootcampspringboot.repositorios.database;

import com.besysoft.bootcampspringboot.dominios.PeliculaSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPeliculaRepository extends JpaRepository<PeliculaSerie,Long> {

    @Query("select p from PeliculaSerie p where p.fechaDeCreacion BETWEEN ?1 AND ?2")
    List<PeliculaSerie> findPeliculaByFechaBetween(LocalDate fecha1, LocalDate fecha2);

    @Query("select p from PeliculaSerie p where p.calificacion > :desde and p.calificacion < :hasta")
    List<PeliculaSerie> findPeliculaByCalificacionBetween(Integer desde, Integer hasta);

    @Query("select p from PeliculaSerie p where p.titulo = :titulo")
    Optional<PeliculaSerie> findPeliculaByTitulo(String titulo);




}
