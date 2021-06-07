/**
 * 
 */
package com.sippulse.pet.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

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
public class Visit extends DescriptionEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "visit_date")
	@DateTimeFormat(pattern = "dd/MM/yyyy kk:mm")
	private LocalDate date;

	@Column(name = "pet_id")
	private Long petId;
	
	@Column(name = "vet_id")
	private Long vetId;
	
	/**
	 * Creates a new instance of Visit for the current date
	 */
	public Visit() {
		this.date = LocalDate.now();
	}

}
