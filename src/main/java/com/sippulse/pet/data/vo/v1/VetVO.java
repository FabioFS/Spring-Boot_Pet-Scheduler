/**
 * 
 */
package com.sippulse.pet.data.vo.v1;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import com.sippulse.pet.entity.Visit;
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
@JsonPropertyOrder({ "id", "firstName", "lastName","user"})
public class VetVO extends ResourceSupport implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -152438993366716352L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	private String firstName;
	private String lastName;
	
	
	@JsonIgnoreProperties("vet")
	private List<Visit> visits;
	
	private User user;
	
}
