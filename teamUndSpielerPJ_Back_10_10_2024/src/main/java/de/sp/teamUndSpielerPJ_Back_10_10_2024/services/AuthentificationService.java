package de.sp.teamUndSpielerPJ_Back_10_10_2024.services;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.auth.AuthDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.User;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;

    public AuthentificationService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public User signUp(AuthDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        return userRepository.save(user);
    }

    public String getJwt(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }


}