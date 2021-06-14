/**
 * 
 */
package com.sippulse.pet.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private static final long serialVersionUID = 7693537620359813193L;
	
	@OneToMany(mappedBy = "vet", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Visit> visits;
	

}
