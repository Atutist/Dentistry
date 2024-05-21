package com.example.dentistry.dto;

import com.example.dentistry.entities.User;
import com.example.dentistry.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    int id;
    String name;
    Role role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getUsername();
        this.role = user.getRole();
    }
}
