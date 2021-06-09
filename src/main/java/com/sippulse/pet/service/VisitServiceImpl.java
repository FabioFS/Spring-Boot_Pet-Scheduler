/**
 * 
 */
package com.sippulse.pet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sippulse.pet.data.vo.v1.VisitVO;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@Service
public class VisitServiceImpl implements VisitService {

	@Override
	public VisitVO create(VisitVO visit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VisitVO update(VisitVO visit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VisitVO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<VisitVO> findAll(Pageable pageableRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<VisitVO> findVisitByCpfOwner(String cpf, Pageable pageableRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<VisitVO> findVisitByVet(Long vet_id, Pageable pageableRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
