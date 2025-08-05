package com.example.demo.endpoint.rest.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@MappedSuperclass
@Data
public abstract class AbstractTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;
}
