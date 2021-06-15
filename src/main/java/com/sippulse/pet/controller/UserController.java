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

import com.sippulse.pet.data.vo.v1.UserVO;
import com.sippulse.pet.service.impl.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@Api(value = "Pet-Shedule_Endpoints", description = "REST API for User", tags = { "UserEndpoint" })
@RestController
@RequestMapping("/api/user/v1")
public class UserController {
	
	@Autowired
	private UserServiceImpl service;
	
    @Autowired
	private PagedResourcesAssembler<UserVO> assembler;
	
	
    @ApiOperation(value = "Create a new User") 
    @RequestMapping(method = RequestMethod.POST,
    consumes = { "application/json", "application/xml", "application/x-yaml" },
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public UserVO create(@RequestBody UserVO user){
    	UserVO userVO = service.create(user);
        userVO.add(linkTo(methodOn(UserController.class).get(userVO.getKey())).withSelfRel());
        return userVO;
    }
    
    @ApiOperation(value = "Update a specific User")
    @RequestMapping(method = RequestMethod.PUT,
    consumes = { "application/json", "application/xml", "application/x-yaml" })
    public UserVO update(@RequestBody UserVO user){
    	UserVO userVO = service.update(user);
        userVO.add(linkTo(methodOn(UserController.class).get(userVO.getKey())).withSelfRel());
        return userVO;
    }
    
    @ApiOperation(value = "List all User" )
    @RequestMapping(
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAll(
    		@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction){
    	
    	Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
    	
    	Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
    	
    	
    	Page<UserVO> users = service.findAll(pageableRequest);


    	users
    		.stream()
    		.forEach(p -> p.add(
    				linkTo(methodOn(UserController.class).get(p.getKey())).withSelfRel()
				)
			);
        PagedResources<?> resources = assembler.toResource(users);

        return ResponseEntity.ok(resources);
    }
    
    @ApiOperation(value = "Find a specific User by your ID" )
    @RequestMapping(value = "/{id}",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public UserVO get(@PathVariable(value = "id") Long id){
        UserVO userVO = service.findById(id);
        userVO.add(linkTo(methodOn(UserController.class).get(id)).withSelfRel());
        return userVO;
    }
    
    @ApiOperation(value = "Find a specific User by your CPF" )
    @RequestMapping(value = "/get-by-cpf/{cpf}",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public UserVO getByCpf(@PathVariable(value = "cpf") String cpf){
        UserVO userVO = service.findUserByCpf(cpf);
        userVO.add(linkTo(methodOn(UserController.class).getByCpf(cpf)).withSelfRel());
        return userVO;
    }
    
    @ApiOperation(value = "Delete a specific User by your ID")
    @RequestMapping(value = "/{id}",
    method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
 }
