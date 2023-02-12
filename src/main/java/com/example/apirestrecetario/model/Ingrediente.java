package com.example.apirestrecetario.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String cantidad;

    private String unidad;

    @ManyToMany(mappedBy = "ingredientes")
    @JsonBackReference
    private Set<Receta> recetas = new HashSet<>();





}
