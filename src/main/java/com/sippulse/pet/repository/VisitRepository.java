package com.sippulse.pet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sippulse.pet.entity.Visit;


@Repository
public interface VisitRepository extends JpaRepository<Visit, Long>{

	Page<Visit> findByVetId(Long vet_id, Pageable pageableRequest);
	
 	@Query("SELECT v FROM Visit v INNER JOIN v.pet p INNER JOIN p.owner o INNER JOIN p.petType pt WHERE o.cpf =:cpfOwner ")
	Page<Visit> findVisitByCpfOwner(String cpfOwner, Pageable pageableRequest);
	
	@Query("SELECT v FROM Visit v INNER JOIN v.pet p INNER JOIN v.vet vt INNER JOIN p.petType pt WHERE vt.firstName =:firstNameVet ")
	Page<Visit> findVisitByFirstNameVet(String firstNameVet, Pageable pageableRequest);

}
