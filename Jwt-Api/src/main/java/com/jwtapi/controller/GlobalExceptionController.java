package com.jwtapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.jwtapi.common.NoResultFoundException;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<String> handleMissingPathVariableException(MissingPathVariableException ex) {
		String errorMessage = "Le paramètre requis '" + ex.getVariableName() + "' est manquant.";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> handleUsernameNotFoundException(AuthenticationException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Mot de passe ou login incorrect.");
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur dans la valeur d'un parametre");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {

		String errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
				.reduce((s1, s2) -> s1 + ", " + s2).orElse("Echec de la validation");

		return ResponseEntity.badRequest().body(errorMessage);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<String> handleNoResourceFoundException(NoResourceFoundException ex,
			HttpServletResponse response) {
		String errorMessage = "Pas de resource pour cet url " + ex.getResourcePath();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}

	@ExceptionHandler(NoResultFoundException.class)
	public ResponseEntity<String> handleNoResultFoundException(NoResultFoundException ex) {
		String errorMessage = ex.getMessage();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erreur Serveur Interne." + ex.getMessage());
	}

}
