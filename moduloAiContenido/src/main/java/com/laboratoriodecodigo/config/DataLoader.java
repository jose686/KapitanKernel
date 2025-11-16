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

            Etiqueta deportes = Etiqueta.builder()
                    .nombre("Deportes")
                    .slug("deportes")
                    .build();

            Etiqueta clima = Etiqueta.builder()
                    .nombre("Clima")
                    .slug("clima")
                    .build();

            Etiqueta programacion = Etiqueta.builder()
                    .nombre("Programación")
                    .slug("programacion")
                    .build();

            Etiqueta economia = Etiqueta.builder()
                    .nombre("Economía")
                    .slug("economia")
                    .build();

            Etiqueta politica = Etiqueta.builder()
                    .nombre("Política")
                    .slug("politica")
                    .build();

            Etiqueta general = Etiqueta.builder()
                    .nombre("General")
                    .slug("general")
                    .build();


            // Guardar todas las etiquetas en la base de datos
            tecnologia = etiquetaService.guardarEtiqueta(tecnologia);
            negocios = etiquetaService.guardarEtiqueta(negocios);
            deportes = etiquetaService.guardarEtiqueta(deportes);
            clima = etiquetaService.guardarEtiqueta(clima);
            programacion = etiquetaService.guardarEtiqueta(programacion);
            economia = etiquetaService.guardarEtiqueta(economia);
            politica = etiquetaService.guardarEtiqueta(politica);
            general = etiquetaService.guardarEtiqueta(general);

            System.out.println("Etiquetas predefinidas cargadas con éxito.");

            //tecnologia = etiquetaService.guardarEtiqueta(tecnologia);
            //negocios = etiquetaService.guardarEtiqueta(negocios);


            // Código que ya tenías:
        /*
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
        */

// ⭐ CÓDIGO A AÑADIR ⭐

// 1. Deportes
            BusquedaPredefinida busquedaDeportes = BusquedaPredefinida.builder()
                    .palabrasClave("Deporte")
                    .limiteNoticias(40)
                    .frecuenciaMinutos(45) // Ejecutar cada 45 minutos
                    .activa(true)
                    .etiqueta(deportes) // Asignada a la etiqueta 'Deportes'
                    .build();

// 2. Clima / Medio Ambiente
            BusquedaPredefinida busquedaClima = BusquedaPredefinida.builder()
                    .palabrasClave("Clima OR Meteorologia OR Inundaciones OR Sequia OR Temperatura")
                    .limiteNoticias(15)
                    .frecuenciaMinutos(120) // Ejecutar cada 2 horas
                    .activa(true)
                    .etiqueta(clima) // Asignada a la etiqueta 'Clima'
                    .build();

// 3. Programación / Desarrollo
            BusquedaPredefinida busquedaProgramacion = BusquedaPredefinida.builder()
                    .palabrasClave("Programacion")
                    .limiteNoticias(30)
                    .frecuenciaMinutos(60) // Ejecutar cada 60 minutos
                    .activa(true)
                    .etiqueta(programacion) // Asignada a la etiqueta 'Programación'
                    .build();

// 4. Economía (para diferenciar ligeramente de 'Negocios')
            BusquedaPredefinida busquedaEconomia = BusquedaPredefinida.builder()
                    .palabrasClave("Economia")
                    .limiteNoticias(25)
                    .frecuenciaMinutos(90) // Ejecutar cada 90 minutos
                    .activa(true)
                    .etiqueta(economia) // Asignada a la etiqueta 'Economía'
                    .build();

// 5. Política
            BusquedaPredefinida busquedaPolitica = BusquedaPredefinida.builder()
                    .palabrasClave("Elecciones OR Gobierno OR Congreso OR Presidente")
                    .limiteNoticias(50)
                    .frecuenciaMinutos(60) // Ejecutar cada 30 minutos (Alta frecuencia)
                    .activa(true)
                    .etiqueta(politica) // Asignada a la etiqueta 'Política'
                    .build();

// 6. General
            BusquedaPredefinida busquedaGeneral = BusquedaPredefinida.builder()
                    .palabrasClave("Actualidad OR Sucesos OR Viral OR Tendencias OR Sociedad")
                    .limiteNoticias(60)
                    .frecuenciaMinutos(180)
                    .activa(true)
                    .etiqueta(general)
                    .build();



            busquedaService.guardarBusqueda(busquedaDeportes);
            busquedaService.guardarBusqueda(busquedaClima);
            busquedaService.guardarBusqueda(busquedaProgramacion);
            busquedaService.guardarBusqueda(busquedaEconomia);
            busquedaService.guardarBusqueda(busquedaPolitica);
            busquedaService.guardarBusqueda(busquedaGeneral);

            System.out.println("Búsquedas predefinidas cargadas con éxito.");



            System.out.println("Datos iniciales cargados con éxito.");
        } else {
            System.out.println("La base de datos ya contiene etiquetas. Omitiendo carga de datos iniciales.");
        }
    }
}