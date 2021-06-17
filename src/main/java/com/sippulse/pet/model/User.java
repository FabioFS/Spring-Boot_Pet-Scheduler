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
 * Class User integrate with Spring Security implementing UserDetails
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

	/**
	 * userName of the user.
	 * 
	 * @param type String -> userName of the owner.
	 * @return The current value of this user's.
	 */	
	@NotEmpty
	@Setter
	private String userName;
	
	/**
	 * password of the user.
	 * 
	 * @param type String -> password of the owner.
	 * @return The current value of this user's.
	 */	
	@Setter
	private String password;
	
	/**
	 * accountNonExpired of the user.
	 * 
	 * @param type Boolean -> accountNonExpired of the owner.
	 * @return The current value of this user's.
	 */	
	@Getter @Setter
	private Boolean accountNonExpired;
	
	/**
	 * accountNonExpired of the user.
	 * 
	 * @param type Boolean -> accountNonExpired of the owner.
	 * @return The current value of this user's.
	 */	
	@Getter @Setter
	private Boolean accountNonLocked;
	
	/**
	 * credentialsNonExpired of the user.
	 * 
	 * @param type Boolean -> credentialsNonExpired of the owner.
	 * @return The current value of this user's.
	 */	
	@Getter @Setter
	private Boolean credentialsNonExpired;
	
	/**
	 * enabled of the user.
	 * 
	 * @param type Boolean -> enabled of the owner.
	 * @return The current value of this user's.
	 */	
	@Getter @Setter
	private Boolean enabled;
	
	/**
	 * cpf of the user.
	 * 
	 * @param type String -> CPF of the owner.
	 * @return The current value of this user.
	 */	
	@Getter @Setter String cpf;
	
	/**
	 * permissions of the user.
	 * 
	 * @param type List -> permissions of the owner.
	 * @return The current value of this user's.
	 */
	@Getter @Setter
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_permission", joinColumns = { @JoinColumn (name = "user_id") },
			inverseJoinColumns = { @JoinColumn (name = "permission_id")})
	private List<Permission> permissions;
	
	/**
	 * roles of the user integrate with Spring Security.
	 * 
	 * @param type List -> roles of the owner.
	 * @return The current value of this user's.
	 */
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
