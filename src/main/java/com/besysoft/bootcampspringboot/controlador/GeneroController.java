package com.besysoft.bootcampspringboot.controlador;
import com.besysoft.bootcampspringboot.dominio.Genero;
import com.besysoft.bootcampspringboot.utilidades.Datos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/generos")
public class GeneroController {

    public Datos datos=new Datos();

    @GetMapping
    public ResponseEntity<List<Genero>> listarGeneros() {
        List<Genero> generos = datos.crearGenero();
        return ResponseEntity.ok().body(generos);
    }

    @PostMapping
    public ResponseEntity<List<Genero>> crearGenero(@RequestBody Genero generoNuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(datos.agregarGenero(generoNuevo));
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<?>actualizarGenero(@PathVariable Long id,
                                                                  @RequestBody Genero genero){
        try{
        return ResponseEntity.ok(datos.updateGenero(id,genero));
    } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }}

