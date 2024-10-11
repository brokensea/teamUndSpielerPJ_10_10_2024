package de.sp.teamUndSpielerPJ_Back_10_10_2024.services;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.TeamDtoRespon;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.UserDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Team;

import java.util.List;

public interface TeamService {
    TeamDtoRespon createTeam(UserDto userDto, TeamDtoRespon teamDtoRespon);

    List<TeamDtoRespon> getAllTeams();

    TeamDtoRespon getTeamById(Long id);

    Team saveTeamIfNotExists(Team team);
}
