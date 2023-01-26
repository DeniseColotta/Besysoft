package com.besysoft.bootcampspringboot.controlador;
import com.besysoft.bootcampspringboot.dominio.Genero;
import com.besysoft.bootcampspringboot.dominio.PeliculaSerie;
import com.besysoft.bootcampspringboot.utilidades.Datos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/películas")

public class PeliculaControlador {

Datos datos =new Datos();

    @GetMapping
    public
    ResponseEntity <List<PeliculaSerie>> listareliculaSerieP() {
        List<Genero> generos = datos.crearGenero();
        return ResponseEntity.ok().body(datos.crearPelicula(generos));
    }

    @GetMapping(path="/titulos/{titulos}")
    public ResponseEntity <?> filtrarPeliculaTitulo(@PathVariable("titulo") String tituloPelicula) {
       return ResponseEntity.ok().body( datos.filtrarPeliculaTitulo(tituloPelicula));
    }

    @GetMapping(path="/generos/{generos}")
    public ResponseEntity<List<PeliculaSerie>> filtrarPeliculaGenero(@PathVariable("genero") String nombreGenero) {
        return ResponseEntity.ok().body(datos.peliculaPorGenero(nombreGenero));
    }

    @GetMapping(path= ("/calificaciones"))
    ResponseEntity <?>peliculaPorRangoCalificacion(@RequestParam int desde, @RequestParam int hasta) {
        try {
            return ResponseEntity.ok().body(datos.filtrarPeliculaPorCalificacion(desde, hasta));
        }catch(RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @GetMapping(path= "/fechas")
    ResponseEntity <?>peliculaPorRangoFecha(
             @RequestParam String desde, @RequestParam  String hasta) {

        try {
            return ResponseEntity.ok().body(datos.filtrarPeliculaPorFecha(desde, hasta));

        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity <?> crearPelicula( @RequestBody PeliculaSerie peliculaNueva) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(datos.agregarPelicula(peliculaNueva));

        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    @PutMapping(path="/{id}")
    public ResponseEntity<?>actualizarPelicula(@PathVariable Long id,
                                   @RequestBody PeliculaSerie pelicula){
        try{
        return ResponseEntity.ok(datos.updatePelicula(id,pelicula));
    }
        catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

}}