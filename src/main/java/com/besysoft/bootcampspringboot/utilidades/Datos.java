package com.besysoft.bootcampspringboot.utilidades;

import com.besysoft.bootcampspringboot.dominio.Genero;
import com.besysoft.bootcampspringboot.dominio.PeliculaSerie;
import com.besysoft.bootcampspringboot.dominio.Personaje;
import com.besysoft.bootcampspringboot.dominio.PersonajePeliculaSerie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Datos implements iDatos {

    List<PeliculaSerie>peliculas=new ArrayList<>();
    List<Personaje>personajes=new ArrayList<>();
    List<Genero>genero=new ArrayList<>();
    List<PersonajePeliculaSerie>personajePeliculaSeries=new ArrayList<>();

     public void crear() {

         Personaje personaje1 = new Personaje("Batman", 40, 90.0, "Batman es la identidad secreta de Bruce Wayne, un empresario multimillonario, galán y filántropo.");
         Personaje personaje2 = new Personaje("El Guazón", 50, 100.0, "Es uno de los criminales más notables de Gotham City, y es el enemigo principal de Batman");
         Personaje personaje3 = new Personaje("Spiderman", 50, 80.0, "Se trata de un superhéroe que emplea sus habilidades sobrehumanas, reminiscentes de una araña, para combatir a otros supervillanos que persiguen fines siniestros.");
         Personaje personaje4 = new Personaje("Gru", 10, 20.0, "El pequeño Gru es el mayor fan del clan y sueña con ser el nuevo 'salvaje'. Cuando consigue presentarse ante ellos, solo recibe burlas por su edad y su aspecto");
         personajes.add(personaje1);
         personajes.add(personaje2);
         personajes.add(personaje3);
         personajes.add(personaje4);


        Genero genero1 = new Genero("comedia");
        Genero genero2 = new Genero("accionAventura");
        Genero genero3 = new Genero("infantil");
        Genero genero4 = new Genero("drama");
        Genero genero5 = new Genero("terror");
        genero.add(genero1);
        genero.add(genero2);
        genero.add(genero3);
        genero.add(genero4);
        genero.add(genero5);


        PeliculaSerie pelicula1 = new PeliculaSerie("Batman", LocalDate.of(2000, 02, 02), 4, genero2);
        PeliculaSerie pelicula2 = new PeliculaSerie("Spiderman", LocalDate.of(2010, 03, 04), 4, genero2);
        PeliculaSerie pelicula3 = new PeliculaSerie("Los Minions", LocalDate.of(2022, 05, 02), 4, genero3);
        PeliculaSerie pelicula4 = new PeliculaSerie("No mires para arriba", LocalDate.of(2021, 12, 04), 5, genero4);
        peliculas.add(pelicula1);
        peliculas.add(pelicula2);
        peliculas.add(pelicula3);
        peliculas.add(pelicula4);


        PersonajePeliculaSerie personajePelicula1 = new PersonajePeliculaSerie(personaje1, pelicula1);
        PersonajePeliculaSerie personajePelicula2 = new PersonajePeliculaSerie(personaje2, pelicula1);
        PersonajePeliculaSerie personajePelicula3 = new PersonajePeliculaSerie(personaje3, pelicula2);
         personajePeliculaSeries.add(personajePelicula1);
         personajePeliculaSeries.add(personajePelicula2);
         personajePeliculaSeries.add(personajePelicula3);

     }


    @Override
    public List<Personaje> listarPersonajes() {
               crear();
               return personajes;
    }


    public Personaje filtrarPersonajePorNombre(String nombre) {
     Personaje personajeNombre=new Personaje();
        crear();
        for (Personaje m : personajes) {
            if (m.getNombre().equals(nombre)) {
                personajeNombre=m;


        }}return personajeNombre;}



    @Override
    public List<Personaje> filtrarPersonajesPorEdad(int edad) {
        List<Personaje>personajeEdad=new ArrayList<>();
        crear();
        for (Personaje e : personajes) {
            if (e.getEdad()==edad){
                personajeEdad.add(e);
            }}return personajeEdad;

    }

    @Override
    public List<PeliculaSerie> listarPeliculaSerie() {
          crear();
        return peliculas;
    }

    @Override
    public PeliculaSerie filtrarPeliculaTitulo(String titulo) {
        PeliculaSerie peliSerie=new PeliculaSerie();
        crear();
        for (PeliculaSerie p : peliculas) {
            if (p.getTitulo().equals(titulo)) {
                peliSerie=p;


            }}return peliSerie;}


    @Override
    public List<PeliculaSerie> peliculaPorGenero(String nombreGenero) {
          List<PeliculaSerie>peliculaGenero=new ArrayList<>();
          crear();
          for(PeliculaSerie g:peliculas){
           if(g.getGenero().getNombre().equalsIgnoreCase(nombreGenero)){
               peliculaGenero.add(g);

           }
       }return peliculaGenero;



}}


