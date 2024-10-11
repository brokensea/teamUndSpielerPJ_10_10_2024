package de.sp.teamUndSpielerPJ_Back_10_10_2024.services.impl;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.MatchDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.MatchResultDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.TeamDtoRespon;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.MatchResult;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Spieler;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Team;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper.TeamMapper;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.repositories.MatchResultRepository;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.repositories.TeamRepository;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.services.MatchService;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchResultRepository matchResultRepository; // 注入 MatchResultRepository

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public MatchResultDto createMatch(MatchDto matchDto) {
        TeamDtoRespon userTeamDto = teamService.getTeamById(matchDto.userId());
        TeamDtoRespon opponentTeamDto = teamService.getTeamById(matchDto.opponentTeamId());

        int userTeamLevel = calculateTotalLevel(userTeamDto);
        int opponentTeamLevel = calculateTotalLevel(opponentTeamDto);

        MatchResult matchResult = new MatchResult();

        Team teamA = teamRepository.findById(userTeamDto.id())
                .orElseThrow(() -> new RuntimeException("Team A not found"));
        Team teamB = teamRepository.findById(opponentTeamDto.id())
                .orElseThrow(() -> new RuntimeException("Team B not found"));
        matchResult.setTeamA(teamA);
        matchResult.setTeamB(teamB);

        // 判断胜负并设置胜利的团队
        if (userTeamLevel >= opponentTeamLevel) {
            matchResult.setGewinnTeam(teamA);
        } else {
            matchResult.setGewinnTeam(teamB);
        }

        // 保存比赛结果
        matchResultRepository.save(matchResult);

        // 返回比赛结果 DTO
        return new MatchResultDto(
                matchResult.getId(),
                matchResult.getTeamA().getId(),
                matchResult.getTeamB().getId(),
                matchResult.getGewinnTeam().getId()
        );
    }


    private int calculateTotalLevel(TeamDtoRespon teamDtoRespon) {
        Team team = TeamMapper.toEntity(teamDtoRespon);
        return team.getSpielers().stream().mapToInt(Spieler::getPowerLevel).sum();
    }
}