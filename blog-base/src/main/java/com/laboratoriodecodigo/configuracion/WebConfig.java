package com.laboratoriodecodigo.configuracion;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // ⬅️ Indica a Spring que es una clase de configuración
public class WebConfig implements WebMvcConfigurer {

    // Inyecta el valor de la ruta de uploads desde application.properties
    // Usamos 'uploads' como valor por defecto si no está definido.
    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        registry.addResourceHandler("/api/imagenes/**")
                // file: indica que se debe buscar en el sistema de archivos (ruta absoluta)
                .addResourceLocations("C:\\Users\\pepe6\\Documents\\Programacion\\java\\KapitanKernel\\KapitanKernel\\uploads");


        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/templates/");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500", "*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}