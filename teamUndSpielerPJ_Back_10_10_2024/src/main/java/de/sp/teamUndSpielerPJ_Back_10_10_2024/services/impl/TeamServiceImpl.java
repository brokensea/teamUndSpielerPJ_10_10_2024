package de.sp.teamUndSpielerPJ_Back_10_10_2024.services.impl;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.TeamDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Team;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper.TeamMapper;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.repositories.TeamRepository;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public TeamDto createTeam(TeamDto teamDto) {
        Team team = TeamMapper.toEntity(teamDto);
        return TeamMapper.toDto(teamRepository.save(team));
    }

    @Override
    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(TeamMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto getTeamById(Long id) {
        return teamRepository.findById(id)
                .map(TeamMapper::toDto)
                .orElse(null);
    }
}
