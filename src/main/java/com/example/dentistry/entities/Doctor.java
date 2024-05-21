package com.example.dentistry.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@ToString
public class Doctor extends User {
    @Column(nullable = false)
    private String specialization;
}
