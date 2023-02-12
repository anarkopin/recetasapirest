package com.example.apirestrecetario.service;

import com.example.apirestrecetario.model.Cocinero;
import com.example.apirestrecetario.repository.CocineroRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CocineroDetailsService implements UserDetailsService {

    private final CocineroRepository cocineroRepository;

    public CocineroDetailsService(CocineroRepository cocineroRepository) {
        this.cocineroRepository = cocineroRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cocinero cocinero = cocineroRepository.findByEmail(email);
        if (cocinero == null) {
            throw new UsernameNotFoundException("No se encontró el cocinero con el correo " + email);
        }

        return User.builder()
                .username(cocinero.getEmail())
                .password(cocinero.getPassword())
                .roles("COCINERO")
                .build();
    }
}