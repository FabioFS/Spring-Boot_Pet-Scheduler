/**
 * 
 */
package com.sippulse.pet.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sippulse.pet.model.PersonEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Fábio Figueiredo da Silva
 * 
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
public class Owner extends PersonEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -314556527848611985L;

	@NotEmpty
	private String cpf;

	/**
	 * cellPhone of the owner.
	 * 
	 * @param type String -> name of the owner.
	 * @return The current value of this owner's.
	 */
	private String cellPhone;
	
	private String email;
	
	private String address;
	
	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Pet> pets;
	
//	protected Set<Pet> getPetsInternal() {
//		if (this.pets == null) {
//			this.pets = new HashSet<>();
//		}
//		return this.pets;
//	}
//
//	protected void setPetsInternal(Set<Pet> pets) {
//		this.pets = pets;
//	}
//
////	public List<Pet> getPets() {
////		List<Pet> sortedPets = new ArrayList<>(getPetsInternal());
////		PropertyComparator.sort(sortedPets, new MutableSortDefinition("name", true, true));
////		return Collections.unmodifiableList(sortedPets);
////	}
//
//	public void addPet(Pet pet) {
//		if (pet.isNew()) {
//			getPetsInternal().add(pet);
//		}
//		pet.setOwner(this);
//	}
//
//	/**
//	 * Return the Pet with the given name, or null if none found for this Owner.
//	 * @param name to test
//	 * @return true if pet name is already in use
//	 */
//	public Pet getPet(String name) {
//		return getPet(name, false);
//	}
//
//	/**
//	 * Return the Pet with the given name, or null if none found for this Owner.
//	 * @param name to test
//	 * @return true if pet name is already in use
//	 */
//	public Pet getPet(String name, boolean ignoreNew) {
//		name = name.toLowerCase();
//		for (Pet pet : getPetsInternal()) {
//			if (!ignoreNew || !pet.isNew()) {
//				String compName = pet.getNamePet();
//				compName = compName.toLowerCase();
//				if (compName.equals(name)) {
//					return pet;
//				}
//			}
//		}
//		return null;
//	}
}