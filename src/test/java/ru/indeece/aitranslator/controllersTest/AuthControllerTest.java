package ru.indeece.aitranslator.controllersTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.indeece.aitranslator.controllers.AuthController;
import ru.indeece.aitranslator.dto.*;
import ru.indeece.aitranslator.services.UserService;

import javax.naming.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @Test
    void signIn_ValidCredentials_ReturnsToken() throws AuthenticationException {
        // Arrange
        UserCredentialsDto credentials = new UserCredentialsDto("test@example.com", "password");
        JwtAuthenticationDto expectedResponse = new JwtAuthenticationDto("token", "refresh");

        when(userService.singIn(credentials)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<JwtAuthenticationDto> response = authController.signIn(credentials);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void register_ValidUser_ReturnsSuccess() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUsername("test");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");

        when(userService.addUser(userDto)).thenReturn("User added");

        // Act
        ResponseEntity<String> response = authController.createUser(userDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User added", response.getBody());
    }
}