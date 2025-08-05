package com.example.demo.endpoint.rest.model.entity;

import com.example.demo.endpoint.rest.model.enums.PaymentStatus;
import jakarta.persistence.*;
import java.time.Instant;
import lombok.Data;

@Entity
@Data
@Table(name = "payment")
public class Payment {

  @Id private String id; // correspond au pspPaymentId

  @Enumerated(EnumType.STRING)
  private PaymentStatus verificationStatus;

  private Integer amount;

  private String pspType; // ex: ORANGE_MONEY

  private String pspPaymentId;

  private Instant creationInstant;

  private Instant lastPspVerificationInstant;

  private int verificationAttemptNb;

  private String payerEmail;
}
