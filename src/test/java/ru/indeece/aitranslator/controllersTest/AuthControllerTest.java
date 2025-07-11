package ru.indeece.aitranslator.controllersTest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.indeece.aitranslator.controllers.AuthController;
import ru.indeece.aitranslator.dto.*;
import ru.indeece.aitranslator.services.UserService;

import javax.naming.AuthenticationException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthControllerTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("test_db")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void setPostgreSQLContainer(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> true);
    }

    @BeforeEach
    void setUp() {
        System.out.println("Preparing test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test closed");
    }

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @Test
    @Order(1)
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