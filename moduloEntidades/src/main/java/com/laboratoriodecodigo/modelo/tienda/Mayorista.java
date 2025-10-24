package com.laboratoriodecodigo.modelo.tienda;

import com.laboratoriodecodigo.modelo.finanzas.GastoTienda;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "mayorista", cascade = CascadeType.ALL)
    private List<GastoTienda> gastosAsociados = new ArrayList<>();
}
