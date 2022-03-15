package com.polina.bookstore.dto.user;

import com.polina.bookstore.domain.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class UserRequest {

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    private String email;
    private String city;
    private String address;
    private String phoneNumber;
    private String postIndex;
    private Set<Role> roles;
}
