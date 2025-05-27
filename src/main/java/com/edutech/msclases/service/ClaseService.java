package com.edutech.msclases.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.msclases.model.Clase;
import com.edutech.msclases.repository.ClaseRepository;

@Service
public class ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    public Clase save(Clase clase) {
        return claseRepository.save(clase);
    }

    public Clase findById(int idClase) {
        return claseRepository.findById(idClase);
    }

    public List<Clase> findAll() {
        return claseRepository.findAll();
    }

    public void deleteById(int idClase) {
        claseRepository.deleteById(idClase);
    }

    /* public Clase clasexId(int idClase) {
        return claseRepository.getReferenceById(idClase);
    } */

    public Boolean cambiarVisibilidad(int idClase) {
        Clase buscarClase = claseRepository.findById(idClase);
        if(buscarClase != null) {
            buscarClase.setPublicado((!buscarClase.getPublicado()));
            claseRepository.save(buscarClase);
            return true;
        }

        return false;
    }
}
