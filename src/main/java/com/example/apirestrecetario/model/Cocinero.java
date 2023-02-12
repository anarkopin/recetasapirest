package com.example.apirestrecetario.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Cocinero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String telefono;

    @OneToMany
    @JoinColumn(name = "cocinero_id")
    private Set<Receta> recetas;


}
