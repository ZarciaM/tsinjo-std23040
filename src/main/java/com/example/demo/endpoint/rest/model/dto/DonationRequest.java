package com.example.demo.endpoint.rest.model.dto;

import lombok.Data;

@Data
public class DonationRequest {

    private String fullName;
    private String email;
    private Integer amount;
    private String pspPaymentId;
}


