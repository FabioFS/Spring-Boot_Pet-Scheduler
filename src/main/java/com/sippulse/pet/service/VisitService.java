/**
 * 
 */
package com.sippulse.pet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sippulse.pet.data.vo.v1.VisitVO;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
public interface VisitService {

	VisitVO create(VisitVO visit);

	VisitVO update(VisitVO visit);

	VisitVO findById(Long id);

	void delete(Long id);
	
	Page<VisitVO> findAll(Pageable pageableRequest);
	
	Page<VisitVO> findVisitByCpfOwner(String cpf, Pageable pageableRequest);
	
	Page<VisitVO> findVisitByVet(Long vet_id, Pageable pageableRequest);

	
}
