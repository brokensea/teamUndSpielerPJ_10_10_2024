package de.sp.teamUndSpielerPJ_Back_10_10_2024.services;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.TeamDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Team;

import java.util.List;

public interface TeamService {
    TeamDto createTeam(TeamDto teamDto);
    List<TeamDto> getAllTeams();
    TeamDto getTeamById(Long id);
}
