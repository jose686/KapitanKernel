package com.laboratoriodecodigo.modelo;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter // Para obtener los datos
@Setter // Para que JPA pueda inicializar los campos
@NoArgsConstructor // OBLIGATORIO para Hibernate
@AllArgsConstructor
@Builder
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategoria;
    private String nombreCategoria;

    @ManyToMany(mappedBy = "categorias")
    private Set<Posts> posts;


}
