package ru.indeece.aitranslator.servicesTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.indeece.aitranslator.dto.*;
import ru.indeece.aitranslator.entities.User;
import ru.indeece.aitranslator.repositories.UserRepository;
import ru.indeece.aitranslator.security.jwt.JwtService;
import ru.indeece.aitranslator.services.impl.UserServiceImpl;

import javax.naming.AuthenticationException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private JwtService jwtService;
    @Mock private PasswordEncoder passwordEncoder;

    @InjectMocks private UserServiceImpl userService;

    @Test
    void signIn_ValidCredentials_ReturnsToken() throws AuthenticationException {
        // Arrange
        UserCredentialsDto credentials = new UserCredentialsDto("test@example.com", "password");
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
        when(jwtService.generateAuthToken("test@example.com")).thenReturn(new JwtAuthenticationDto("token", "refresh"));

        // Act
        JwtAuthenticationDto result = userService.singIn(credentials);

        // Assert
        assertEquals("token", result.getToken());
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void signIn_InvalidPassword_ThrowsException() {
        UserCredentialsDto credentials = new UserCredentialsDto("test@example.com", "wrong");
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "encodedPassword")).thenReturn(false);

        assertThrows(AuthenticationException.class, () -> userService.singIn(credentials));
    }

    @Test
    void refreshToken_ValidToken_ReturnsNewToken() throws Exception {
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto("valid-refresh-token");
        User user = new User();
        user.setEmail("test@example.com");

        when(jwtService.validateToken("valid-refresh-token")).thenReturn(true);
        when(jwtService.getEmailFromToken("valid-refresh-token")).thenReturn("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(jwtService.generateBaseToken("test@example.com", "valid-refresh-token"))
                .thenReturn(new JwtAuthenticationDto("new-token", "valid-refresh-token"));

        JwtAuthenticationDto result = userService.refreshToken(refreshTokenDto);

        assertEquals("new-token", result.getToken());
    }

    @Test
    void addUser_ValidUser_ReturnsSuccessMessage() {
        UserDto userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setUsername("test");
        userDto.setPassword("password");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        String result = userService.addUser(userDto);

        assertEquals("User added", result);
        verify(userRepository, times(1)).save(any(User.class));
    }
}