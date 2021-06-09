/**
 * 
 */
package com.sippulse.pet.service;

import com.sippulse.pet.data.vo.v1.PetTypeVO;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
public interface PetTypeService {

	PetTypeVO create(PetTypeVO pet);

	PetTypeVO update(PetTypeVO pet);

	PetTypeVO findById(Long id);

	void delete(Long id);
}
