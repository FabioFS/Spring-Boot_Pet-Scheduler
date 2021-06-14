/**
 * 
 */
package com.sippulse.pet.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private static final long serialVersionUID = -8157980336140096938L;

	@NotEmpty
	private String cpf;

	/**
	 * cellPhone of the owner.
	 * 
	 * @param type String -> name of the owner.
	 * @return The current value of this owner's.
	 */
	private String cellPhone;
	
	private String email;
	
	private String address;
	
	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Pet> pets;
}