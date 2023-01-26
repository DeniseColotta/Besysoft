package com.besysoft.bootcampspringboot.controlador;
import com.besysoft.bootcampspringboot.dominio.Personaje;
import com.besysoft.bootcampspringboot.utilidades.Datos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/personajes")

public class PersonajeControlador {

    Datos datos =new Datos();

    @GetMapping
    public @ResponseBody
    ResponseEntity <List<Personaje>> filtrarPersonaje() {
        return ResponseEntity.ok().body(datos.crearPersonaje());
    }

    @GetMapping(path="/nombres/{nombres}")
    ResponseEntity <?>filtrarPersonajePorNombre(@PathVariable("nombre") String nombrePersonaje) {
        try {
            return ResponseEntity.ok().body(datos.filtrarPersonajePorNombre(nombrePersonaje));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

        @GetMapping(path="/edades/{edades}")
    ResponseEntity<?>filtrarPersonajePorEdad(@PathVariable("edad") int edad) {
            try {
                return ResponseEntity.ok().body(datos.filtrarPersonajesPorEdad(edad));
            } catch (RuntimeException ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }
        }
    @GetMapping(path= ("/edades"))
            ResponseEntity <?>filtrarPersonajePorRangoEdad(@RequestParam int edad1,@RequestParam int edad2) {
        try {
            return ResponseEntity.ok().body(datos.filtrarPersonajesPorRangoEdad(edad1, edad2));
        }catch(RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

}

    @PostMapping()
        public ResponseEntity<?> altaPersonaje(@RequestBody Personaje personajeNuevo) {
        try{
        return ResponseEntity.status(HttpStatus.CREATED).body(datos.agregarPersonaje(personajeNuevo));
        }catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }


}
    @PutMapping(path="/{id}")
    public ResponseEntity<?>actualizarPersonaje(@PathVariable Long id,
                                                                  @RequestBody Personaje personaje){
        try{
        return ResponseEntity.ok(datos.updatePersonaje(id,personaje));
    } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

}}


