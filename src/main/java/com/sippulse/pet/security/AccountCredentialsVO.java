/**
 * 
 */
package com.sippulse.pet.security;

import java.io.Serializable;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountCredentialsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiParam(name = "userName",
    		type = "String",
    		value = "homer@pet",
    		example = "moe@pet",
    		required = true,
    		defaultValue = "homer@pet")
	private String username;
	
	@ApiParam(name = "password",
    		type = "String",
    		value = "123admin",
    		example = "123admin",
    		defaultValue = "123admin",
    		required = true)
	private String password;

}
