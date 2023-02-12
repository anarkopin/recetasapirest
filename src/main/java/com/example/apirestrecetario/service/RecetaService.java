package com.example.apirestrecetario.service;

import com.example.apirestrecetario.model.Receta;
import com.example.apirestrecetario.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository repository;

    public List<Receta> findAll() {
        return repository.findAll();
    }

    public Receta findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Receta save(Receta receta) {
        return repository.save(receta);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
