package de.sp.teamUndSpielerPJ_Back_10_10_2024.services.impl;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.UserDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.User;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper.UserMapper;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.repositories.UserRepository;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

   /* @Override
    public UserDto registerUser(UserDto userDto) {
        // Check if the username already exists
        if (userRepository.findByUsername(userDto.username()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = UserMapper.toEntity(userDto);
        // Password encryption logic here
        return UserMapper.toDto(userRepository.save(user));
    }


    @Override
    public Optional<UserDto> loginUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(UserMapper::toDto);
    }*/

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDto)
                .orElse(null);
    }
}