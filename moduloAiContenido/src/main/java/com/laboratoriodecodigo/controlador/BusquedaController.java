package com.laboratoriodecodigo.controlador;


import com.laboratoriodecodigo.modelo.iaContenido.BusquedaPredefinida;
import com.laboratoriodecodigo.modelo.iaContenido.Etiqueta;
import com.laboratoriodecodigo.servicios.BusquedaPredefinidaService;
import com.laboratoriodecodigo.servicios.EtiquetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/busquedas") // Ruta dedicada para gestión de búsquedas
public class BusquedaController {

    private final BusquedaPredefinidaService busquedaService;
    private final EtiquetaService etiquetaService;

    @Autowired
    public BusquedaController(BusquedaPredefinidaService busquedaService, EtiquetaService etiquetaService) {
        this.busquedaService = busquedaService;
        this.etiquetaService = etiquetaService;
    }


    @PostMapping("/config")
    public ResponseEntity<?> configurarBusquedaAutomatica(@RequestBody Map<String, Object> busquedaData) {

        Long idEtiqueta = Long.valueOf(busquedaData.get("idEtiqueta").toString());
        String palabrasClave = (String) busquedaData.get("palabrasClave");

        if (palabrasClave == null || palabrasClave.trim().isEmpty()) {
            return new ResponseEntity<>("Las 'palabrasClave' son obligatorias.", HttpStatus.BAD_REQUEST);
        }

        Etiqueta etiqueta = etiquetaService.buscarPorId(idEtiqueta);
        if (etiqueta == null) {
            return new ResponseEntity<>("La Etiqueta con ID " + idEtiqueta + " no existe.", HttpStatus.BAD_REQUEST);
        }

        BusquedaPredefinida busqueda = BusquedaPredefinida.builder()
                .palabrasClave(palabrasClave)
                .limiteNoticias(busquedaData.get("limiteNoticias") != null ? (Integer) busquedaData.get("limiteNoticias") : 10)
                .frecuenciaMinutos(busquedaData.get("frecuenciaMinutos") != null ? (Integer) busquedaData.get("frecuenciaMinutos") : 60)
                .activa(busquedaData.get("activa") instanceof Boolean ? (Boolean) busquedaData.get("activa") : true)
                .etiqueta(etiqueta)
                .build();

        BusquedaPredefinida nuevaBusqueda = busquedaService.guardarBusqueda(busqueda);

        return new ResponseEntity<>(nuevaBusqueda, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<BusquedaPredefinida>> obtenerTodasLasConfiguraciones() {
        List<BusquedaPredefinida> busquedas = busquedaService.obtenerTodas();
        return new ResponseEntity<>(busquedas, HttpStatus.OK);
    }

}