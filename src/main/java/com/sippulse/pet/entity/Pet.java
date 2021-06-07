package com.sippulse.pet.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

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
	private static final long serialVersionUID = 2802479334120486179L;

	private String namePet;
	
	@Column(name = "birth_date")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate birthDate;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private PetType type;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;
	
	@Transient
	private Set<Visit> visits = new LinkedHashSet<>();
	
	protected Set<Visit> getVisitsInternal() {
		if (this.visits == null) {
			this.visits = new HashSet<>();
		}
		return this.visits;
	}

	protected void setVisitsInternal(Collection<Visit> visits) {
		this.visits = new LinkedHashSet<>(visits);
	}

	public List<Visit> getVisits() {
		List<Visit> sortedVisits = new ArrayList<>(getVisitsInternal());
		PropertyComparator.sort(sortedVisits, new MutableSortDefinition("date", false, false));
		return Collections.unmodifiableList(sortedVisits);
	}

	public void addVisit(Visit visit) {
		getVisitsInternal().add(visit);
		visit.setPetId(this.getId());
	}
}
