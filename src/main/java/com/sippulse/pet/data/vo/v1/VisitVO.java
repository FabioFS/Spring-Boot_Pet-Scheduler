/**
 * 
 */
package com.sippulse.pet.data.vo.v1;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

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
@JsonPropertyOrder({ "id", "petId", "vetId", "visitDate"})
public class VisitVO extends ResourceSupport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	private LocalDate visitDate;
	private Long petId;
	private Long vetId;

	

}
