package de.sp.teamUndSpielerPJ_Back_10_10_2024.controller;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.*;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.User;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper.UserMapper;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private SpielerService spielerService;
    @Autowired
    private AuthentificationService authentificationService;
    @Autowired
    private MatchService matchService;


   /* @GetMapping("/teams")
    public ResponseEntity<List<TeamDtoRespon>> getAllTeams() {
        List<TeamDtoRespon> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }*/

   /* @PostMapping("/teams/{teamId}/players")
    public ResponseEntity<SpielerDto> createPlayer(@PathVariable Long teamId, @RequestBody SpielerDto spielerDto) {
        spielerDto.setTeamId(teamId);
        SpielerDto createdPlayer = spielerService.createPlayer(spielerDto);
        return ResponseEntity.ok(createdPlayer);
    }*/

    // Initialize team for a user
    @PostMapping("/initializeTeam/{userId}")
    public ResponseEntity<TeamDtoRespon> initializeTeam(@PathVariable Long userId) {
        UserDto userDto = userService.findById(userId);
        if (userDto == null) {
            return ResponseEntity.notFound().build();
        }
        TeamDtoRespon teamDto = authentificationService.initializeTeam(userDto);
        return ResponseEntity.ok(teamDto);
    }

   /* @GetMapping("/teams/{teamId}/players")
    public ResponseEntity<List<SpielerDto>> getPlayersByTeamId(@PathVariable Long teamId) {
        List<SpielerDto> players = spielerService.getPlayersByTeamId(teamId);
        return ResponseEntity.ok(players);
    }*/

    // matches zwischen Benutzern und anderen Benutzern
    @PostMapping("/creatematch")
    public ResponseEntity<MatchResultDto> createMatch(@RequestBody MatchDto matchDto) {
        MatchResultDto matchResultDto = matchService.createMatch(matchDto);
        return ResponseEntity.ok(matchResultDto);
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<TeamDtoWithPlayer> getTeamWithPlayers(@PathVariable Long id) {
        TeamDtoWithPlayer teamDtoWithPlayer = teamService.getTeamWithPlayersById(id);
        return ResponseEntity.ok(teamDtoWithPlayer);
    }


    // mit MatchId prüfen MatchResultDto
   /* @GetMapping("/matches/{matchId}")
    public ResponseEntity<MatchResultDto> getMatchResult(@PathVariable Long matchId) {
        return ResponseEntity.ok().build();
    }*/
}