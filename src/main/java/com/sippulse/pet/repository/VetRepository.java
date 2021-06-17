package com.sippulse.pet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sippulse.pet.entity.Vet;


@Repository
public interface VetRepository extends JpaRepository<Vet, Long>{

	Page<Vet> findVetByFirstName(String firstName, Pageable pageable);

}
