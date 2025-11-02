package com.laboratoriodecodigo.config;


import com.laboratoriodecodigo.modelo.iaContenido.BusquedaPredefinida;
import com.laboratoriodecodigo.modelo.iaContenido.Etiqueta;
import com.laboratoriodecodigo.servicios.BusquedaPredefinidaService;
import com.laboratoriodecodigo.servicios.EtiquetaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final EtiquetaService etiquetaService;
    private final BusquedaPredefinidaService busquedaService;

    // Inyección de dependencias
    public DataLoader(EtiquetaService etiquetaService, BusquedaPredefinidaService busquedaService) {
        this.etiquetaService = etiquetaService;
        this.busquedaService = busquedaService;
    }


    @Override
    public void run(String... args) throws Exception {
        // Ejecutamos la creación de datos solo si no hay etiquetas existentes
        if (etiquetaService.obtenerTodas().isEmpty()) {
            System.out.println("Cargando datos iniciales: Etiquetas y Búsquedas predefinidas...");

            Etiqueta tecnologia = Etiqueta.builder()
                    .nombre("Tecnología")
                    .slug("tecnologia")
                    .build();

            Etiqueta negocios = Etiqueta.builder()
                    .nombre("Negocios")
                    .slug("negocios")
                    .build();

            tecnologia = etiquetaService.guardarEtiqueta(tecnologia);
            negocios = etiquetaService.guardarEtiqueta(negocios);


            BusquedaPredefinida busquedaTecnologia = BusquedaPredefinida.builder()
                    .palabrasClave("Inteligencia Artificial OR IA")
                    .limiteNoticias(50)
                    .frecuenciaMinutos(30) // Ejecutar cada 30 minutos
                    .activa(true)
                    .etiqueta(tecnologia) // Asignada a la etiqueta 'Tecnología'
                    .build();


            BusquedaPredefinida busquedaNegocios = BusquedaPredefinida.builder()
                    .palabrasClave("Bolsa OR Finanzas OR Mercados")
                    .limiteNoticias(20)
                    .frecuenciaMinutos(60) // Ejecutar cada 60 minutos
                    .activa(true)
                    .etiqueta(negocios) // Asignada a la etiqueta 'Negocios'
                    .build();

            busquedaService.guardarBusqueda(busquedaTecnologia);
            busquedaService.guardarBusqueda(busquedaNegocios);

            System.out.println("Datos iniciales cargados con éxito.");
        } else {
            System.out.println("La base de datos ya contiene etiquetas. Omitiendo carga de datos iniciales.");
        }
    }
}