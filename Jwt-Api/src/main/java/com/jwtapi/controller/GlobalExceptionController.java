package com.jwtapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> handleUsernameNotFoundException(AuthenticationException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Mot de passe ou login incorrect.");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .reduce((s1, s2) -> s1 + ", " + s2)
                .orElse("Validation failed");

        return ResponseEntity.badRequest().body(errorMessage);
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur Serveur Interne.");
	}

	
	
}
