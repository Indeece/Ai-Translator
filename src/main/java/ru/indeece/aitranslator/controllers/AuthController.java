package ru.indeece.aitranslator.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.indeece.aitranslator.dto.JwtAuthenticationDto;
import ru.indeece.aitranslator.dto.RefreshTokenDto;
import ru.indeece.aitranslator.dto.UserCredentialsDto;
import ru.indeece.aitranslator.dto.UserDto;
import ru.indeece.aitranslator.services.UserService;

import javax.naming.AuthenticationException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationDto> signIn(@RequestBody UserCredentialsDto userCredentialsDto) {
        try {
            JwtAuthenticationDto jwtAuthenticationDto = userService.singIn(userCredentialsDto);
            return ResponseEntity.ok(jwtAuthenticationDto);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed" + e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public JwtAuthenticationDto refresh(@RequestBody RefreshTokenDto refreshTokenDto) throws Exception {
        return userService.refreshToken(refreshTokenDto);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDto userDto) {
        String result = userService.addUser(userDto);
        return ResponseEntity.ok(result);
    }
}
