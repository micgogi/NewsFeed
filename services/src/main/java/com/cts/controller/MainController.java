package com.cts.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.JwtResponse;
import com.cts.entity.ResponseMessage;
import com.cts.entity.Users;
import com.cts.exception.ExeceptionHandle;
import com.cts.repository.UsersRepository;
import com.cts.security.JwtProvider;
import com.cts.service.UserService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/auth")
public class MainController extends ExeceptionHandle{
	@Autowired UserService userService;
	@Autowired AuthenticationManager authenticationManager;
	@Autowired JwtProvider jwtProvider;
	@Autowired UsersRepository userRepo;
	@Autowired PasswordEncoder passwordEncoder;
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody Users users) {
		System.out.println("M"+users.getPassword());
		System.out.println(users);
		System.out.println(users.getEmail());
		System.out.println(users.getPassword());
		if (userRepo.existsByUsername(users.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepo.existsByEmail(users.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		users.setPassword(passwordEncoder.encode(users.getPassword()));
	userService.save(users);
	return new ResponseEntity<>(new ResponseMessage("Message: Details saved Successfully"),HttpStatus.OK);
	}
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser( @RequestBody Users users ) {
        
		System.out.println("in signin");
		System.out.println(users.getUsername()+" "+users.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));
		System.out.println(users.getUsername()+" "+users.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}
	
}
