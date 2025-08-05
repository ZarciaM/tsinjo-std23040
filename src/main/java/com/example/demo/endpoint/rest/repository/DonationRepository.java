package com.example.demo.endpoint.rest.repository;

import com.example.demo.endpoint.rest.model.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {}
