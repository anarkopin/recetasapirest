package com.example.apirestrecetario.controller;

import com.example.apirestrecetario.model.Cocinero;
import com.example.apirestrecetario.model.LoginRequest;
import com.example.apirestrecetario.repository.CocineroRepository;
import com.example.apirestrecetario.security.JwtTokenProvider;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {


    private AuthenticationManager authenticationManager;

    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Autowired
    private CocineroRepository cocineroRepository;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> ingresar(@RequestParam String email, @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        Cocinero cocinero = cocineroRepository.findByEmail(email);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(cocinero);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrarCocinero(@RequestBody Cocinero cocinero) {
        // Validar si el email ya está en uso
        if (cocineroRepository.existsByEmail(cocinero.getEmail())) {
            return new ResponseEntity<>("Email ya está en uso!",
                    HttpStatus.BAD_REQUEST);
        }

        // Encriptar la contraseña
        cocinero.setPassword(passwordEncoder.encode(cocinero.getPassword()));

        // Guardar el cocinero
        Cocinero result = cocineroRepository.save(cocinero);
        return new ResponseEntity<>("Cocinero registrado exitosamente", HttpStatus.CREATED);
    }
}
