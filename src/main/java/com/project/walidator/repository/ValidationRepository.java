package com.project.walidator.repository;

import com.project.walidator.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ValidationRepository extends JpaRepository<Validation, Integer> {
}
