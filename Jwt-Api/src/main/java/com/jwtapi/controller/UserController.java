package com.jwtapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtapi.adapter.UserInfosAdapter;
import com.jwtapi.common.HelperJwt;
import com.jwtapi.dto.UserInfosDTO;
import com.jwtapi.model.AuthRequest;
import com.jwtapi.model.RefreshRequest;
import com.jwtapi.model.UserInfo;
import com.jwtapi.service.JwtService;
import com.jwtapi.service.UserInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Value("${app.cookie.expiration}")
	private long expirationcookie;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserInfosAdapter userInfosAdapter;

	@Operation(summary = "Connect to the system using username and password")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Logged in successfully.", content = @Content(schema = @Schema(implementation = RefreshRequest.class))) })

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome this endpoint is not secure";
	}

	@PostMapping("/addNewUser")
	public String addNewUser(@Valid @RequestBody UserInfo userInfo) {
		return userInfoService.addUser(userInfo);
	}

	@GetMapping("/user/currentUserProfile")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<UserInfosDTO> currentUserProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		UserInfosDTO userInfosDTO = userInfoService.findEntityByName(username);

		return ResponseEntity.ok(userInfosDTO);
	}

	@GetMapping("/user/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<UserInfosDTO> userProfileById(@PathVariable("id") UUID id) {

		UserInfosDTO userInfosDTO = userInfoService.findById(id).map(userInfosAdapter::transform).orElse(null);

		return ResponseEntity.ok(userInfosDTO);
	}

	@GetMapping("/admin/adminProfile")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> adminProfile() {
		return ResponseEntity.ok("Profile Admin afficher le nom + email");
	}

	@PostMapping("/generateToken")
	public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest,
			HttpServletResponse response) throws AuthenticationException {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			String token = jwtService.generateToken(authRequest.getUsername());
			HelperJwt.setCookie(response, "jwt", token, expirationcookie);
			return ResponseEntity.ok(token);
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}

	@PostMapping("/refresh")
	public ResponseEntity<String> refresh(@RequestBody RefreshRequest refreshRequest, HttpServletResponse response) {
		String refreshToken = refreshRequest.getToken();
		String newAccessToken = jwtService.refreshAccessToken(refreshToken);
		HelperJwt.setCookie(response, "jwt", newAccessToken, expirationcookie);
		return ResponseEntity.ok(newAccessToken);
	}

	@GetMapping("/deleteCookie")
	public ResponseEntity<String> deleteCookie(HttpServletResponse response) {
		HelperJwt.setCookie(response, "jwt", null, 0);
		return ResponseEntity.ok("Delete Cookie with success");
	}

}
