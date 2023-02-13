package com.besysoft.bootcampspringboot.controlador;


import com.besysoft.bootcampspringboot.Entidades.PeliculaSerie;
import com.besysoft.bootcampspringboot.servicios.interfaces.IPeliculaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/películas")

public class PeliculaControlador {

    @Autowired
    private IPeliculaService pelicula;

    @GetMapping
    public ResponseEntity<List<PeliculaSerie>> listaPeliculaSerie() {

        return ResponseEntity.ok().body(pelicula.getAll());
    }

    @GetMapping(path = "/titulos/{titulos}")
    public ResponseEntity<?> filtrarPeliculaTitulo(@PathVariable("titulos") String tituloPelicula) {
        return ResponseEntity.ok().body(pelicula.filtrarPeliculaTitulo(tituloPelicula));
    }


    @GetMapping(path = ("/calificaciones"))
    ResponseEntity<?> peliculaPorRangoCalificacion(@RequestParam int desde, @RequestParam int hasta) {
        return ResponseEntity.ok().body(pelicula.filtrarPeliculaPorCalificacion(desde, hasta));

    }


    @GetMapping(path = "/fechas")
    ResponseEntity<?> peliculaPorRangoFecha(
            @RequestParam String desde, @RequestParam String hasta) {

        try {
            return ResponseEntity.ok().body(pelicula.filtrarPeliculaPorFecha(desde, hasta));

        } catch (RuntimeException ex) {
            return new ResponseEntity("Ingrese fecha con el formato dd-MM-yyyy", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> crearPelicula(@RequestBody PeliculaSerie peliculaNueva) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pelicula.agregarPelicula(peliculaNueva));


        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizarPelicula(@PathVariable Long id,
                                                @RequestBody PeliculaSerie peliculaNueva) {
        try {
            return ResponseEntity.ok().body(pelicula.updatePelicula(id, peliculaNueva));

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}