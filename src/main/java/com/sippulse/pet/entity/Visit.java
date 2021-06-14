/**
 * 
 */
package com.sippulse.pet.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = -6211186900414733039L;

	@Column(name = "visit_date", columnDefinition = "DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate visitDate;
	
	@Column(name = "visit_time", columnDefinition = "TIME")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime visitTime;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	@ManyToOne
	@JoinColumn(name = "vet_id")
	private Vet vet;
	
	/**
	 * Creates a new instance of Visit for the current date and time
	 */
	public Visit() {
//		this.visitDate = LocalDate.now();
//		this.visitTime = LocalTime.now();
	}

}
