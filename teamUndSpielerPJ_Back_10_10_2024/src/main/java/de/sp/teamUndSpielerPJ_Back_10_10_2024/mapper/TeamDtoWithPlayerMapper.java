package de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.SpielerDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.TeamDtoWithPlayer;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamDtoWithPlayerMapper {

    public static TeamDtoWithPlayer toDto(Team team) {
        List<SpielerDto> playerDtos = team.getSpielers().stream()
                .map(spieler -> new SpielerDto(spieler.getId(), spieler.getPowerLevel(), spieler.getTeam().getId())) // 需要您定义 SpielerDto
                .collect(Collectors.toList());


        return new TeamDtoWithPlayer(team.getId(), team.getName(), team.getUser().getId(), playerDtos);
    }
}
