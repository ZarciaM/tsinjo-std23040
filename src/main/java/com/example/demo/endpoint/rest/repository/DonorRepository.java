package com.example.demo.endpoint.rest.repository;

import com.example.demo.endpoint.rest.model.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Long> {
}
