package com.example.demo.endpoint.rest.repository;

import com.example.demo.endpoint.rest.model.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {}
