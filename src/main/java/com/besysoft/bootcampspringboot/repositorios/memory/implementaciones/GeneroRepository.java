package com.besysoft.bootcampspringboot.repositorios.memory.implementaciones;

import com.besysoft.bootcampspringboot.Entidades.Genero;
import com.besysoft.bootcampspringboot.Entidades.PeliculaSerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class GeneroRepository implements com.besysoft.bootcampspringboot.repositorios.memory.interfaces.GeneroRepository {

    List<Genero> generos;

    List<PeliculaSerie> peliculas;

    @Autowired
    public PeliculaRepository peliculaRepository;

    @Override
    public List<Genero> obtenerTodos() {

        List<PeliculaSerie> peliculas = peliculaRepository.crearPelicula();
        this.generos = new ArrayList<>();
        Genero comedia = new Genero(1L, "comedia");
        Genero accion = new Genero(2L, "accionAventura");
        Genero infantil = new Genero(3L, "infantil");
        Genero drama = new Genero(4L, "drama");
        Genero terror = new Genero(5L, "terror");


        comedia.setPeliculaSerie(List.of(peliculas.get(3)));
        accion.setPeliculaSerie(List.of(peliculas.get(1), peliculas.get(0)));
        infantil.setPeliculaSerie(List.of(peliculas.get(2)));
        generos.add(comedia);
        generos.add(accion);
        generos.add(infantil);
        generos.add(drama);
        generos.add(terror);
        return generos;
    }


    @Override
    public List<Genero> filtrarPeliculaPorGenero(String nombreGenero) {

        return generos.stream().filter(genero -> genero.getNombre().equalsIgnoreCase(nombreGenero)).collect(Collectors.toList());
    }

    public Optional<Genero> buscarGenero(String nombre) {

        return this.generos.stream().filter(g -> g.getNombre().equalsIgnoreCase(nombre)).findAny();
    }

    @Override
    public List<Genero> agregarGenero(Genero nuevoGenero) {
        nuevoGenero.setId((long) (generos.size() + 1));
        this.generos.add(nuevoGenero);
        return this.generos;
    }

    @Override
    public Genero updateGenero(long id, Genero genero) {

        generos.forEach(p -> {
            if (p.getId() == id) {
                p.setNombre(genero.getNombre());


            }
        });
        return genero;
    }
}



