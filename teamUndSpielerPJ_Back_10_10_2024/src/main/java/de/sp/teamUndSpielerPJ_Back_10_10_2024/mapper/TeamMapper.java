package de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.TeamDtoRespon;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Team;

public class TeamMapper {
    public static TeamDtoRespon toDto(Team team) {
        return new TeamDtoRespon(team.getId(), team.getName(), team.getUser().getId());
    }

    public static Team toEntity(TeamDtoRespon teamDtoRespon) {
        Team team = new Team();
        team.setName(teamDtoRespon.name());
        return team;
    }
}