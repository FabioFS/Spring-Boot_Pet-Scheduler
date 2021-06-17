package com.sippulse.pet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sippulse.pet.data.vo.v1.VetVO;

public interface VetService {

	VetVO create(VetVO pet);

	VetVO update(VetVO pet);

	VetVO findById(Long id);
	
	void delete(Long id);

	Page<VetVO> findByFirstName(String firstName,Pageable pageable);

	Page<VetVO> findAll(Pageable pageable);
}
