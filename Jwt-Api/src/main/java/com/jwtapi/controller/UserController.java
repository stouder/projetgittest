package com.jwtapi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
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

import com.jwtapi.common.HelperJwt;
import com.jwtapi.common.NoResultFoundException;
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
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {

	@Value("${app.cookie.expiration}")
	private long expirationcookie;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Resource
	private CacheManager cacheManager;

	@Operation(summary = "Connect to the system using username and password")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Logged in successfully.", content = @Content(schema = @Schema(implementation = RefreshRequest.class))) })

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome this endpoint is not secure";
	}

	@GetMapping(value = { "/bonjour/", "/bonjour/{name}" })
	public String bonjour(@PathVariable(name = "name") String name) {
		return "Bonjour cet endpoint n'est pas sécurisé " + name;
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

		Cache cache = cacheManager.getCache("cache-data");

		if (cache != null) {
			UserInfosDTO userInfosDTO = cache.get(username, UserInfosDTO.class);
			if (userInfosDTO == null) {
				userInfosDTO = userInfoService.findEntityByName(username);
				if (userInfosDTO != null) {
					cache.put(username, userInfosDTO);
				}
			}
			return ResponseEntity.ok(userInfosDTO);
		} else {
			log.error("Cache problem : cache instance is null");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@Cacheable("cache-data")
	@GetMapping(value = { "/user/", "/user/{id}" })
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<UserInfosDTO> userProfileById(@PathVariable UUID id) throws NoResultFoundException {

		UserInfosDTO userInfosDTO = userInfoService.findById(id);

		return ResponseEntity.ok(userInfosDTO);
	}

	@GetMapping("/users")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<List<UserInfosDTO>> allUsers() {
		List<UserInfosDTO> usersInfosDTO = userInfoService.findAll();
		return ResponseEntity.ok(usersInfosDTO);
	}

	@GetMapping("/admin/adminProfile")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> adminProfile() {
		return ResponseEntity.ok("Profile Admin afficher le nom + email");
	}

	@PostMapping("/generateToken")
	public ResponseEntity<String> authenticateAndGetToken(@RequestBody @Valid AuthRequest authRequest,
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
	public ResponseEntity<String> refresh(@RequestBody @Valid RefreshRequest refreshRequest,
			HttpServletResponse response) {
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
