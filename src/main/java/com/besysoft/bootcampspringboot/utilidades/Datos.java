package com.besysoft.bootcampspringboot.utilidades;



import com.besysoft.bootcampspringboot.Entidades.Genero;
import com.besysoft.bootcampspringboot.Entidades.PeliculaSerie;
import com.besysoft.bootcampspringboot.Entidades.Personaje;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Deprecated
public class Datos {

    public List<PeliculaSerie> crearPelicula() {
        List<PeliculaSerie> peliculas = new ArrayList<>();


        PeliculaSerie batmanPeli = new PeliculaSerie(1L, "Batman", Fecha.formatear("03102000"), 4);
        PeliculaSerie spidermanPeli = new PeliculaSerie(2L, "Spiderman", Fecha.formatear("04012005"), 4);
        PeliculaSerie minionsPeli = new PeliculaSerie(3L, "Los Minions", Fecha.formatear("02012010"), 4);
        PeliculaSerie noMiresPeli = new PeliculaSerie(4L, "No mires para arriba", Fecha.formatear("02092022"), 5);
        peliculas.add(batmanPeli);
        peliculas.add(spidermanPeli);
        peliculas.add(minionsPeli);
        peliculas.add(noMiresPeli);

        return peliculas;
    }
        public List<Personaje> crearPersonaje() {
        List<PeliculaSerie>peliculas=crearPelicula();
            List<Personaje> personajes = new ArrayList<>();

            Personaje batman = new Personaje(1L, "Batman", 40, 90.0, "Batman es la identidad secreta de Bruce Wayne, un empresario multimillonario, galán y filántropo.");
            Personaje guazon = new Personaje(2L, "El Guazón", 50, 100.0, "Es uno de los criminales más notables de Gotham City, y es el enemigo principal de Batman");
            Personaje spiderman = new Personaje(3L, "Spiderman", 50, 80.0, "Se trata de un superhéroe que emplea sus habilidades sobrehumanas, reminiscentes de una araña, para combatir a otros supervillanos que persiguen fines siniestros.");
            Personaje gru = new Personaje(4L, "Gru", 10, 20.0, "El pequeño Gru es el mayor fan del clan y sueña con ser el nuevo 'salvaje'. Cuando consigue presentarse ante ellos, solo recibe burlas por su edad y su aspecto");

            batman.setPeliculaSerie(List.of(peliculas.get(0)));
            guazon.setPeliculaSerie(List.of(peliculas.get(0)));
            spiderman.setPeliculaSerie(List.of(peliculas.get(1)));
            gru.setPeliculaSerie(List.of(peliculas.get(2)));
            personajes.add(batman);
            personajes.add(guazon);
            personajes.add(spiderman);
            personajes.add(gru);
            return personajes;
        }

        public List<Genero> crearGenero() {
            List<PeliculaSerie>peliculas=crearPelicula();
            List<Genero> genero = new ArrayList<>();
            Genero comedia= new Genero(1L, "comedia");
            Genero accion=new Genero(2L, "accionAventura");
            Genero infantil= new Genero(3L, "infantil");
            Genero drama=new Genero(4L, "drama");
            Genero terror=new Genero(5L, "terror");


            comedia.setPeliculaSerie(List.of(peliculas.get(3)));
            accion.setPeliculaSerie(List.of(peliculas.get(1),peliculas.get(0)));
            infantil.setPeliculaSerie(List.of(peliculas.get(2)));
            genero.add(comedia);
            genero.add(accion);
            genero.add(infantil);
            genero.add(drama);
            genero.add(terror);
            return genero;
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
            List<PeliculaSerie> peliculas = crearPelicula();

            LocalDate fecha1 = Fecha.formatear(desde);
            LocalDate fecha2 = Fecha.formatear(hasta);
            return peliculas.stream().filter(pelicula ->pelicula.getFechaDeCreacion().isAfter(fecha1) && pelicula.getFechaDeCreacion().isBefore(fecha2)).collect(Collectors.toList());

        }

        public List<PeliculaSerie> filtrarPeliculaPorCalificacion(int desde,int hasta) {
            List<Genero>generos=crearGenero();
            List<PeliculaSerie> peliculas = crearPelicula();
            return peliculas.stream().filter(pelicula -> pelicula.getCalificacion() >= desde && pelicula.getCalificacion()<=hasta).collect(Collectors.toList());

        }
        public Optional<PeliculaSerie> filtrarPeliculaTitulo(String titulo) {
            List<Genero> generos = crearGenero();
            List<PeliculaSerie> peliculas = crearPelicula();
            return peliculas.stream().filter(peliculaSerie -> peliculaSerie.getTitulo().equalsIgnoreCase(titulo)).findAny();
        }


        public List<Genero> filtrarPeliculaPorGenero(String nombreGenero) {
            List<PeliculaSerie> peliculas = crearPelicula();
            List<Genero> generos = crearGenero();
            return generos.stream().filter(genero -> genero.getNombre().equalsIgnoreCase(nombreGenero)).collect(Collectors.toList());

        }

        public List<PeliculaSerie> agregarPelicula(PeliculaSerie nuevaPelicula) {
            List<Genero> generos = crearGenero();
            List<PeliculaSerie> peliculas = crearPelicula();
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
            List<PeliculaSerie> peliculas=crearPelicula();


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

