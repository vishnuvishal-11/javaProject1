package com.example.dbproject.controller;
import com.example.dbproject.dto.UserDto;
import com.example.dbproject.security.AuthenticationRequest;
import com.example.dbproject.security.AuthenticationResponse;
import com.example.dbproject.security.JwtUtils;
import com.example.dbproject.service.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    User user;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @Autowired
    private JwtUtils jwtTokenUtil;

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @ApiOperation(value = "Read user", notes = "This method fetches user having given id")
    public UserDto read(@PathVariable Long id) throws ExpiredJwtException {
        return user.get(id);

    }

    @GetMapping(value = "/",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @ApiOperation(value = "Read All users", notes = "This method fetches all  users with pagination of given id")
    public Page<UserDto> readAll(@RequestParam(value ="search" ,required = false) String search, @RequestParam(value = "offset",required = false,defaultValue = "0") int offset,
                                 @RequestParam(value = "pagesize",required = false,defaultValue = "5") int pageSize, @RequestParam(value = "field",required = false,defaultValue = "id")String field) throws ExpiredJwtException {
        return user.getAll(search,offset,pageSize,field);
    }


    @PostMapping(value = "/",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @ApiOperation(value = "add a new user", notes = "This method adds a new user")
    public Long create(@Validated @RequestBody UserDto userDto) throws ParseException, SQLIntegrityConstraintViolationException,ExpiredJwtException {
            return user.save(userDto);

    }

    @DeleteMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @ApiOperation(value = "delete  the user", notes = "This method deletes a  user having the given id")
    public void delete(@PathVariable Long id)throws ExpiredJwtException
    {
          user.delete(id);

    }

    @PutMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    @ApiOperation(value = "update  the user details", notes = "This method update the user details having the given id")
    public UserDto update(@RequestBody UserDto userDto, @PathVariable Long id) throws ParseException,ExpiredJwtException {
    try{
       return user.update(userDto,id);

        } catch (ParseException e) {
            throw new ParseException("parse error",0);
        }


}

//    @PostMapping(value = "/authenticate",produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ApiOperation(value = "creation of JWT token", notes = "This method creates a jwt token  for the user")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException, ExpiredJwtException {
//        if(inMemoryUserDetailsManager.userExists(authenticationRequest.getUsername()))
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//        else
//            return  null;
//        final UserDetails userDetails = inMemoryUserDetailsManager.loadUserByUsername(authenticationRequest.getUsername());
//        final String jwt = jwtTokenUtil.generateToken(userDetails);
//        return ResponseEntity.accepted().body(new AuthenticationResponse(jwt));
//    }

}