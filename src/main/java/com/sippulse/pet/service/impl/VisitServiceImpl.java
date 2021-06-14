/**
 * 
 */
package com.sippulse.pet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.sippulse.pet.converter.DozerConverter;
import com.sippulse.pet.data.vo.v1.VisitVO;
import com.sippulse.pet.entity.Visit;
import com.sippulse.pet.repository.VisitRepository;
import com.sippulse.pet.service.VisitService;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@Service
public class VisitServiceImpl implements VisitService {
	
	@Autowired
	VisitRepository repository;

	@Override
	public VisitVO create(VisitVO visit) {
		Visit entity = DozerConverter.parseObject(visit, Visit.class);
		VisitVO vo = DozerConverter.parseObject(repository.save(entity), VisitVO.class);
		return vo;
	}

	@Override
	public VisitVO update(VisitVO visit) {
		Visit entity = repository.findById(visit.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setDescription(visit.getDescription());
		entity.setPet(visit.getPet());
		entity.setVet(visit.getVet());
//		entity.setVisitDate(visit.getVisitDate());
//		entity.setVisitTime(visit.getVisitTime());
	
		VisitVO vo = DozerConverter.parseObject(repository.save(entity), VisitVO.class);
		return vo;
	}

	@Override
	public VisitVO findById(Long id) {
		Visit entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, VisitVO.class);
	}

	@Override
	public void delete(Long id) {
		Visit entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
		
	}

	@Override
	public Page<VisitVO> findAll(Pageable pageableRequest) {
		Page<Visit> page = repository.findAll(pageableRequest);
		return page.map(this::convertToVisitVO);
	}	
	
	private VisitVO convertToVisitVO(Visit entity) {
		return DozerConverter.parseObject(entity, VisitVO.class);
	}

	@Override
	public Page<VisitVO> findVisitByCpfOwner(String cpfOwner, Pageable pageableRequest) {
		//Page<Visit> page = repository.findVisitByCpfOwner(cpfOwner, pageableRequest);
	//	return page.map(this::convertToVisitVO);
		return null;
	}
	
	@Override
	public Page<VisitVO> findByVetId(Long vet_id, Pageable pageableRequest) {
		Page<Visit> page = repository.findByVetId(vet_id, pageableRequest);
		return page.map(this::convertToVisitVO);
	}

}
