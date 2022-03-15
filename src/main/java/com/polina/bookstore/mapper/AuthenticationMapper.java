package com.polina.bookstore.mapper;

import com.polina.bookstore.dto.RegistrationRequest;
import com.polina.bookstore.dto.auth.AuthenticationRequest;
import com.polina.bookstore.dto.auth.AuthenticationResponse;
import com.polina.bookstore.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public AuthenticationResponse login(AuthenticationRequest request) {
        Map<String, String> credentials = authenticationService.login(request.getEmail(), request.getPassword());
        AuthenticationResponse response = new AuthenticationResponse();
        response.setEmail(credentials.get("email"));
        response.setToken(credentials.get("token"));
        response.setUserRole(credentials.get("userRole"));
        return response;
    }


    public String registerUser( RegistrationRequest registrationRequest) {
        return authenticationService.registerUser(userMapper.convertToEntity(registrationRequest), registrationRequest.getPassword2());
    }
}
