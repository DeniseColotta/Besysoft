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
        return ResponseEntity.ok().body(servicePersonaje.filtrarPersonajePorNombre(nombrePersonaje));
    }


    @GetMapping(path = "/edades/{edades}")
    ResponseEntity<?> filtrarPersonajePorEdad(@PathVariable("edades") int edad) {

        return ResponseEntity.ok().body(servicePersonaje.filtrarPersonajesPorEdad(edad));
    }


    @GetMapping(path = ("/edades"))
    ResponseEntity<?> filtrarPersonajePorRangoEdad(@RequestParam int desde, @RequestParam int hasta) {

        return ResponseEntity.ok().body(servicePersonaje.filtrarPersonajesPorRangoEdad(desde, hasta));
    }


    @PostMapping()
    public ResponseEntity<?> altaPersonaje(@RequestBody Personaje personajeNuevo) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(servicePersonaje.agregarPersonaje(personajeNuevo));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizarPersonaje(@PathVariable Long id,
                                                 @RequestBody Personaje personaje) {

        try {
            return ResponseEntity.ok(servicePersonaje.updatePersonaje(id, personaje));

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

}

