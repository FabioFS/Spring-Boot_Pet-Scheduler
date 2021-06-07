/**
 * 
 */
package com.sippulse.pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.sippulse.pet.converter.DozerConverter;
import com.sippulse.pet.data.vo.v1.OwnerVO;
import com.sippulse.pet.entity.Owner;
import com.sippulse.pet.repository.OwnerRepository;

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
	public OwnerVO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OwnerVO findByCpf(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OwnerVO disableOwner(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
