package com.sippulse.pet.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {

	ROLE_OWNER_CRUD("/api/owner/**", "EMPLOYEE"),
	ROLE_USER_CRUD("/api/user/**", "EMPLOYEE"),
	ROLE_PET_CRUD("/api/pet/**", "EMPLOYEE"),
	ROLE_VISIT_CRUD("/api/visit/**","EMPLOYEE"),
	
	ROLE_FIND_VISIT_BY_OWNER("/api/visit/find-visit-by-cpf","");
	
	private String url;
	private String description;

	public static Roles find(String role) {
		for (Roles r : values()) {
			if (r.toString().equals(role)) {
				return r;
			}
		}
		return null;
	}
	
}
