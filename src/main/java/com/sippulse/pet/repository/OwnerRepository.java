package com.sippulse.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sippulse.pet.entity.Pet;

@org.springframework.stereotype.Repository
public interface OwnerRepository extends JpaRepository<Pet, Long>{
}
