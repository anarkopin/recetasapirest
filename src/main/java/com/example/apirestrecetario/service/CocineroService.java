package com.example.apirestrecetario.service;

import com.example.apirestrecetario.model.Cocinero;
import com.example.apirestrecetario.model.Receta;
import com.example.apirestrecetario.repository.CocineroRepository;
import com.example.apirestrecetario.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CocineroService {

    @Autowired
    private CocineroRepository repository;

    @Autowired
    private EntityManager em;

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Cocinero> findAll() {
        return repository.findAll();
    }

    public Cocinero findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Cocinero save(Cocinero cocinero) {
        return repository.save(cocinero);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Receta> findRecetaByCocineroId(Long cocineroId) {
        Predicate[] predicatesArray;
        var predicates = new ArrayList<Predicate>();

        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(Receta.class);
        var root = cq.from(Receta.class);
        Join<Receta, Cocinero> joinCocinero = root.join("cocinero");

        predicates.add(cb.equal(joinCocinero.get("id"), cocineroId));

        predicatesArray = predicates.toArray(new Predicate[0]);
        if (!predicates.isEmpty()) {
            cq.where(predicatesArray);
        }

        var result = em.createQuery(cq);


        List<Receta> response = result.getResultList();

        return response;

    }

}
