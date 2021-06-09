/**
 * 
 */
package com.sippulse.pet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sippulse.pet.converter.DozerConverter;
import com.sippulse.pet.data.vo.v1.OwnerVO;
import com.sippulse.pet.entity.Owner;
import com.sippulse.pet.repository.OwnerRepository;
import com.sippulse.pet.service.OwnerService;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@Service
public class OwnerServiceImpl implements OwnerService {
	
	@Autowired
	OwnerRepository repository;

	@Override
	public OwnerVO create(OwnerVO owner) {
		Owner entity = DozerConverter.parseObject(owner, Owner.class);
		OwnerVO vo = DozerConverter.parseObject(repository.save(entity), OwnerVO.class);
		return vo;
	}

	@Override
	public OwnerVO update(OwnerVO owner) {
		Owner entity = repository.findById(owner.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFirstName(owner.getFirstName());
		entity.setLastName(owner.getLastName());
		entity.setAddress(owner.getAddress());
		entity.setCellPhone(owner.getCellPhone());
		entity.setCpf(owner.getCpf());
	
		OwnerVO vo = DozerConverter.parseObject(repository.save(entity), OwnerVO.class);
		return vo;
	}

	@Override
	public Page<OwnerVO> findAll(Pageable pageable) {
		Page<Owner> page = repository.findAll(pageable);
		return page.map(this::convertToOwnerVO);
	}	
	
	private OwnerVO convertToOwnerVO(Owner entity) {
		return DozerConverter.parseObject(entity, OwnerVO.class);
	}
	
	@Override
	public OwnerVO findById(Long id) {
		Owner entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, OwnerVO.class);
	}

	@Override
	public void delete(Long id) {
		Owner entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
		
	}

	@Override
	public OwnerVO findOwnerByCpf(String cpf) {
		Owner entity = repository.findOwnerByCpf(cpf);
		if (entity != null) {
			return DozerConverter.parseObject(entity, OwnerVO.class);
		} else {
			throw new UsernameNotFoundException("Owner not found");
		}
	}
}
