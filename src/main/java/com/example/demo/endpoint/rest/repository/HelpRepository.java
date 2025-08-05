package com.example.demo.endpoint.rest.repository;

import com.example.demo.endpoint.rest.model.entity.Help;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpRepository extends JpaRepository<Help, Long> {
}
