package com.novelis.novy.auth;


import com.novelis.novy.Repository.AccountRepository;
import com.novelis.novy.Repository.CollaboratorRepository;
import com.novelis.novy.config.JwtService;
import com.novelis.novy.enums.Role;
import com.novelis.novy.model.Account;
import com.novelis.novy.model.Collaborator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountRepository accountRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CollaboratorRepository collaboratorRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        // Validate input data (email and password)
        String email = request.getUserName();
        String password = request.getPassword();

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            // Handle invalid input data with a custom error response
            return AuthenticationResponse.builder()
                    .error("Invalid input data")
                    .build();
        }

        // Check if an account with the same email (username) already exists
        if (accountRepository.findByUsername(email).isPresent()) {
            // Handle account already exists with a custom error response
            return AuthenticationResponse.builder()
                    .error("Account already exists")
                    .build();
        }

        // Find the collaborator by email (username)
        Collaborator collaborator = collaboratorRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Collaborator not found"));

        // Hash the password and create the Account object
        String encodedPassword = passwordEncoder.encode(password);
        Account account = Account.builder()
                .collaborator(collaborator)
                .username(email)
                .password(encodedPassword)
                .role(request.getRole())
                .build();

        // Save the account
        accountRepository.save(account);

        // Generate JWT token
        String jwtToken = jwtService.generateToken(account);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (AuthenticationException e) {
            // Handle authentication failure (e.g., invalid username or password)
            return AuthenticationResponse.builder()
                    .error("Authentication failed")
                    .build();
        }

        // If authentication is successful, retrieve the account
        var account = accountRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("email not found"));

        // Verify the provided password against the stored and encoded password
        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            // Handle incorrect password
            return AuthenticationResponse.builder()
                    .error("Incorrect password")
                    .build();
        }

        // Generate JWT token
        var jwToken = jwtService.generateToken(account);

        return AuthenticationResponse.builder().token(jwToken).build();
    }

}