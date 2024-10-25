package com.jayghz.bookhub.security;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jayghz.bookhub.model.entity.User;
import com.jayghz.bookhub.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado con el correo electrónico: " + email));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getName().name());

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(authority),
                user
        );
    }
}
