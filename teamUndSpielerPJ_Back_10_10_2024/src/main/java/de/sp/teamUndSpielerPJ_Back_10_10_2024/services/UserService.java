package de.sp.teamUndSpielerPJ_Back_10_10_2024.services;


import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.UserDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.User;

import java.util.Optional;

public interface UserService {
    UserDto registerUser(UserDto userDto);
    Optional<UserDto> loginUser(String username, String password);
    UserDto findById(Long id);
}
