package de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.UserDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword());
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.id());
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());
        return user;
    }
}