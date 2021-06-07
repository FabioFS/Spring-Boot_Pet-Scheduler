/**
 * 
 */
package com.sippulse.pet.entity;

import javax.persistence.Entity;

import com.sippulse.pet.model.PersonEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
public class Vet extends PersonEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8659424248121676261L;
	

}
