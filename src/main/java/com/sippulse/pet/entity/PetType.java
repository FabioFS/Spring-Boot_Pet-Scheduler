/**
 * 
 */
package com.sippulse.pet.entity;

import javax.persistence.Entity;

import com.sippulse.pet.model.NamedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
public class PetType extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1886996061209198767L;

}
