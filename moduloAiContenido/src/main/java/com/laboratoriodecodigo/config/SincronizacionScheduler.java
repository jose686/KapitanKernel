package com.laboratoriodecodigo.config;


import com.laboratoriodecodigo.modelo.iaContenido.BusquedaPredefinida;
import com.laboratoriodecodigo.servicios.BusquedaPredefinidaService;
import com.laboratoriodecodigo.servicios.INoticiasService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SincronizacionScheduler {

    private final BusquedaPredefinidaService busquedaService;
    private final INoticiasService noticiasService;



    public SincronizacionScheduler(BusquedaPredefinidaService busquedaService, INoticiasService noticiasService) {
        this.busquedaService = busquedaService;
        this.noticiasService = noticiasService;
    }

    //@Scheduled(fixedRate = 300000)
    public void sincronizarNoticiasMasivamente() {
        System.out.println("🤖 CRON JOB: Iniciando proceso de sincronización masiva a las: " + LocalDateTime.now());
        List<BusquedaPredefinida> busquedasActivas = busquedaService.obtenerBusquedasActivas();

        // ⭐ El retraso total será: 5 segundos * número de búsquedas (aprox. 30 segundos)
        final long RETRASO_MS = 5000;

        for (BusquedaPredefinida busqueda : busquedasActivas) {

            try {

                int noticiasImportadas = noticiasService.sincronizarYGuardar(
                        busqueda.getPalabrasClave(),
                        busqueda.getLimiteNoticias(),
                        busqueda.getEtiqueta()
                );

                busquedaService.actualizarUltimaEjecucion(busqueda);
                System.out.printf("CRON JOB: Éxito en '%s'. Noticias importadas: %d\n",
                        busqueda.getPalabrasClave(), noticiasImportadas);

            } catch (Exception e) {
                System.err.printf("CRON JOB: Error al ejecutar la búsqueda '%s': %s\n",
                        busqueda.getPalabrasClave(), e.getMessage());
            }

            // ⭐ IMPLEMENTACIÓN DEL RETRASO ⭐
            try {
                System.out.println("Esperando " + (RETRASO_MS / 1000) + " segundos...");
                Thread.sleep(RETRASO_MS);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                System.err.println("Hilo de sincronización interrumpido.");
                return; // Salir del método
            }
        }
        System.out.println("🤖 CRON JOB: Finalizado.");
    }
}
