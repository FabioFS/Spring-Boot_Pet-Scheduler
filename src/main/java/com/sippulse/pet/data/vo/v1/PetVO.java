/**
 * 
 */
package com.sippulse.pet.data.vo.v1;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import com.sippulse.pet.entity.Owner;
import com.sippulse.pet.entity.PetType;
import com.sippulse.pet.entity.Visit;

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
@EqualsAndHashCode(callSuper=false)
@Data
@JsonPropertyOrder({ "id", "namePet", "birthDate", "petType", "owner", "visits","description"})
public class PetVO extends ResourceSupport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	private String namePet;
	private LocalDate birthDate;
	private PetType petType;
	
	@JsonIgnoreProperties("pets")
	private Owner owner;
	
	@JsonIgnoreProperties("pet")
	private List<Visit> visits;
	private String description;

}
