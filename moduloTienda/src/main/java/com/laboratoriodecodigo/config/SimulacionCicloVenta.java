package com.laboratoriodecodigo.config;

import com.laboratoriodecodigo.modelo.tienda.EstadoPedidoEnum;
import com.laboratoriodecodigo.modelo.tienda.Mayorista;
import com.laboratoriodecodigo.modelo.tienda.Pedido;
import com.laboratoriodecodigo.modelo.tienda.Producto;
import com.laboratoriodecodigo.servicios.IMayoristaService;
import com.laboratoriodecodigo.servicios.IPedidoService;
import com.laboratoriodecodigo.servicios.IProductoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional; // Necesario para Optional
import java.math.BigDecimal;

@Configuration
public class SimulacionCicloVenta {

    // Inyección de dependencias
    private final IMayoristaService mayoristaService;
    private final IProductoService productoService;
    private final IPedidoService pedidoService;

    public SimulacionCicloVenta(IMayoristaService mayoristaService,
                                IProductoService productoService,
                                IPedidoService pedidoService) {
        this.mayoristaService = mayoristaService;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
    }

    /**
     * Función auxiliar que busca un producto por SKU, y si no existe, lo guarda.
     * @param producto El objeto Producto con los datos a guardar.
     * @return El objeto Producto ya persistido o encontrado en la base de datos.
     */
    private Producto buscarOInsertar(Producto producto) {
        Optional<Producto> existenteOpt = productoService.buscarPorSku(producto.getSkuMayoristaUnique());

        if (existenteOpt.isPresent()) {
            // Ya existe, usamos la versión persistida.
            System.out.println("   [~] Producto ya existe (SKU: " + producto.getSkuMayoristaUnique() + "). Usando datos existentes.");
            return existenteOpt.get();
        } else {
            // Es nuevo, lo guardamos.
            Producto guardado = productoService.guardar(producto);
            System.out.println("   [+] Producto insertado: " + guardado.getNombreProducto());
            return guardado;
        }
    }

    @Bean
    public CommandLineRunner correrSimulacion() {
        return args -> {
            System.out.println("\n=============================================");
            System.out.println("🚀 INICIANDO SIMULACIÓN DE DATOS Y VENTA 🚀");
            System.out.println("=============================================");

            // --- 1. CREACIÓN DEL MAYORISTA Y PRODUCTOS (INSERCIÓN DIRECTA) ---

            // A. Insertar Mayorista
            System.out.println("1. INSERTANDO DATOS BÁSICOS EN DB...");
            Mayorista mayorista = new Mayorista();
            mayorista.setNombre("Componentes Globales S.L.");
            mayorista.setUrlApiBase("http://simulacion.api");

            // Nota: Podrías necesitar una lógica de buscarOInsertar para Mayorista también si su nombre es Unique.
            Mayorista mGuardado = mayoristaService.guardar(mayorista);
            Long mayoristaId = mGuardado.getIdMayorista();
            System.out.println("-> Mayorista ID: " + mayoristaId);

            // B. Insertar Productos (Aplicando la nueva lógica de buscar O insertar)

            Producto p1 = new Producto();
            p1.setIdMayoristaFk(mayoristaId);
            p1.setNombreProducto("Tarjeta Gráfica RTX 5060 12GB");
            p1.setPrecioCostoDecimal(new BigDecimal("350.50"));
            p1.setPrecioPvpSugerido(new BigDecimal("499.99"));
            p1.setSkuMayoristaUnique("CG-RTX5060-12G");
            p1.setStockInteger(45);
            p1.setUrlImagenPrincipal("/img/rtx5060.jpg");
            Producto p1Guardado = buscarOInsertar(p1);

            Producto p2 = new Producto();
            p2.setIdMayoristaFk(mayoristaId);
            p2.setNombreProducto("Placa Base X670E Chipset");
            p2.setPrecioCostoDecimal(new BigDecimal("185.00"));
            p2.setPrecioPvpSugerido(new BigDecimal("259.90"));
            p2.setSkuMayoristaUnique("CG-X670E-CHIP");
            p2.setStockInteger(28);
            p2.setUrlImagenPrincipal("/img/x670e.jpg");
            Producto p2Guardado = buscarOInsertar(p2);


            // Producto 3: Procesador (CPU)
            Producto p3 = new Producto();
            p3.setIdMayoristaFk(mayoristaId);
            p3.setNombreProducto("Procesador Intel i7-14700K");
            p3.setPrecioCostoDecimal(new BigDecimal("320.00"));
            p3.setPrecioPvpSugerido(new BigDecimal("410.50"));
            p3.setSkuMayoristaUnique("CG-I7-14700K");
            p3.setStockInteger(55);
            p3.setUrlImagenPrincipal("/img/i7-14700k.jpg");
            Producto p3Guardado = buscarOInsertar(p3);

            // Producto 4: Memoria RAM
            Producto p4 = new Producto();
            p4.setIdMayoristaFk(mayoristaId);
            p4.setNombreProducto("Memoria RAM 32GB DDR5 6000MHz");
            p4.setPrecioCostoDecimal(new BigDecimal("105.90"));
            p4.setPrecioPvpSugerido(new BigDecimal("149.95"));
            p4.setSkuMayoristaUnique("CG-RAM-DDR5-32");
            p4.setStockInteger(120);
            p4.setUrlImagenPrincipal("/img/ram-32gb.jpg");
            Producto p4Guardado = buscarOInsertar(p4);

            // Producto 5: Almacenamiento SSD
            Producto p5 = new Producto();
            p5.setIdMayoristaFk(mayoristaId);
            p5.setNombreProducto("SSD NVMe 2TB Gen4");
            p5.setPrecioCostoDecimal(new BigDecimal("115.00"));
            p5.setPrecioPvpSugerido(new BigDecimal("165.99"));
            p5.setSkuMayoristaUnique("CG-SSD-2TB-NVME");
            p5.setStockInteger(90);
            p5.setUrlImagenPrincipal("/img/ssd-2tb.jpg");
            Producto p5Guardado = buscarOInsertar(p5);

            // Producto 6: Fuente de Poder
            Producto p6 = new Producto();
            p6.setIdMayoristaFk(mayoristaId);
            p6.setNombreProducto("Fuente Poder Modular 1000W 80+ Gold");
            p6.setPrecioCostoDecimal(new BigDecimal("140.00"));
            p6.setPrecioPvpSugerido(new BigDecimal("189.90"));
            p6.setSkuMayoristaUnique("CG-PSU-1000W");
            p6.setStockInteger(35);
            p6.setUrlImagenPrincipal("/img/psu-1000w.jpg");
            Producto p6Guardado = buscarOInsertar(p6);

            // Producto 7: Enfriamiento Líquido
            Producto p7 = new Producto();
            p7.setIdMayoristaFk(mayoristaId);
            p7.setNombreProducto("Refrigeración Líquida 360mm ARGB");
            p7.setPrecioCostoDecimal(new BigDecimal("85.00"));
            p7.setPrecioPvpSugerido(new BigDecimal("119.99"));
            p7.setSkuMayoristaUnique("CG-AIO-360MM");
            p7.setStockInteger(60);
            p7.setUrlImagenPrincipal("/img/aio-360mm.jpg");
            Producto p7Guardado = buscarOInsertar(p7);


            System.out.println("-> Catálogo inicial procesado. 7 Productos.");

            // --- 2. SIMULANDO CICLO DE VENTA ---

            System.out.println("\n2. SIMULANDO CICLO DE VENTA...");
            Long idUsuarioSimulado = 1L;
            Long idDireccionEnvioSimulada = 10L;
            List<Long> productosEnCarrito = List.of(
                    p1Guardado.getId(), // Tarjeta Gráfica
                    p2Guardado.getId(), // Placa Base
                    p3Guardado.getId(), // Procesador
                    p4Guardado.getId(), // RAM
                    p5Guardado.getId(), // SSD
                    p6Guardado.getId(), // Fuente de Poder
                    p7Guardado.getId()  // Enfriamiento
            );

            // Lógica de Pedido
            Pedido pedidoInicial = pedidoService.iniciarPedido(
                    idUsuarioSimulado,
                    idDireccionEnvioSimulada,
                    productosEnCarrito
            );
            Long idPedido = pedidoInicial.getIdPedido();

            System.out.println("-> PEDIDO #" + idPedido + " creado. Total: " + pedidoInicial.getTotalDecimal());

            // D. Confirmar Pago y Envío
            String transaccionId = "TXN-" + System.currentTimeMillis();
            pedidoService.confirmarPago(idPedido, transaccionId);
            pedidoService.actualizarEstado(idPedido, EstadoPedidoEnum.ENVIADO);

            System.out.println("\n=============================================");
            System.out.println("✅ SIMULACIÓN DE DATOS COMPLETADA. VERIFICAR EN MYSQL.");
            System.out.println("Último estado del Pedido #" + idPedido + ": " +
                    pedidoService.buscarPorId(idPedido).orElseThrow().getEstado());
            System.out.println("=============================================");
        };
    }

}