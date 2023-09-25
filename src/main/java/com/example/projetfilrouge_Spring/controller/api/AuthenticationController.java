package com.example.projetfilrouge_Spring.controller.api;

import com.example.projetfilrouge_Spring.repository.entity.User;
import com.example.projetfilrouge_Spring.security.JwtUtils;
import com.example.projetfilrouge_Spring.security.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

        @Autowired
        JwtUtils jwtUtils;
        @Autowired
        AuthenticationManager authenticationManager;

        @PostMapping("/login")
        public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
            UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Long userId = ((User) authentication.getPrincipal()).getId();
            String tokenGenerated = jwtUtils.generateJwtToken(authentication);
            Map<String, Object> response = new HashMap<>();
            response.put("token", tokenGenerated);
            response.put("userId", userId);
            return ResponseEntity.ok(response);
        }
}