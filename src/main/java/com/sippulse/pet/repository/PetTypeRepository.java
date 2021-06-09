package com.sippulse.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sippulse.pet.entity.PetType;


@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long>{
}
