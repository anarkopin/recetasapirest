package com.example.apirestrecetario.controller;

import com.example.apirestrecetario.model.Cocinero;
import com.example.apirestrecetario.model.Receta;
import com.example.apirestrecetario.service.CocineroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cocinero")
public class CocineroController {

    @Autowired
    private CocineroService service;

    @GetMapping
    public List<Cocinero> getAllCocineros() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Cocinero getCocineroById(@PathVariable  Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Cocinero saveCocinero(@RequestBody Cocinero cocinero) {
        return service.save(cocinero);
    }

    @DeleteMapping("/{id}")
    public void deleteCocineroById(@PathVariable  Long id) {
        service.deleteById(id);
    }

    @GetMapping("/{id}/recetas")
    public List<Receta> getRecetaByCocineroId(@PathVariable  Long id) {
        return service.findRecetaByCocineroId(id);
    }

}
