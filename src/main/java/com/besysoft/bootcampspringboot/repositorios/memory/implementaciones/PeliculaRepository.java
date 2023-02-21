package com.besysoft.bootcampspringboot.repositorios.memory.implementaciones;

import com.besysoft.bootcampspringboot.dominios.PeliculaSerie;
import com.besysoft.bootcampspringboot.repositorios.memory.interfaces.IPeliculaRepository;
import com.besysoft.bootcampspringboot.utilidades.Fecha;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository

public class PeliculaRepository implements IPeliculaRepository {

    List<PeliculaSerie> peliculas;


   public PeliculaRepository(){
        this.peliculas = new ArrayList<>();

        PeliculaSerie batmanPeli = new PeliculaSerie(1L, "Batman", Fecha.formatear("03-10-2000"), 4);
        PeliculaSerie spidermanPeli = new PeliculaSerie(2L, "Spiderman", Fecha.formatear("04-01-2005"), 4);
        PeliculaSerie minionsPeli = new PeliculaSerie(3L, "Los Minions", Fecha.formatear("02-01-2010"), 4);
        PeliculaSerie noMiresPeli = new PeliculaSerie(4L, "No mires para arriba", Fecha.formatear("02-09-2022"), 5);
        peliculas.add(batmanPeli);
        peliculas.add(spidermanPeli);
        peliculas.add(minionsPeli);
        peliculas.add(noMiresPeli);

    }
    @Override
    public List<PeliculaSerie> obtenerTodos() {
        return peliculas;}

    @Override
    public List<PeliculaSerie> filtrarPeliculaPorFecha(String desde, String hasta) {

        LocalDate fecha1 = Fecha.formatear(desde);
        LocalDate fecha2 = Fecha.formatear(hasta);
        return peliculas.stream().filter(pelicula -> pelicula.getFechaDeCreacion().isAfter(fecha1) && pelicula.getFechaDeCreacion().isBefore(fecha2)).collect(Collectors.toList());
    }

    @Override
    public List<PeliculaSerie> filtrarPeliculaPorCalificacion(int desde, int hasta) {

        return peliculas.stream().filter(pelicula -> pelicula.getCalificacion() >= desde && pelicula.getCalificacion() <= hasta).collect(Collectors.toList());
    }

    @Override
    public Optional<PeliculaSerie> filtrarPeliculaTitulo(String titulo) {

        return peliculas.stream().filter(peliculaSerie -> peliculaSerie.getTitulo().equalsIgnoreCase(titulo)).findAny();
    }

    @Override
    public PeliculaSerie agregarPelicula(PeliculaSerie nuevaPelicula) {
        nuevaPelicula.setId((long) (peliculas.size() + 1));
        peliculas.add(nuevaPelicula);
        return nuevaPelicula;
    }

    @Override
    public PeliculaSerie updatePelicula(long id, PeliculaSerie pelicula) {


        peliculas.forEach(p -> {
                    if (pelicula.getId() == id) {
                        p.setTitulo(pelicula.getTitulo());
                        p.setCalificacion(pelicula.getCalificacion());
                        p.setFechaDeCreacion(pelicula.getFechaDeCreacion());

                    }
                }
        );
        return pelicula;
    }
}
