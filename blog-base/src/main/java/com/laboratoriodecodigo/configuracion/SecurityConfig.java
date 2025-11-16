package com.laboratoriodecodigo.configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order; // Necesitas importar Order
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; // Necesitas importar SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final String jwtSecret;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    public SecurityConfig(
            @Value("${app.jwtSecret}") String jwtSecret,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            JwtAuthenticationEntryPoint unauthorizedHandler
    ) {
        this.jwtSecret = jwtSecret;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.unauthorizedHandler = unauthorizedHandler;
    }


    @Bean
    @Order(1) // Asegura que esta cadena se evalúe antes que la web.
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**") // ⭐ CLAVE: Aplica SOLAMENTE a rutas que empiezan con /api/ ⭐
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin sesiones HTTP
                .authorizeHttpRequests(auth -> auth
                        // Permite el login/registro de la API sin JWT
                        .requestMatchers("/api/auth/**").permitAll()
                        // Cualquier otra ruta API requiere autenticación JWT
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptions -> exceptions
                        // Al fallar la autenticación de la API, devuelve JSON 401
                        .authenticationEntryPoint(unauthorizedHandler)
                )
                // Filtro JWT
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas de la web
                        .requestMatchers("/", "/blog/**", "/css/**", "/js/**", "/images/**","/uploads/**").permitAll()
                        // Rutas protegidas que requieren ROLE_ADMIN
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Cualquier otra ruta requiere autenticación de sesión
                        .anyRequest().authenticated()
                )
                // ⭐ CLAVE: Redirección al Login HTML ⭐
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/admin", true)
                        .permitAll()
                )
                // Otras configuraciones web
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessUrl("/")
                )
                // Aquí puedes dejar el unauthorizedHandler solo para el 403 o quitarlo
                // ya que formLogin maneja los 401 con redirección por defecto.
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/error/403")
                );

        // ¡IMPORTANTE! Removemos el jwtAuthenticationFilter de esta cadena,
        // ya que la autenticación se maneja por sesión/cookie.

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}