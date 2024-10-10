package de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.MatchResultDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.MatchResult;

public class MatchResultMapper {
    public static MatchResultDto toDto(MatchResult matchResult) {
        return new MatchResultDto(matchResult.getId(), matchResult.getTeamA().getId(), matchResult.getTeamB().getId(), matchResult.getGewinnTeam().getId());
    }

    public static MatchResult toEntity(MatchResultDto matchResultDto) {
        MatchResult matchResult = new MatchResult();
        matchResult.setId(matchResultDto.id());
        return matchResult;
    }
}