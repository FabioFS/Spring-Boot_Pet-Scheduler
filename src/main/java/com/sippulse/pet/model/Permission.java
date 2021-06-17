/**
 * 
 */
package com.sippulse.pet.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Fábio Figueiredo da Silva
 * 
 * Class Permission integrate with Spring Security implementing GrantedAuthority
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
public class Permission extends DescriptionEntity implements GrantedAuthority, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3078329334989826541L;

	@Override
	public String getAuthority() {
		return this.getDescription();
	}
	
	@ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<User> users;

}
