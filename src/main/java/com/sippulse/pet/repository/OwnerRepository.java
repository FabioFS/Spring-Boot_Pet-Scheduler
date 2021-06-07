package com.sippulse.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sippulse.pet.entity.Owner;


@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long>{

}
