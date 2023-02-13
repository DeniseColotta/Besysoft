package com.besysoft.bootcampspringboot.controlador;

import com.besysoft.bootcampspringboot.Entidades.Genero;
import com.besysoft.bootcampspringboot.servicios.interfaces.IGeneroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/generos")
public class GeneroControlador {


    @Autowired
    private IGeneroService generoServicie;

    @GetMapping
    public ResponseEntity<List<Genero>> listarGeneros() {
        return ResponseEntity.ok().body(generoServicie.getAll());
    }

    @GetMapping(path = "/{generos}")
    public ResponseEntity<List<Genero>> filtrarPeliculaGenero(@PathVariable("generos") String nombreGenero) {
        try {
            return ResponseEntity.ok().body(generoServicie.filtrarPeliculaPorGenero(nombreGenero));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> crearGenero(@RequestBody Genero generoNuevo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(generoServicie.agregarGenero(generoNuevo));

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizarGenero(@PathVariable Long id, @RequestBody Genero genero) {
        try {
            return ResponseEntity.ok(generoServicie.updateGenero(id, genero));

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}


