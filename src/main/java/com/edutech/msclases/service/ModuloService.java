package com.edutech.msclases.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.msclases.model.Modulo;
import com.edutech.msclases.repository.ModuloRepository;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    public Modulo save(Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    public Modulo findById(int idModulo) {
        return moduloRepository.findById(idModulo);
    }

    public List<Modulo> findAll() {
        return moduloRepository.findAll();
    }

    public void deleteById(int idCurso) {
        moduloRepository.deleteById(idCurso);
    }
}
