/**
 * 
 */
package com.sippulse.pet.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.sippulse.pet.model.PersonEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Fábio Figueiredo da Silva
 * 
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
public class Owner extends PersonEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5272533592153838148L;

	/**
	 * cellPhone of the owner.
	 * 
	 * @param type String -> name of the owner.
	 * @return The current value of this owner's.
	 */
	private String cellPhone;
	
	private String email;
	
	private String address;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private Set<Pet> pets;

}
