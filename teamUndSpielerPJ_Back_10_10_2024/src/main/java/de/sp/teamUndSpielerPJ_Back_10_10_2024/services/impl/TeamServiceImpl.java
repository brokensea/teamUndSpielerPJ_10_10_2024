package de.sp.teamUndSpielerPJ_Back_10_10_2024.services.impl;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.TeamDtoRespon;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.UserDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.PlayerType;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Spieler;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Team;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.User;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper.TeamMapper;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.repositories.TeamRepository;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.repositories.UserRepository;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

   /* @Override
    public TeamDtoRespon createTeam(UserDto userDto, TeamDtoRespon teamDtoRespon) {
        User user = userRepository.findById(userDto.id()).orElse(() -> new Exception(""));

        Team team = TeamMapper.toEntity(teamDtoRespon);
        team.setUser(user);

        // Initialize the players in service layer
        team.setSpielers(initializePlayers(team));
        return TeamMapper.toDto(teamRepository.save(team));
    }*/

   /* @Override
    @Transactional
    public TeamDtoRespon createTeam(UserDto userDto, TeamDtoRespon teamDtoRespon) {
        // 从仓库中获取用户
        User user = userRepository.findById(userDto.id())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userDto.id()));
        // 将 TeamDtoRespon 映射到 Team 实体
        Team team = TeamMapper.toEntity(teamDtoRespon);
        team.setUser(user); // 设置团队的用户
        // 为团队初始化玩家
        team.setSpielers(initializePlayers(team));
        // 保存团队并返回 DTO
        return TeamMapper.toDto(teamRepository.save(team));
    }*/

    @Override
    @Transactional
    public TeamDtoRespon createTeam(UserDto userDto, TeamDtoRespon teamDtoRespon) {
        User user = userRepository.findById(userDto.id())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userDto.id()));
        Team team = TeamMapper.toEntity(teamDtoRespon);
        team.setUser(user);
        team = teamRepository.save(team);
        team.setSpielers(initializePlayers(team));
        team = teamRepository.save(team);
        return TeamMapper.toDto(team);
    }


    private List<Spieler> initializePlayers(Team team) {
        List<Spieler> spielers = new ArrayList<>();
        spielers.add(createSpieler(PlayerType.ROOKIE, team));   // 1 Rookie
        spielers.add(createSpieler(PlayerType.NORMAL, team));   // 2 Normal
        spielers.add(createSpieler(PlayerType.NORMAL, team));   // 2 Normal
        spielers.add(createSpieler(PlayerType.VETERAN, team));  // 1 Veteran
        spielers.add(createSpieler(PlayerType.LEGEND, team));   // 1 Legend
        return spielers;
    }

    private Spieler createSpieler(PlayerType playerType, Team team) {
        Random random = new Random();
        int powerLevel = generatePowerLevel(playerType);
        Spieler spieler = new Spieler();
        spieler.setPowerLevel(powerLevel);
        spieler.setPlayerType(playerType);
        spieler.setTeam(team);  // Set the team here
        return spieler;
    }

    private int generatePowerLevel(PlayerType playerType) {
        Random random = new Random();
        switch (playerType) {
            case ROOKIE:
                return random.nextInt(10) + 1;   // 1-10
            case NORMAL:
                return random.nextInt(20) + 11;  // 11-30
            case VETERAN:
                return random.nextInt(20) + 31;  // 31-50
            case LEGEND:
                return random.nextInt(30) + 51;  // 51-80
            default:
                throw new IllegalArgumentException("Unknown PlayerType: " + playerType);
        }
    }

    @Override
    public List<TeamDtoRespon> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(TeamMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDtoRespon getTeamById(Long id) {
        return teamRepository.findById(id)
                .map(TeamMapper::toDto)
                .orElse(null);
    }

    @Override
    public Team saveTeamIfNotExists(Team team) {
        if (team.getId() == null || !teamRepository.existsById(team.getId())) {
            return teamRepository.save(team);
        }
        return team;
    }
}