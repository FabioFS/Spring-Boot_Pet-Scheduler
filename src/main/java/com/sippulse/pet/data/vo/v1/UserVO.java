/**
 * 
 */
package com.sippulse.pet.data.vo.v1;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import com.sippulse.pet.model.Permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
@JsonPropertyOrder({ "id", "firstName", "lastName", "cpf", "userName", "accountNonExpired", "accountNonLocked","credentialsNonExpired","enabled","permissions"})
public class UserVO extends ResourceSupport implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6879893295195708759L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	private String firstName;
	private String lastName;
	private String cpf;
	private String userName;
	private String password;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean credentialsNonExpired;
	private Boolean enabled;

	@JsonIgnoreProperties("users")
	private List<Permission> permissions;
	

}
