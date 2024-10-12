package de.sp.teamUndSpielerPJ_Back_10_10_2024.services;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.TeamDtoRespon;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.UserDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.auth.AuthDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Team;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.User;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper.TeamMapper;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper.UserMapper;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.repositories.UserRepository;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.services.impl.TeamServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;
    private TeamServiceImpl teamService;

    public AuthentificationService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, TeamServiceImpl teamService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.teamService = teamService;
    }

    public User signUp(AuthDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        User savedUser = userRepository.save(user);
        System.out.println("Saved User: " + savedUser);
        return savedUser;
    }

    public String getJwt(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

    public TeamDtoRespon initializeTeam(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        try {
            // Initialize the TeamDto, this can be customized based on your requirements
            TeamDtoRespon teamDto = new TeamDtoRespon(user.getId(), user.getUsername() + "'s Team", user.getId());
            System.out.println("Initialized TeamDto: " + teamDto);
            Team team = TeamMapper.toEntity(teamDto);
            team.setUser(user);
            teamService.createTeam(userDto, TeamMapper.toDto(team));
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Optionally throw a custom exception
            throw new RuntimeException("Error creating team", e);
        }
        return new TeamDtoRespon(null, user.getUsername() + "'s Team", user.getId());
    }


}