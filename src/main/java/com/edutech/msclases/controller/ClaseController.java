package com.edutech.msclases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.msclases.model.Clase;
import com.edutech.msclases.model.Modulo;
import com.edutech.msclases.service.ClaseService;
import com.edutech.msclases.service.ModuloService;

@RestController
@RequestMapping("/api/v1/clases")
public class ClaseController {

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private ClaseService claseService;

    @GetMapping
    public ResponseEntity<List<Clase>> listarClases() {
        List<Clase> clases = claseService.findAll();
        if(clases.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(clases, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Clase> createClase(@RequestBody Clase clase) {
        int idLink = clase.getModulo().getIdModulo();
        Modulo modulo = moduloService.moduloxId(idLink);
        if(modulo != null) {
            clase.setModulo(modulo);
        }

        Clase nuevaClase = claseService.save(clase);
        return new ResponseEntity<>(nuevaClase, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{idClase}")
    public ResponseEntity<Clase> readClase(@PathVariable int idClase) {
        try {
            Clase clase = claseService.findById(idClase);
            return new ResponseEntity<>(clase, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idClase}")
    public ResponseEntity<Clase> updateClase(@PathVariable int idClase, @RequestBody Clase clase) {
        try {
            Clase cla = claseService.findById(idClase);
            cla.setIdClase(idClase);
            cla.setIdCurso(clase.getIdCurso());
            cla.setTitulo(clase.getTitulo());
            cla.setDescripcion(clase.getDescripcion());
            cla.setCategoria(clase.getCategoria());
            cla.setFechaCreacion(clase.getFechaCreacion());
            cla.setPublicado(clase.getPublicado());

            claseService.save(cla);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idClase}")
    public ResponseEntity<?> deleteClase(@PathVariable int idClase) {
        try {
            claseService.deleteById(idClase);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
