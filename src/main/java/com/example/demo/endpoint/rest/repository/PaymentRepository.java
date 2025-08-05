package com.example.demo.endpoint.rest.repository;

import com.example.demo.endpoint.rest.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {}
