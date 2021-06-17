package com.sippulse.pet.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {

	ROLE_OWNER_CRUD("/api/owner/**"),
	ROLE_PET_CRUD("/api/pet/**"),
	ROLE_PET_TYPE_CRUD("/api/pet-type/**"),
	ROLE_USER_CRUD("/api/user/**"),
	ROLE_VET_CRUD("/api/vet/**"),
	ROLE_VISIT_CRUD("/api/visit/private/**"),
	
	ROLE_FIND_VISIT_BY_OWNER("/api/visit/v1/public/visit-by-cpf/**");
	
	private String url;

	public static RoleEnum find(String role) {
		for (RoleEnum r : values()) {
			if (r.toString().equals(role)) {
				return r;
			}
		}
		return null;
	}
	
}