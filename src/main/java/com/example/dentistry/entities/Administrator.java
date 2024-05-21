package com.example.dentistry.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "administrators")
@Getter
@Setter
@ToString
public class Administrator extends User {
    @Column(nullable = false, unique = true)
    private String phone;
}
