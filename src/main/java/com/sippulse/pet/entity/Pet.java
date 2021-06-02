package com.sippulse.pet.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sippulse.pet.model.NamedEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
public class Pet extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2802479334120486179L;

	private String nomePet;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;
}
