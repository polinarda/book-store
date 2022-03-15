package com.polina.bookstore.service;

import com.polina.bookstore.domain.User;

import java.util.Map;

public interface AuthenticationService {

    Map<String, String> login(String email, String password);

    String registerUser(User user, String password2);

}
