package com.laboratoriodecodigo.configuracion;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        // 1. Establecer el código de estado 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // 2. Especificar que la respuesta es JSON (opcional, pero buena práctica)
        response.setContentType("application/json");

        // 3. Escribir un mensaje de error claro en JSON (si lo deseas)
        response.getWriter().write("{\"error\": \"Acceso Denegado\", \"message\": \"Se requiere un token JWT válido para acceder a este recurso.\"}");
    }
}