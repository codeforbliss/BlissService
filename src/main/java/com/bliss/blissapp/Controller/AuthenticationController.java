package com.bliss.blissapp.Controller;

import com.bliss.blissapp.Model.AuthenticationRequest;
import com.bliss.blissapp.Model.AuthenticationResponse;
import com.bliss.blissapp.Model.RegisterRequest;
import com.bliss.blissapp.Service.AuthenticationService;
import com.bliss.blissapp.Service.JwtService;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/expired")
    public ResponseEntity<Boolean> isTokenExpired(@RequestParam String token) {
        return ResponseEntity.ok(jwtService.isTokenExpired(token));
    }

}
