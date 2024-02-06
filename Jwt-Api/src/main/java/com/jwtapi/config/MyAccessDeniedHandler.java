package com.jwtapi.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
       
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
       
       if(authentication != null) {
    	   String errorMessage = "Vous n'avez pas les droits nécessaires pour accéder à cette ressource.";
           response.setStatus(HttpServletResponse.SC_FORBIDDEN);
           response.setContentType("application/json;charset=UTF-8");
           response.setCharacterEncoding(StandardCharsets.UTF_8.name());
           response.getWriter().write(errorMessage);
           response.getWriter().flush();
           response.getWriter().close();
       }else {
    	   String errorMessage = "Vous n'etes pas authentifié. Veuillez vous connecter";
           response.setStatus(HttpServletResponse.SC_FORBIDDEN);
           response.setContentType("application/json;charset=UTF-8");
           response.setCharacterEncoding(StandardCharsets.UTF_8.name());
           response.getWriter().write(errorMessage);
           response.getWriter().flush();
           response.getWriter().close(); 
       }
        
    }
}
