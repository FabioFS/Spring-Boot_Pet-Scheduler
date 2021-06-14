package com.sippulse.pet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.sippulse.pet.converter.DozerConverter;
import com.sippulse.pet.data.vo.v1.PetVO;
import com.sippulse.pet.entity.Pet;
import com.sippulse.pet.repository.PetRepository;
import com.sippulse.pet.service.PetService;

@Service
public class PetServiceImpl implements PetService{
	
	@Autowired
	PetRepository repository;

	public PetVO create(PetVO pet) {
		Pet entity = DozerConverter.parseObject(pet, Pet.class);
		PetVO vo = DozerConverter.parseObject(repository.save(entity), PetVO.class);
		return vo;
	}

	@Override
	public PetVO update(PetVO pet) {
		Pet entity = repository.findById(pet.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
//		entity.setBirthDate(pet.getBirthDate());
		entity.setNamePet(pet.getNamePet());
		entity.setDescription(pet.getDescription());
		entity.setPetType(pet.getPetType());
		entity.setOwner(pet.getOwner());
	
		PetVO vo = DozerConverter.parseObject(repository.save(entity), PetVO.class);
		return vo;
	}

	@Override
	public PetVO findById(Long id) {
		Pet entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, PetVO.class);
	}
	

	@Override
	public Page<PetVO> findAll(Pageable pageable) {
		Page<Pet> page = repository.findAll(pageable);
		return page.map(this::convertToPetVO);
	}
	
	private PetVO convertToPetVO(Pet entity) {
		return DozerConverter.parseObject(entity, PetVO.class);
	}

	@Override
	public void delete(Long id) {
		Pet entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
		
	}

	@Override
	public PetVO findPetByOwnerCpf(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
