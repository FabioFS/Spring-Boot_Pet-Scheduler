package com.sippulse.pet.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissionEnum {

	EMPLOYEE("EMPLOYEE"),
	VET("VET"),
	MANAGER("MANAGER");

	private String permission;

	public static PermissionEnum find(String permission) {
		for (PermissionEnum r : values()) {
			if (r.toString().equals(permission)) {
				return r;
			}
		}
		return null;
	}
	
}