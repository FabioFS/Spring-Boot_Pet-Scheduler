/**
 * 
 */
package com.sippulse.pet.model;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * Simple JavaBean domain object with an id property to <code>BaseEntity</code>. 
 * Used as a base class for objects needing this property.
 * 
 * @author Fábio Figueiredo da Silva
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Data
@MappedSuperclass
public class DescriptionEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3442613052882700716L;
	
	/**
	 * description of an Entity
	 * 
	 * @param name of the Entity.
	 * @return The current value of this Entity.
	 */
	private String description;

}
