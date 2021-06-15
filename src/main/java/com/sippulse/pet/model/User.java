/**
 * 
 */
package com.sippulse.pet.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Fábio Figueiredo da Silva
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Entity
public class User extends PersonEntity implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5093901342206060929L;

	@NotEmpty
	@Setter
	private String userName;
	
	@Setter
	private String password;
	
	@Getter @Setter
	private Boolean accountNonExpired;
	
	@Getter @Setter
	private Boolean accountNonLocked;
	
	@Getter @Setter
	private Boolean credentialsNonExpired;
	
	@Getter @Setter
	private Boolean enabled;
	
	/**
	 * cpf of the owner.
	 * 
	 * @param cpf of the owner.
	 * @return The current value of this owner's.
	 */	
	@Getter @Setter String cpf;
	
	@Getter @Setter
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_permission", joinColumns = { @JoinColumn (name = "user_id") },
			inverseJoinColumns = { @JoinColumn (name = "permission_id")})
	private List<Permission> permissions;
	
	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for (Permission permission : this.permissions) {
			roles.add(permission.getDescription());
		}
		return roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
      return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

}
