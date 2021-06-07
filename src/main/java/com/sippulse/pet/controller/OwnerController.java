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
	
//	@Autowired
//	private PagedResourcesAssembler<OwnerVO> assembler;
	
	
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
    @RequestMapping(value = "/{cpf}",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public OwnerVO getByCpf(@PathVariable(value = "cpf") String cpf){
        OwnerVO ownerVO = service.findByCpf(cpf);
        ownerVO.add(linkTo(methodOn(OwnerController.class).getByCpf(cpf)).withSelfRel());
        return ownerVO;
    }
    
    
    @ApiOperation(value = "Set a specific Owner by to disabled")
    @RequestMapping(value = "/{id}",
    method = RequestMethod.PATCH)
    public OwnerVO disable(@PathVariable(value = "id") Long id){
    	OwnerVO ownerVO = service.disableOwner(id);
        ownerVO.add(linkTo(methodOn(OwnerController.class).get(ownerVO.getKey())).withSelfRel());
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
