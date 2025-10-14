package com.laboratoriodecodigo.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mayoristas")
@Getter // Para obtener los datos
@Setter // Para que JPA pueda inicializar los campos
@NoArgsConstructor // OBLIGATORIO para Hibernate
@AllArgsConstructor
@Builder
public class Mayorista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMayorista;
    private String nombre;
    @Column(name = "url_api_base")
    private String urlApiBase;
    @Column(name = "key_api_secret")
    private String keyApiSecret;
    @Column(name = "adapter_class_name")
    private String adapterClassName;

}
