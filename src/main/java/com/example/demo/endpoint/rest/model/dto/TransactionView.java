package com.example.demo.endpoint.rest.model.dto;

import com.example.demo.endpoint.rest.model.enums.PaymentStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionView {

    private String fullName;
    private String email;
    private Integer amount;
    private LocalDate date;
    private PaymentStatus paymentStatus;
    private String description; // uniquement pour les aides
    private boolean isDonation;
}
