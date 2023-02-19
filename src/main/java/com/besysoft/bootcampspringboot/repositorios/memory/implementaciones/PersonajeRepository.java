package com.besysoft.bootcampspringboot.repositorios.memory.implementaciones;

import com.besysoft.bootcampspringboot.modelos.Personaje;
import com.besysoft.bootcampspringboot.repositorios.memory.interfaces.IPersonajeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonajeRepository implements IPersonajeRepository {

    private List<Personaje> personajes;

    @Override
    public List<Personaje> crearPersonaje() {
        this.personajes = new ArrayList<>();

        Personaje batman = new Personaje(1L, "Batman", 40, 90.0, "Batman es la identidad secreta de Bruce Wayne, un empresario multimillonario, galán y filántropo.");
        Personaje guazon = new Personaje(2L, "El Guazón", 50, 100.0, "Es uno de los criminales más notables de Gotham City, y es el enemigo principal de Batman");
        Personaje spiderman = new Personaje(3L, "Spiderman", 50, 80.0, "Se trata de un superhéroe que emplea sus habilidades sobrehumanas, reminiscentes de una araña, para combatir a otros supervillanos que persiguen fines siniestros.");
        Personaje gru = new Personaje(4L, "Gru", 10, 20.0, "El pequeño Gru es el mayor fan del clan y sueña con ser el nuevo 'salvaje'. Cuando consigue presentarse ante ellos, solo recibe burlas por su edad y su aspecto");

        personajes.add(batman);
        personajes.add(guazon);
        personajes.add(spiderman);
        personajes.add(gru);
        return personajes;
    }

    @Override
    public List<Personaje> filtrarPersonajePorNombre(String nombre) {
        List<Personaje> personajes = crearPersonaje();
        return personajes.stream().filter(personaje -> personaje.getNombre().equalsIgnoreCase(nombre)).collect(Collectors.toList());
    }

    @Override
    public List<Personaje> filtrarPersonajesPorEdad(int edad) {
        List<Personaje> personajes = crearPersonaje();
        return personajes.stream().filter(personaje -> personaje.getEdad() == edad).collect(Collectors.toList());
    }

    @Override
    public List<Personaje> filtrarPersonajesPorRangoEdad(int desde, int hasta) {
        List<Personaje> personajes = crearPersonaje();
        return personajes.stream().filter(personaje -> personaje.getEdad() >= desde && personaje.getEdad() <= hasta).collect(Collectors.toList());
    }

    @Override
    public List<Personaje> agregarPersonaje(Personaje nuevoPersonaje) {
        List<Personaje> personajes = crearPersonaje();
        nuevoPersonaje.setId((long) (personajes.size() + 1));
        personajes.add(nuevoPersonaje);
        return personajes;
    }

    @Override
    public Personaje updatePersonaje(long id, Personaje personaje) {


        personajes.forEach(p -> {
            if (p.getId() == id) {
                p.setNombre(personaje.getNombre());
                p.setEdad(personaje.getEdad());
                p.setPeso(personaje.getPeso());
                p.setHistoria(personaje.getHistoria());

            }
        });
        return personaje;


    }


}
