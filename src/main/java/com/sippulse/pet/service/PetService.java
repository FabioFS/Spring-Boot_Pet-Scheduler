package com.sippulse.pet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sippulse.pet.data.vo.v1.PetVO;

public interface PetService {

	PetVO create(PetVO pet);

	PetVO update(PetVO pet);

	PetVO findById(Long id);
	
	void delete(Long id);

	PetVO findPetByOwnerCpf(String cpf);

	Page<PetVO> findAll(Pageable pageable);
}
