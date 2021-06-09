package com.sippulse.pet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sippulse.pet.entity.Owner;


@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long>{
	
	@Query("SELECT DISTINCT o FROM Owner o WHERE o.cpf =:cpf")
	@Transactional(readOnly = true)
	Owner findOwnerByCpf(@Param("cpf") String cpf);


}
