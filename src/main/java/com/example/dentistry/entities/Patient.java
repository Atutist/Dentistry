package com.example.dentistry.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "patients")
@Getter
@Setter
@ToString
public class Patient extends User {
    @Column(nullable = false, unique = true)
    private String phone;

}
