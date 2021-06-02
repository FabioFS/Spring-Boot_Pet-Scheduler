/**
 * 
 */
package com.sippulse.pet.system;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fábio Figueiredo da Silva
 *
 */

@RestController
@RequestMapping("/")
class WellcomeController {


	public String wellcome() {
		return "Wellcome";
	}
	
	
}
