package com.springreact.springReact.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springreact.springReact.model.EngineType;

@Repository
public interface EngineTypeRepository extends JpaRepository<EngineType, Long> {

}
