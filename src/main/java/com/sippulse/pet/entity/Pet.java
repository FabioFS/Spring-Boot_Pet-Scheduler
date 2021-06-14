package com.sippulse.pet.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sippulse.pet.model.DescriptionEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
public class Pet extends DescriptionEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3059554084548471731L;

	private String namePet;
	
	@Column(name = "birth_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pet_type_id")
	private PetType petType;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;
	
	@OneToMany(mappedBy = "pet", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Visit> visits;
	
	
}
