package de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.TeamDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Team;

public class TeamMapper {
    public static TeamDto toDto(Team team) {
        return new TeamDto(team.getId(), team.getName(), team.getUser().getId());
    }

    public static Team toEntity(TeamDto teamDto) {
        Team team = new Team();
        team.setId(teamDto.id());
        team.setName(teamDto.name());
        return team;
    }
}