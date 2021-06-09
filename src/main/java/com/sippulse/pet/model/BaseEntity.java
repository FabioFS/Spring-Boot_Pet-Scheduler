package com.sippulse.pet.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Simple JavaBean domain object with an id property. Used as a base class for objects
 * needing this property.
 * 
 * @author Fábio Figueiredo da Silva
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6827722653399433828L;
	
	/**
	 * Unique identification of an Entity
	 * 
	 * @param entity identification by id.
	 * @return The current value of this entity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}