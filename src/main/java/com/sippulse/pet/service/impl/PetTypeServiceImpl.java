/**
 * 
 */
package com.sippulse.pet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.sippulse.pet.converter.DozerConverter;
import com.sippulse.pet.data.vo.v1.PetTypeVO;
import com.sippulse.pet.entity.PetType;
import com.sippulse.pet.repository.PetTypeRepository;
import com.sippulse.pet.service.PetTypeService;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@Service
public class PetTypeServiceImpl implements PetTypeService {
	
	@Autowired
	PetTypeRepository repository;

	@Override
	public PetTypeVO create(PetTypeVO petType) {
		PetType entity = DozerConverter.parseObject(petType, PetType.class);
		PetTypeVO vo = DozerConverter.parseObject(repository.save(entity), PetTypeVO.class);
		return vo;
	}

	@Override
	public PetTypeVO update(PetTypeVO petType) {
		PetType entity = repository.findById(petType.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setDescription(petType.getDescription());
			
		PetTypeVO vo = DozerConverter.parseObject(repository.save(entity), PetTypeVO.class);
		return vo;
	}

	@Override
	public PetTypeVO findById(Long id) {
		PetType entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, PetTypeVO.class);
	}

	@Override
	public void delete(Long id) {
		PetType entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
		
	}

}
