package com.example.demo.endpoint.rest.model.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

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
