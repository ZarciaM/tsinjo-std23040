package com.example.demo.endpoint.rest.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "help")
public class Help extends AbstractTransaction {

    @Embedded
    private Beneficiary beneficiary;

    private String description; // description de l'accident couvert
}
