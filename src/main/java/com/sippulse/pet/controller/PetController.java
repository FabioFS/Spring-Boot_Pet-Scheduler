package com.sippulse.pet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "Pet-Shedule_Endpoints", description = "REST API for Pet", tags = { "PetEndpoint" })
@RestController
@RequestMapping("/api/pet/v1")
public class PetController {

	
    @RequestMapping(method = RequestMethod.GET,
	produces = { "application/json", "application/xml", "application/x-yaml" })	
	public String getPet() {
		return "Aló mundoo!";
	}

}