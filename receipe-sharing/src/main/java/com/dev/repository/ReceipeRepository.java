package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.model.Receipe;

public interface ReceipeRepository extends JpaRepository<Receipe, Long>{

}
