package com.example.apirestrecetario.controller;

import com.example.apirestrecetario.model.Receta;
import com.example.apirestrecetario.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/receta")
public class RecetaController {

    @Autowired
    private RecetaService service;

    @GetMapping
    public List<Receta> getAllRecetas() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Receta getRecetaById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Receta saveReceta(@RequestBody Receta receta) {
        return service.save(receta);
    }

    @DeleteMapping("/{id}")
    public void deleteRecetaById(@PathVariable Long id) {
        service.deleteById(id);
    }




}
