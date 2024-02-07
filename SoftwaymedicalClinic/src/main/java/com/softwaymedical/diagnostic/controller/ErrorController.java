package com.softwaymedical.diagnostic.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

	@ExceptionHandler({ Exception.class})
    public void handleException() {
        System.out.println("Error");
    }

}
