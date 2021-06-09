/**
 * 
 */
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

import com.sippulse.pet.data.vo.v1.OwnerVO;
import com.sippulse.pet.service.OwnerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@Api(value = "Pet-Shedule_Endpoints", description = "REST API for Owner", tags = { "OwnerEndpoint" })
@RestController
@RequestMapping("/api/owner/v1")
public class OwnerController {
	
	@Autowired
	private OwnerService service;
	
    @Autowired
	private PagedResourcesAssembler<OwnerVO> assembler;
	
	
    @ApiOperation(value = "Create a new Owner") 
    @RequestMapping(method = RequestMethod.POST,
    consumes = { "application/json", "application/xml", "application/x-yaml" },
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public OwnerVO create(@RequestBody OwnerVO owner){
    	OwnerVO ownerVO = service.create(owner);
        ownerVO.add(linkTo(methodOn(OwnerController.class).get(ownerVO.getKey())).withSelfRel());
        return ownerVO;
    }
    
    @ApiOperation(value = "Update a specific Owner")
    @RequestMapping(method = RequestMethod.PUT,
    consumes = { "application/json", "application/xml", "application/x-yaml" })
    public OwnerVO update(@RequestBody OwnerVO owner){
    	OwnerVO ownerVO = service.update(owner);
        ownerVO.add(linkTo(methodOn(OwnerController.class).get(ownerVO.getKey())).withSelfRel());
        return ownerVO;
    }
    
    @ApiOperation(value = "List all Owner" )
    @RequestMapping(
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction){
    	
    	Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
    	
    	Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
    	
    	
    	Page<OwnerVO> owners = service.findAll(pageableRequest);


    	owners
    		.stream()
    		.forEach(p -> p.add(
    				linkTo(methodOn(OwnerController.class).get(p.getKey())).withSelfRel()
				)
			);
        PagedResources<?> resources = assembler.toResource(owners);

        return ResponseEntity.ok(resources);
    }
    
    @ApiOperation(value = "Find a specific Owner by your ID" )
    @RequestMapping(value = "/{id}",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public OwnerVO get(@PathVariable(value = "id") Long id){
        OwnerVO ownerVO = service.findById(id);
        ownerVO.add(linkTo(methodOn(OwnerController.class).get(id)).withSelfRel());
        return ownerVO;
    }
    
    @ApiOperation(value = "Find a specific Owner by your CPF" )
    @RequestMapping(value = "/get-by-cpf/{cpf}",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public OwnerVO getByCpf(@PathVariable(value = "cpf") String cpf){
        OwnerVO ownerVO = service.findOwnerByCpf(cpf);
        ownerVO.add(linkTo(methodOn(OwnerController.class).getByCpf(cpf)).withSelfRel());
        return ownerVO;
    }
    
    @ApiOperation(value = "Delete a specific Owner by your ID")
    @RequestMapping(value = "/{id}",
    method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
