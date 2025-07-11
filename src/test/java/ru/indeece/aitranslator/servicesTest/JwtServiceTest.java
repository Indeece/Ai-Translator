package ru.indeece.aitranslator.servicesTest;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.indeece.aitranslator.security.jwt.JwtService;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    private static final String TEST_SECRET = "test-secret-1234567890-1234567890-1234567890";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtService, "secret", TEST_SECRET);
    }

    @Test
    void generateToken_ValidEmail_ReturnsToken() {
        try (MockedStatic<Jwts> jwts = mockStatic(Jwts.class)) {
            // Arrange
            JwtBuilder builderMock = mock(JwtBuilder.class);
            when(Jwts.builder()).thenReturn(builderMock);
            when(builderMock.subject(anyString())).thenReturn(builderMock);
            when(builderMock.expiration(any())).thenReturn(builderMock);
            when(builderMock.signWith(any())).thenReturn(builderMock);
            when(builderMock.compact()).thenReturn("mocked.token");

            // Act
            String token = jwtService.generateToken("test@example.com");

            // Assert
            assertEquals("mocked.token", token);
        }
    }

    @Test
    void validateToken_ValidToken_ReturnsTrue() {
        try (MockedStatic<Jwts> jwts = mockStatic(Jwts.class)) {
            // Arrange
            when(jwtService.validateToken("valid.token")).thenReturn(true);

            // Act & Assert
            assertTrue(jwtService.validateToken("valid.token"));
        }
    }
}