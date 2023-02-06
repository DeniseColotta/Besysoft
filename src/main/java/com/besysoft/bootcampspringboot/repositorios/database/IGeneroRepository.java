package com.besysoft.bootcampspringboot.repositorios.database;

import com.besysoft.bootcampspringboot.Entidades.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IGeneroRepository extends JpaRepository<Genero,Long> {

   @Query("select g from Genero g where g.nombre = :nombre")
    Optional<Genero> findByNombre(String nombre);

   /* @Query("select g from generos g where g.peliculas_id like ?1")
    List<Genero> findGeneroByPelicula(String nombreGenero);
}*/
}