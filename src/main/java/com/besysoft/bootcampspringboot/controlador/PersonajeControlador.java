package com.besysoft.bootcampspringboot.controlador;

import com.besysoft.bootcampspringboot.Entidades.Personaje;
import com.besysoft.bootcampspringboot.servicios.interfaces.IPersonajeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/personajes")

public class PersonajeControlador {

    @Autowired
    private IPersonajeService servicePersonaje;

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<Personaje>> filtrarPersonaje() {
        return ResponseEntity.ok().body(servicePersonaje.getAll());
    }

    @GetMapping(path = "/nombres/{nombres}")
    ResponseEntity<?> filtrarPersonajePorNombre(@PathVariable("nombres") String nombrePersonaje) {
        try {
            return ResponseEntity.ok().body(servicePersonaje.filtrarPersonajePorNombre(nombrePersonaje));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping(path = "/edades/{edades}")
    ResponseEntity<?> filtrarPersonajePorEdad(@PathVariable("edades") int edad) {
        try {
            return ResponseEntity.ok().body(servicePersonaje.filtrarPersonajesPorEdad(edad));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping(path = ("/edades"))
    ResponseEntity<?> filtrarPersonajePorRangoEdad(@RequestParam int desde, @RequestParam int hasta) {
        try {
            return ResponseEntity.ok().body(servicePersonaje.filtrarPersonajesPorRangoEdad(desde, hasta));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PostMapping()
    public ResponseEntity<?> altaPersonaje(@RequestBody Personaje personajeNuevo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(servicePersonaje.agregarPersonaje(personajeNuevo));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }


    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizarPersonaje(@PathVariable Long id,
                                                 @RequestBody Personaje personaje) {
        try {
            return ResponseEntity.ok(servicePersonaje.updatePersonaje(id, personaje));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }
}


