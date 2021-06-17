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

import com.sippulse.pet.data.vo.v1.VetVO;
import com.sippulse.pet.service.VetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Pet-Shedule_Endpoints", description = "REST API for Vet", tags = { "VetEndpoint" })
@RestController
@RequestMapping("/api/vet/v1")
public class VetController {
	
	@Autowired
	VetService service;
	
	@Autowired
	private PagedResourcesAssembler<VetVO> assembler;

	
    @ApiOperation(value = "Create a new Vet") 
    @RequestMapping(method = RequestMethod.POST,
    consumes = { "application/json", "application/xml", "application/x-yaml" },
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public VetVO create(@RequestBody VetVO pet){
    	VetVO vetVO = service.create(pet);
    	vetVO.add(linkTo(methodOn(VetController.class).get(vetVO.getKey())).withSelfRel());
        return vetVO;
    }
    
    @ApiOperation(value = "Update a specific Vet")
    @RequestMapping(method = RequestMethod.PUT,
    consumes = { "application/json", "application/xml", "application/x-yaml" })
    public VetVO update(@RequestBody VetVO pet){
    	VetVO vetVO = service.update(pet);
    	vetVO.add(linkTo(methodOn(VetController.class).get(vetVO.getKey())).withSelfRel());
        return vetVO;
    }
    
    @ApiOperation(value = "Find a specific Vet by your ID" )
    @RequestMapping(value = "/{id}",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public VetVO get(@PathVariable(value = "id") Long id){
        VetVO vetVO = service.findById(id);
        vetVO.add(linkTo(methodOn(VetController.class).get(id)).withSelfRel());
        return vetVO;
    }
    
    @ApiOperation(value = "List all Vets" )
    @RequestMapping(
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction){
    	
    	Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
    	
    	Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
    	
    	
    	Page<VetVO> vets = service.findAll(pageableRequest);


    	vets
    		.stream()
    		.forEach(p -> p.add(
    				linkTo(methodOn(VetController.class).get(p.getKey())).withSelfRel()
				)
			);
        PagedResources<?> resources = assembler.toResource(vets);

        return ResponseEntity.ok(resources);
    }
    
    @ApiOperation(value = "Delete a specific Vet by your ID")
    @RequestMapping(value = "/{id}",
    method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @ApiOperation(value = "List all vets by first name" )
    @RequestMapping( value = "vet-by-first-name",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findVisitByFirstNameVet(
    		@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, 
            @RequestParam(value = "first-name", defaultValue = "fulano de Tal") String firstName){
    	
    	Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
    	
    	Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
    	
    	
    	Page<VetVO> vets = service.findByFirstName(firstName, pageableRequest);


    	vets
    		.stream()
    		.forEach(p -> p.add(
    				linkTo(methodOn(VetController.class).get(p.getKey())).withSelfRel()
				)
			);
        PagedResources<?> resources = assembler.toResource(vets);

        return ResponseEntity.ok(resources);
    }

    
}