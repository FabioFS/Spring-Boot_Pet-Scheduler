/**
 * 
 */
package com.sippulse.pet.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.LocalDate;

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

import com.sippulse.pet.data.vo.v1.VisitVO;
import com.sippulse.pet.service.VisitService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Fábio Figueiredo da Silva
 *
 */
@Api(value = "Pet-Shedule_Endpoints", description = "REST API for Visit", tags = { "VisitEndpoint" })
@RestController
@RequestMapping("/api/visit/v1")
public class VisitController {
	
	@Autowired
	private VisitService service;
	
	@Autowired
	private PagedResourcesAssembler<VisitVO> assembler;
	
	
    @ApiOperation(value = "Create a new Visit") 
    @RequestMapping(value = "/private/create", method = RequestMethod.POST,
    consumes = { "application/json", "application/xml", "application/x-yaml" },
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public VisitVO create(@RequestBody VisitVO visit){
    	VisitVO visitVO = service.create(visit);
        visitVO.add(linkTo(methodOn(VisitController.class).get(visitVO.getKey())).withSelfRel());
        return visitVO;
    }
    
    @ApiOperation(value = "Update a specific Visit")
    @RequestMapping(value = "/private/update", method = RequestMethod.PUT,
    consumes = { "application/json", "application/xml", "application/x-yaml" })
    public VisitVO update(@RequestBody VisitVO visit){
    	VisitVO visitVO = service.update(visit);
        visitVO.add(linkTo(methodOn(VisitController.class).get(visitVO.getKey())).withSelfRel());
        return visitVO;
    }
    
    @ApiOperation(value = "Find a specific Visit by your ID" )
    @RequestMapping(value = "/private/{id}",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public VisitVO get(@PathVariable(value = "id") Long id){
        VisitVO visitVO = service.findById(id);
        visitVO.add(linkTo(methodOn(VisitController.class).get(id)).withSelfRel());
        return visitVO;
    }
       
    @ApiOperation(value = "Delete a specific Visit by your ID")
    @RequestMapping(value = "/private/{id}",
    method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @ApiOperation(value = "List all visit" )
    @RequestMapping( path = "/private/all-visits",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAll(
     		@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {
    	
    	Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
    	
    	Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(sortDirection,"visitDate"));
    	
    	
    	Page<VisitVO> visits = service.findAll(pageableRequest);


    	visits
    		.stream()
    		.forEach(p -> p.add(
    				linkTo(methodOn(VisitController.class).get(p.getKey())).withSelfRel()
				)
			);
        PagedResources<?> resources = assembler.toResource(visits);

        return ResponseEntity.ok(resources);
    }
    
    @ApiOperation(value = "List all visit by visit date" )
    @RequestMapping( value = "/private/get-by-date{visit_date}",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAllByDate(
     		@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            @ApiParam(
            		name = "visitDate",
            		type = "LocalDate",
            		value = "input format is yyyy-MM-dd",
            		example = "1999-12-22",
            		required = true)
            @PathVariable(value = "date_time") LocalDate visitDate){
    	
    	Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
    	
    	Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(sortDirection, visitDate.toString()));
    	
    	
    	Page<VisitVO> visits = service.findAll(pageableRequest);


    	visits
    		.stream()
    		.forEach(p -> p.add(
    				linkTo(methodOn(VisitController.class).get(p.getKey())).withSelfRel()
				)
			);
        PagedResources<?> resources = assembler.toResource(visits);

        return ResponseEntity.ok(resources);
    }
    
    @ApiOperation(value = "List all visit of the pet by vet's ID" )
    @RequestMapping( value = "/private/get-by-vet/{vet_id}",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findByVetId(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, @PathVariable(value = "vet_id") Long vet_id){
    	
    	Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
    	
    	Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(sortDirection, "vetId", "visitDate"));
    	
    	
    	Page<VisitVO> visits = service.findByVetId(vet_id, pageableRequest);


    	visits
    		.stream()
    		.forEach(p -> p.add(
    				linkTo(methodOn(VisitController.class).get(p.getKey())).withSelfRel()
				)
			);
        PagedResources<?> resources = assembler.toResource(visits);

        return ResponseEntity.ok(resources);
    }

    @ApiOperation(value = "List all visit of the pet by owner's cpf" )
    @RequestMapping( value = "/public/visit-by-owner/{cpf_owner}",
    method = RequestMethod.GET, 
    produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findVisitByCpfOwner(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction, @PathVariable(value = "cpf_owner") String cpfOwner){
    	
    	Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
    	
    	Pageable pageableRequest = PageRequest.of(page, limit, Sort.by(sortDirection, "v.visit_date"));
    	
    	
    	Page<VisitVO> visits = service.findVisitByCpfOwner(cpfOwner, pageableRequest);


    	visits
    		.stream()
    		.forEach(p -> p.add(
    				linkTo(methodOn(VisitController.class).get(p.getKey())).withSelfRel()
				)
			);
        PagedResources<?> resources = assembler.toResource(visits);

        return ResponseEntity.ok(resources);
    }
}
