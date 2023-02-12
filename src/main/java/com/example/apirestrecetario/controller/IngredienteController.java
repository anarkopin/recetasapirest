package com.example.apirestrecetario.controller;

import com.example.apirestrecetario.model.Ingrediente;
import com.example.apirestrecetario.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ingrediente")
public class IngredienteController {

    @Autowired
    private IngredienteService service;

    @GetMapping
    public List<Ingrediente> getAllIngredientes() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Ingrediente getIngredienteById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Ingrediente saveIngrediente(@RequestBody Ingrediente ingrediente) {
        return service.save(ingrediente);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredienteById(@PathVariable Long id) {
        service.deleteById(id);
    }

}
