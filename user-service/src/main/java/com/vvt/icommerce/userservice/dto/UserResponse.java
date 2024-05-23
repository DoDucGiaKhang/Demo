package com.vvt.icommerce.userservice.dto;

import com.vvt.icommerce.userservice.model.Role;
import com.vvt.icommerce.userservice.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@Setter @Getter @NoArgsConstructor
public class UserResponse {
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private byte[] avatarData;

    private String token;

    private List<String> roles;

    public UserResponse(Long id, String username, String firstName, String lastName, byte[] avatarData, String token) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarData = avatarData;
        this.token = token;
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
    }
}
