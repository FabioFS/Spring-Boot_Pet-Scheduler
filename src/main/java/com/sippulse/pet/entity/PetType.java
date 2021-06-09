/**
 * 
 */
package com.sippulse.pet.entity;

import javax.persistence.Entity;

import com.sippulse.pet.model.DescriptionEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
public class PetType extends DescriptionEntity{/**
	 * 
	 */
	private static final long serialVersionUID = 740279523914867423L;
	
	

}
