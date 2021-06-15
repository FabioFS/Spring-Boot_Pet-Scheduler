/**
 * 
 */
package com.sippulse.pet.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sippulse.pet.model.User;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u WHERE u.userName =:userName")
	User findByUsername(@Param("userName") String userName);

	User findUserByCpf(String cpf);

}