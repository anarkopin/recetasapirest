package com.example.apirestrecetario.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "receta_ingrediente",
            joinColumns = @JoinColumn(name = "receta_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private Set<Ingrediente> ingredientes = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cocinero_id")
    private Cocinero cocinero;

    private Integer dificultad;



}
