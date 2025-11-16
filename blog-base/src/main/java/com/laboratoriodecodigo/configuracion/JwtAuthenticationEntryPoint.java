package com.laboratoriodecodigo.configuracion;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
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
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String message = authException.getMessage() != null ? authException.getMessage() : "Se requiere autenticación. Token inválido o no proporcionado.";
        String jsonError = String.format("{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"%s\"}", message);

        response.getWriter().write(jsonError);
        response.getWriter().flush();
        // 2. Especificar que la respuesta es JSON (opcional, pero buena práctica)
       //response.setContentType("application/json");
       // response.sendRedirect(request.getContextPath() + "/login");
        // 3. Escribir un mensaje de error claro en JSON (si lo deseas)
        //response.getWriter().write("{\"error\": \"Acceso Denegado\", \"message\": \"Se requiere un token JWT válido para acceder a este recurso.\"}");
    }
}