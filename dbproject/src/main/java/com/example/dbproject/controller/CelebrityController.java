package com.example.dbproject.controller;
import com.example.dbproject.security.AuthenticationRequest;
import com.example.dbproject.security.AuthenticationResponse;
import com.example.dbproject.security.JwtUtils;
import com.example.dbproject.service.Celebrities;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.dbproject.dto.CelebrityDto;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/celebrity")
@Slf4j
@Validated
public class CelebrityController {
    @Autowired
    Celebrities celebrity;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
   InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @Autowired
    private JwtUtils jwtTokenUtil;

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Read celebrity", notes = "This method fetches celebrity having given id")
    public CelebrityDto read(@PathVariable Long id) throws ExpiredJwtException {
       return celebrity.get(id);
    }

    @GetMapping(value ="/",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @ApiOperation(value = "Read All celebrities", notes = "This method fetches all  celebrities with pagination of given id")
    public Page<CelebrityDto> readAll(@RequestParam(value ="search" ,required = false) String search,@RequestParam(value = "offset",required = false,defaultValue = "0") int offset,
            @RequestParam(value = "pagesize",required = false,defaultValue = "5") int pageSize, @RequestParam(value = "field",required = false,defaultValue = "id")String field)throws ExpiredJwtException {
        return celebrity.getAll(search,offset,pageSize,field);
    }


    @PostMapping(value ="/",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @ApiOperation(value = "add a new celebrity", notes = "This method adds a new celebrity")
    public Long create(@Valid @RequestBody CelebrityDto CelebrityDto) throws ExpiredJwtException {
        Long id = celebrity.save(CelebrityDto);
        if (id != null) return id;
             else
            throw new EntityNotFoundException("wrong input..");

    }

    @DeleteMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @ApiOperation(value = "delete  the celebrity", notes = "This method deletes a  celebrity having the given id")
    public void delete(@PathVariable Long id) throws ExpiredJwtException {
        celebrity.delete(id);
    }

    @PutMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @ApiOperation(value = "update  the celebrity details", notes = "This method update the celebrity details having the given id")
    public CelebrityDto update(@RequestBody CelebrityDto CelebrityDto, @PathVariable Long id) throws ExpiredJwtException {
        return (CelebrityDto) celebrity.update(CelebrityDto, id);
    }

    @PostMapping(value = "/authenticate",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "creation of JWT token", notes = "This method creates a jwt token  for the admin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest  authenticationRequest) throws BadCredentialsException,ExpiredJwtException {
            if(inMemoryUserDetailsManager.userExists(authenticationRequest.getUsername()))
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            else
            return  null;
        final UserDetails userDetails = inMemoryUserDetailsManager.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.accepted().body(new AuthenticationResponse(jwt));
    }


}
