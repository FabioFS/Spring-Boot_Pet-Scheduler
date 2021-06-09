package com.sippulse.pet.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sippulse.pet.data.vo.v1.PetVO;
import com.sippulse.pet.service.PetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Pet-Shedule_Endpoints", description = "REST API for Pet", tags = { "PetEndpoint" })
@RestController
@RequestMapping("/api/pet/v1")
public class PetController {
	
	@Autowired
	PetService service;
	
	@Autowired
	private PagedResourcesAssembler<PetVO> assembler;

	
    @ApiOperation(value = "Create a new Pet") 
    @RequestMapping(method = RequestMethod.POST,
    consumes = { "application/json", "application/xml", "application/x-yaml" },
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public PetVO create(@RequestBody PetVO pet){
    	PetVO petVO = service.create(pet);
    	petVO.add(linkTo(methodOn(OwnerController.class).get(petVO.getKey())).withSelfRel());
        return petVO;
    }
    
    @ApiOperation(value = "Update a specific Pet")
    @RequestMapping(method = RequestMethod.PUT,
    consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PetVO update(@RequestBody PetVO pet){
    	PetVO petVO = service.update(pet);
    	petVO.add(linkTo(methodOn(OwnerController.class).get(petVO.getKey())).withSelfRel());
        return petVO;
    }
    
    @ApiOperation(value = "Find a specific Pet by your ID" )
    @RequestMapping(value = "/{id}",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public PetVO get(@PathVariable(value = "id") Long id){
        PetVO petVO = service.findById(id);
        petVO.add(linkTo(methodOn(OwnerController.class).get(id)).withSelfRel());
        return petVO;
    }
    
    @ApiOperation(value = "List all Pets" )
    @RequestMapping(
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction){
    	
    	Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
    	
    	Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(sortDirection, "namePet"));
    	
    	
    	Page<PetVO> pets = service.findAll(pageableRequest);


    	pets
    		.stream()
    		.forEach(p -> p.add(
    				linkTo(methodOn(PetController.class).get(p.getKey())).withSelfRel()
				)
			);
        PagedResources<?> resources = assembler.toResource(pets);

        return ResponseEntity.ok(resources);
    }
    
    @ApiOperation(value = "Delete a specific Pet by your ID")
    @RequestMapping(value = "/{id}",
    method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    
}