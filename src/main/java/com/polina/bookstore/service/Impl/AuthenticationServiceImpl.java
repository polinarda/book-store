package com.polina.bookstore.service.Impl;

import com.polina.bookstore.domain.User;
import com.polina.bookstore.exception.ApiRequestException;
import com.polina.bookstore.exception.EmailException;
import com.polina.bookstore.exception.PasswordException;
import com.polina.bookstore.repository.UserRepository;
import com.polina.bookstore.security.JwtProvider;
import com.polina.bookstore.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Map<String, String> login(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            User user = userRepository.findByEmail(email);
            String userRole = user.getRoles().iterator().next().name();
            String token = jwtProvider.createToken(email, userRole);
            Map<String, String> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);
            response.put("userRole", userRole);
            return response;
        } catch (AuthenticationException e) {
            throw new ApiRequestException("Incorrect password or email", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public String registerUser(User user, String password2) {
        if (user.getPassword() != null && !user.getPassword().equals(password2)) {
            throw new PasswordException("Passwords do not match.");
        }
        User userFromDb = userRepository.findByEmail(user.getEmail());

        if (userFromDb != null) {
            throw new EmailException("Email is already used.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User successfully registered.";
    }
}
