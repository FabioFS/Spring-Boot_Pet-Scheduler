/**
 * 
 */
package com.sippulse.pet.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
public class User extends BaseEntity implements UserDetails {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8028424108647534319L;

	@NotEmpty
	@Setter
	private String userName;
	
	@Setter
	private String password;
	
	@Getter
	@Setter
	private String fullName;
	
	@Getter
	private Boolean accountNonExpired;
	
	@Getter
	private Boolean accountNonLocked;
	
	@Getter
	private Boolean credentialsNonExpired;
	
	@Getter
	private Boolean enabled;
	
	/**
	 * cpf of the owner.
	 * 
	 * @param cpf of the owner.
	 * @return The current value of this owner's.
	 */	
	@NotEmpty
	@Getter @Setter String cpf;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_permission", joinColumns = { @JoinColumn (name = "id_user") },
			inverseJoinColumns = { @JoinColumn (name = "id_permission")})
	private List<Permission> permissions;
	
	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for (Permission permission : this.permissions) {
			roles.add(permission.getName());
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
