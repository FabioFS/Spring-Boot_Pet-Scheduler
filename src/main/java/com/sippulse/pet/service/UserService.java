/**
 * 
 */
package com.sippulse.pet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sippulse.pet.data.vo.v1.UserVO;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
public interface UserService {

	UserVO create(UserVO user);

	UserVO update(UserVO user);

	UserVO findById(Long id);

	void delete(Long id);

	UserVO findUserByCpf(String cpf);

	Page<UserVO> findAll(Pageable pageableRequest);
}
