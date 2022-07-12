package com.example.dbproject.controller;

import com.example.dbproject.dto.CelebrityDto;
import com.example.dbproject.model.Credentials;
import com.example.dbproject.repository.CredentialRepository;
import com.example.dbproject.security.AuthenticationRequest;
import com.example.dbproject.security.AuthenticationResponse;
import com.example.dbproject.security.JwtUtils;
import com.example.dbproject.service.Celebrities;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/signUp")
@Slf4j
@Validated
public class SignupController {
    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private Celebrities celebrity;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @Autowired
    private JwtUtils jwtTokenUtil;

    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @ApiOperation(value = "add a new celebrity", notes = "This method adds a new celebrity")
    public Long signUp(@Valid @RequestBody Credentials Credentials) throws ExpiredJwtException {
        credentialRepository.save(Credentials);
        if (Credentials != null) return Credentials.getId();
        else
            throw new EntityNotFoundException("wrong input..");

    }

    @PostMapping(value = "/authenticate", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "creation of JWT token", notes = "This method creates a jwt token  for the admin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException, ExpiredJwtException {
       List<Credentials> list= credentialRepository.findByUserName(authenticationRequest.getUsername());
        if (!list.isEmpty())
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        else
            return null;
        final UserDetails userDetails = User.withDefaultPasswordEncoder().username(authenticationRequest.getUsername())
                .password(authenticationRequest.getPassword()).roles(list.get(0).getRole()).authorities(list.get(0).getAuthority()).build();
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.accepted().body(new AuthenticationResponse(jwt));
    }
}
