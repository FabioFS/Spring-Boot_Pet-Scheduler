/**
 * 
 */
package com.sippulse.pet.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sippulse.pet.data.vo.v1.PetTypeVO;
import com.sippulse.pet.service.PetTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@Api(value = "Pet-Shedule_Endpoints", description = "REST API for PetType", tags = { "PetTypeEndpoint" })
@RestController
@RequestMapping("/api/pet-type/v1")
public class PetTypeController {
	
	@Autowired
	private PetTypeService service;
	
//	@Autowired
//	private PagedResourcesAssembler<PetTypeVO> assembler;
	
	
    @ApiOperation(value = "Create a new PetType") 
    @RequestMapping(method = RequestMethod.POST,
    consumes = { "application/json", "application/xml", "application/x-yaml" },
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public PetTypeVO create(@RequestBody PetTypeVO petType){
    	PetTypeVO petTypeVO = service.create(petType);
        petTypeVO.add(linkTo(methodOn(PetTypeController.class).get(petTypeVO.getKey())).withSelfRel());
        return petTypeVO;
    }
    
    @ApiOperation(value = "Update a specific PetType")
    @RequestMapping(method = RequestMethod.PUT,
    consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PetTypeVO update(@RequestBody PetTypeVO petType){
    	PetTypeVO petTypeVO = service.update(petType);
        petTypeVO.add(linkTo(methodOn(PetTypeController.class).get(petTypeVO.getKey())).withSelfRel());
        return petTypeVO;
    }
    
    @ApiOperation(value = "Find a specific PetType by your ID" )
    @RequestMapping(value = "/{id}",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public PetTypeVO get(@PathVariable(value = "id") Long id){
        PetTypeVO petTypeVO = service.findById(id);
        petTypeVO.add(linkTo(methodOn(PetTypeController.class).get(id)).withSelfRel());
        return petTypeVO;
    }
       
    @ApiOperation(value = "Delete a specific PetType by your ID")
    @RequestMapping(value = "/{id}",
    method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
