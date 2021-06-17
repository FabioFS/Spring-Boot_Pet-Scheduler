package com.sippulse.pet.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.sippulse.pet.converter.DozerConverter;
import com.sippulse.pet.data.vo.v1.VetVO;
import com.sippulse.pet.entity.Vet;
import com.sippulse.pet.repository.VetRepository;
import com.sippulse.pet.service.VetService;

@Service
public class VetServiceImpl implements VetService{
	
	@Autowired
	VetRepository repository;

	public VetVO create(VetVO vet) {
		Vet entity = DozerConverter.parseObject(vet, Vet.class);
		VetVO vo = DozerConverter.parseObject(repository.save(entity), VetVO.class);
		return vo;
	}

	@Override
	public VetVO update(VetVO vet) {
		Vet entity = repository.findById(vet.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
//		entity.setBirthDate(vet.getBirthDate());
		entity.setFirstName(vet.getFirstName());
		entity.setLastName(vet.getLastName());

	
		VetVO vo = DozerConverter.parseObject(repository.save(entity), VetVO.class);
		return vo;
	}

	@Override
	public VetVO findById(Long id) {
		Vet entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, VetVO.class);
	}
	

	@Override
	public Page<VetVO> findAll(Pageable pageable) {
		Page<Vet> page = repository.findAll(pageable);
		return page.map(this::convertToVetVO);
	}
	
	private VetVO convertToVetVO(Vet entity) {
		return DozerConverter.parseObject(entity, VetVO.class);
	}

	@Override
	public void delete(Long id) {
		Vet entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
		
	}

	@Override
	public Page<VetVO> findByFirstName(String firstName, Pageable pageable) {
		Page<Vet> page = repository.findVetByFirstName(firstName, pageable);
		return page.map(this::convertToVetVO);
	}




}
