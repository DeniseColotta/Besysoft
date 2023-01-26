package com.besysoft.bootcampspringboot.utilidades;

import com.besysoft.bootcampspringboot.dominio.Genero;
import com.besysoft.bootcampspringboot.dominio.PeliculaSerie;
import com.besysoft.bootcampspringboot.dominio.Personaje;
import com.besysoft.bootcampspringboot.dominio.PersonajePeliculaSerie;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class Datos {

    public List<Personaje> crearPersonaje() {
        List<Personaje> personajes = new ArrayList<>();

        Personaje personaje1 = new Personaje(1, "Batman", 40, 90.0, "Batman es la identidad secreta de Bruce Wayne, un empresario multimillonario, galán y filántropo.");
        Personaje personaje2 = new Personaje(2, "El Guazón", 50, 100.0, "Es uno de los criminales más notables de Gotham City, y es el enemigo principal de Batman");
        Personaje personaje3 = new Personaje(3, "Spiderman", 50, 80.0, "Se trata de un superhéroe que emplea sus habilidades sobrehumanas, reminiscentes de una araña, para combatir a otros supervillanos que persiguen fines siniestros.");
        Personaje personaje4 = new Personaje(4, "Gru", 10, 20.0, "El pequeño Gru es el mayor fan del clan y sueña con ser el nuevo 'salvaje'. Cuando consigue presentarse ante ellos, solo recibe burlas por su edad y su aspecto");
        personajes.add(personaje1);
        personajes.add(personaje2);
        personajes.add(personaje3);
        personajes.add(personaje4);
        return personajes;
    }

    public List<Genero> crearGenero() {
        List<Genero> genero = new ArrayList<>();
        Genero genero1 = new Genero(1, "comedia");
        Genero genero2 = new Genero(2, "accionAventura");
        Genero genero3 = new Genero(3, "infantil");
        Genero genero4 = new Genero(4, "drama");
        Genero genero5 = new Genero(5, "terror");
        genero.add(genero1);
        genero.add(genero2);
        genero.add(genero3);
        genero.add(genero4);
        genero.add(genero5);
        return genero;
    }


    public List<PeliculaSerie> crearPelicula(List<Genero> genero) {
        List<PeliculaSerie> peliculas = new ArrayList<>();


        PeliculaSerie pelicula1 = new PeliculaSerie(1, "Batman", Fecha.formatear("03102000"), 4, genero.get(1));
        PeliculaSerie pelicula2 = new PeliculaSerie(2, "Spiderman", Fecha.formatear("04012005"), 4, genero.get(1));
        PeliculaSerie pelicula3 = new PeliculaSerie(3, "Los Minions", Fecha.formatear("02012010"), 4, genero.get(2));
        PeliculaSerie pelicula4 = new PeliculaSerie(4, "No mires para arriba", Fecha.formatear("02092022"), 5, genero.get(3));
        peliculas.add(pelicula1);
        peliculas.add(pelicula2);
        peliculas.add(pelicula3);
        peliculas.add(pelicula4);

        return peliculas;
    }

    public List<PersonajePeliculaSerie> crearPeliculaPersonaje(List<PeliculaSerie> peliculas, List<Personaje> personajes) {
        List<PersonajePeliculaSerie> personajePeliculaSeries = new ArrayList<>();


        PersonajePeliculaSerie personajePelicula1 = new PersonajePeliculaSerie(1, personajes.get(0), peliculas.get(0));
        PersonajePeliculaSerie personajePelicula2 = new PersonajePeliculaSerie(2, personajes.get(1), peliculas.get(0));
        PersonajePeliculaSerie personajePelicula3 = new PersonajePeliculaSerie(3, personajes.get(3), peliculas.get(2));
        personajePeliculaSeries.add(personajePelicula1);
        personajePeliculaSeries.add(personajePelicula2);
        personajePeliculaSeries.add(personajePelicula3);

        return personajePeliculaSeries;

    }


    public List<Personaje> filtrarPersonajePorNombre(String nombre) {
        List<Personaje> personajes = crearPersonaje();
        return personajes.stream().filter(personaje -> personaje.getNombre().equalsIgnoreCase(nombre)).collect(Collectors.toList());
    }


    public List<Personaje> filtrarPersonajesPorEdad(int edad) {
        List<Personaje> personajes = crearPersonaje();
        return personajes.stream().filter(personaje -> personaje.getEdad() == edad).collect(Collectors.toList());
    }
    public List<Personaje> filtrarPersonajesPorRangoEdad(int desde,int hasta) {
        List<Personaje> personajes = crearPersonaje();
        return personajes.stream().filter(personaje -> personaje.getEdad() >= desde && personaje.getEdad()<=hasta).collect(Collectors.toList());

    }
    public List<PeliculaSerie> filtrarPeliculaPorFecha(String desde,String hasta) {
        List<Genero>generos=crearGenero();
        List<PeliculaSerie> peliculas = crearPelicula(generos);

        LocalDate fecha1 = Fecha.formatear(desde);
        LocalDate fecha2 = Fecha.formatear(hasta);
        return peliculas.stream().filter(pelicula ->pelicula.getFechaDeCreacion().isAfter(fecha1) && pelicula.getFechaDeCreacion().isBefore(fecha2)).collect(Collectors.toList());

    }

    public List<PeliculaSerie> filtrarPeliculaPorCalificacion(int desde,int hasta) {
        List<Genero>generos=crearGenero();
        List<PeliculaSerie> peliculas = crearPelicula(generos);
        return peliculas.stream().filter(pelicula -> pelicula.getCalificacion() >= desde && pelicula.getCalificacion()<=hasta).collect(Collectors.toList());

    }
    public Optional<PeliculaSerie> filtrarPeliculaTitulo(String titulo) {
        List<Genero> generos = crearGenero();
        List<PeliculaSerie> peliculas = crearPelicula(generos);
        return peliculas.stream().filter(peliculaSerie -> peliculaSerie.getTitulo().equalsIgnoreCase(titulo)).findAny();
    }


    public List<PeliculaSerie> peliculaPorGenero(String nombreGenero) {
        List<Genero> generos = crearGenero();
        List<PeliculaSerie> peliculas = crearPelicula(generos);
        return peliculas.stream().filter(peliculaSerie -> peliculaSerie.getGenero().getNombre().equalsIgnoreCase(nombreGenero)).collect(Collectors.toList());

    }

    public List<PeliculaSerie> agregarPelicula(PeliculaSerie nuevaPelicula) {
        List<Genero> generos = crearGenero();
        List<PeliculaSerie> peliculas = crearPelicula(generos);
        nuevaPelicula.setId((long) (peliculas.size() + 1));
        peliculas.add(nuevaPelicula);
        return peliculas;
    }

    public List<Personaje> agregarPersonaje(Personaje nuevoPersonaje) {
        List<Personaje> personajes = crearPersonaje();
        nuevoPersonaje.setId((long) (personajes.size() + 1));
        personajes.add(nuevoPersonaje);
        return personajes;
    }

    public List<Genero> agregarGenero(Genero nuevoGenero) {
        List<Genero> generos = crearGenero();
        nuevoGenero.setId((long) (generos.size() + 1));
        generos.add(nuevoGenero);
        return generos;

}
    public Optional<PeliculaSerie> updatePelicula(long id, PeliculaSerie pelicula) {
        List<Genero> generos = crearGenero();
        List<PeliculaSerie> peliculas=crearPelicula(generos);


        Optional<PeliculaSerie> oPelicula = peliculas.stream().
                filter(p -> p.getId() == id)
                .findAny();

        if(!oPelicula.isPresent()){
            throw new RuntimeException("El Id ingresado no existe");
        }

        peliculas.forEach(p -> {
            if(p.getId() == id){
                p.setTitulo(pelicula.getTitulo());
                p.setCalificacion(pelicula.getCalificacion());
                p.setFechaDeCreacion(pelicula.getFechaDeCreacion());
                p.setGenero(pelicula.getGenero());
            }
        } );
        return oPelicula;
    }
    public Optional<Personaje> updatePersonaje(long id, Personaje personaje) {

        List<Personaje> personajes=crearPersonaje();

        Optional<Personaje> oPersonaje = personajes.stream().
                filter(pr -> pr.getId() == id)
                .findAny();

        if(!oPersonaje.isPresent()){
            throw new RuntimeException("El Id ingresado no existe");
        }


        personajes.forEach(pr -> {
            if(pr.getId() == id){
                pr.setNombre(pr.getNombre());
                pr.setEdad(pr.getEdad());
                pr.setPeso(pr.getPeso());
                pr.setHistoria(pr.getHistoria());

            }


            });return oPersonaje;
        }

    public Optional<Genero> updateGenero(long id, Genero genero) {

        List<Genero> generos=crearGenero();


        Optional<Genero> oGenero = generos.stream().
                filter(pr -> pr.getId() == id)
                .findAny();

        if(!oGenero.isPresent()){
            throw new RuntimeException("El Id ingresado no existe");
        }


        generos.forEach(g -> {
            if(g.getId() == id){
                g.setNombre(g.getNombre());


            }


        });return oGenero;
    }}


