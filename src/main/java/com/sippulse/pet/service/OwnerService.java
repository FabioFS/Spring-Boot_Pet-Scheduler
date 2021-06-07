/**
 * 
 */
package com.sippulse.pet.service;

import com.sippulse.pet.data.vo.v1.OwnerVO;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
public interface OwnerService {

	OwnerVO create(OwnerVO owner);

	OwnerVO update(OwnerVO owner);

	OwnerVO findById(Long id);

	void delete(Long id);

	OwnerVO findByCpf(String cpf);

	OwnerVO disableOwner(Long id);


	
	

}
