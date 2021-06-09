package com.sippulse.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sippulse.pet.entity.Visit;


@Repository
public interface VisitRepository extends JpaRepository<Visit, Long>{

}
