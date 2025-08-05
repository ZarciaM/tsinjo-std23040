package com.example.demo.endpoint.rest.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Donor {
  private String fullName;
  private String email;
}
