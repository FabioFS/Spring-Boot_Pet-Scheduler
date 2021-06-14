/**
 * 
 */
package com.sippulse.pet.model;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
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

}
