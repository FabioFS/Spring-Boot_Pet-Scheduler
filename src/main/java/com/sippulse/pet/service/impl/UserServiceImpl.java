/**
 * 
 */
package com.sippulse.pet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sippulse.pet.converter.DozerConverter;
import com.sippulse.pet.data.vo.v1.UserVO;
import com.sippulse.pet.model.User;
import com.sippulse.pet.repository.UserRepository;
import com.sippulse.pet.service.UserService;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@Service
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	UserRepository repository;
	
	public void UserServices(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = repository.findByUsername(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
	}

	@Override
	public UserVO create(UserVO user) {
		User entity = DozerConverter.parseObject(user, User.class);
		UserVO vo = DozerConverter.parseObject(repository.save(entity), UserVO.class);
		return vo;
	}

	@Override
	public UserVO update(UserVO user) {
		User entity = repository.findById(user.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
	   
	    if (!isNullOrEmpty( user.getFirstName())){ entity.setFirstName(user.getFirstName());};
	    if (!isNullOrEmpty( user.getLastName())) { entity.setLastName(user.getLastName());};
	    if (!isNullOrEmpty( user.getCredentialsNonExpired())) { entity.setCredentialsNonExpired(user.getCredentialsNonExpired());};

		entity.setCpf(user.getCpf());
		entity.setUserName(user.getUserName());
		entity.setEnabled(user.getEnabled());
		entity.setPassword(cryptPass(user.getPassword()));
		//entity.setPermissions(null);
		//entity.setCredentialsNonExpired(user.getCredentialsNonExpired());
		entity.setAccountNonLocked(user.getAccountNonLocked());
		entity.setAccountNonLocked(user.getAccountNonLocked());

		UserVO vo = DozerConverter.parseObject(repository.save(entity), UserVO.class);
		return vo;
	}

	private boolean isNullOrEmpty(Object a) {
	        return a == null || a. toString().isEmpty();
	    }
	
	@Override
	public Page<UserVO> findAll(Pageable pageable) {
		Page<User> page = repository.findAll(pageable);
		return page.map(this::convertToUserVO);
	}	
	
	private UserVO convertToUserVO(User entity) {
		return DozerConverter.parseObject(entity, UserVO.class);
	}
	
	@Override
	public UserVO findById(Long id) {
		User entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, UserVO.class);
	}

	@Override
	public void delete(Long id) {
		User entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
		
	}

	@Override
	public UserVO findUserByCpf(String cpf) {
		User entity = repository.findUserByCpf(cpf);
		if (entity != null) {
			return DozerConverter.parseObject(entity, UserVO.class);
		} else {
			throw new UsernameNotFoundException("User not found");
		}
	}

    private String cryptPass(String pass) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		String result = bCryptPasswordEncoder.encode("123admin");
		return result;
    }
}
