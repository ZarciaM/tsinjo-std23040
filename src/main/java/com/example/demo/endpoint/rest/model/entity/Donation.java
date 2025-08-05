package com.example.demo.endpoint.rest.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "donation")
public class Donation extends AbstractTransaction {

  @Embedded private Donor donor;
}
