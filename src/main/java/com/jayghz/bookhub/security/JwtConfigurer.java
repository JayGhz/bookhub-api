package com.jayghz.bookhub.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> { // Clase para integrar el filtro JWTFilter en la cadena de seguridad

    private final TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Crear una instancia del filtro JwtFilter
        JwtFilter jwtFilter = new JwtFilter(tokenProvider);

        // Agregar el filtro JWTFilter antes del filtro de autenticación de nombre de usuario y contraseña
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
