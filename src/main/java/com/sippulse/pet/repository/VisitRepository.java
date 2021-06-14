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
	
 //@Query(value="SELECT o.first_name, o.cpf, pt.description, p.name_pet, v.visit_date, v.visit_time, v.description FROM `pet-schedule`.visit v inner join `pet-schedule`.pet p on p.id=v.pet_id inner join `pet-schedule`.owner o on o.id=p.owner_id inner join `pet-schedule`.pet_type pt on pt.id=p.pet_type_id where o.cpf=:cpfOwner", nativeQuery = true)
	
//	@Query("SELECT v FROM Visit v INNER JOIN v.pet p INNER JOIN p.owner o INNER JOIN p.petType pt WHERE o.cpf =:cpfOwner ")
//	Page<Visit> findVisitByCpfOwner(String cpfOwner, Pageable pageableRequest);

}
