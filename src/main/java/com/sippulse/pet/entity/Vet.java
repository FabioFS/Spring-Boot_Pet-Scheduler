/**
 * 
 */
package com.sippulse.pet.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sippulse.pet.model.PersonEntity;
import com.sippulse.pet.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
public class Vet extends PersonEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7693537620359813193L;
	
	@OneToMany(mappedBy = "vet")
	@JsonIgnore
	private List<Visit> visits;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	

}
