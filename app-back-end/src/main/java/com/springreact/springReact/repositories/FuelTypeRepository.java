package com.springreact.springReact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springreact.springReact.model.FuelType;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {

}
