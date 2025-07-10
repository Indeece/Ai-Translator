package ru.indeece.aitranslator.services;

import org.springframework.data.crossstore.ChangeSetPersister;
import ru.indeece.aitranslator.dto.JwtAuthenticationDto;
import ru.indeece.aitranslator.dto.RefreshTokenDto;
import ru.indeece.aitranslator.dto.UserCredentialsDto;
import ru.indeece.aitranslator.dto.UserDto;

import javax.naming.AuthenticationException;

public interface UserService {
    JwtAuthenticationDto singIn(UserCredentialsDto userCredentialsDto) throws AuthenticationException;
    JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception;
    UserDto getUserById(String id) throws ChangeSetPersister.NotFoundException;
    UserDto getUserByEmail(String email) throws ChangeSetPersister.NotFoundException;
    String addUser(UserDto user);
}
