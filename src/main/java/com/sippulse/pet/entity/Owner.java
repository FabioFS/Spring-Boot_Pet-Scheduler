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

	/**
	 * CPF of the owner.
	 * 
	 * @param type String -> CPF of the owner.
	 * @return The current value of this owner's.
	 */
	@NotEmpty
	private String cpf;

	/**
	 * cellPhone of the owner.
	 * 
	 * @param type String -> cellPhone of the owner.
	 * @return The current value of this owner's.
	 */
	private String cellPhone;
	
	/**
	 * email of the owner.
	 * 
	 * @param type String -> email of the owner.
	 * @return The current value of this owner's.
	 */
	private String email;
	
	/**
	 * address of the owner.
	 * 
	 * @param type String -> address of the owner.
	 * @return The current value of this owner's.
	 */
	private String address;
	
	/**
	 * List pets of the owner.
	 * 
	 * @param List<PET> -> pets of the owner.
	 * @return The current value of this owner's.
	 */
	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Pet> pets;
}