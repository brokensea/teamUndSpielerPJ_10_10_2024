package de.sp.teamUndSpielerPJ_Back_10_10_2024.controller;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.*;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.services.SpielerService;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.services.TeamService;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.services.UserService;
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

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        UserDto registeredUser = userService.registerUser(userDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }

    @PostMapping("/teams")
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto) {
        TeamDto createdTeam = teamService.createTeam(teamDto);
        return ResponseEntity.ok(createdTeam);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        List<TeamDto> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

   /* @PostMapping("/teams/{teamId}/players")
    public ResponseEntity<SpielerDto> createPlayer(@PathVariable Long teamId, @RequestBody SpielerDto spielerDto) {
        spielerDto.setTeamId(teamId);
        SpielerDto createdPlayer = spielerService.createPlayer(spielerDto);
        return ResponseEntity.ok(createdPlayer);
    }*/

    @GetMapping("/teams/{teamId}/players")
    public ResponseEntity<List<SpielerDto>> getPlayersByTeamId(@PathVariable Long teamId) {
        List<SpielerDto> players = spielerService.getPlayersByTeamId(teamId);
        return ResponseEntity.ok(players);
    }

    // matches zwischen Benutzern und anderen Benutzern
    @PostMapping("/matches")
    public ResponseEntity<Void> createMatch(@RequestBody MatchDto matchDto) {
        return ResponseEntity.ok().build();
    }

    // mit MatchId prüfen MatchResultDto
    @GetMapping("/matches/{matchId}")
    public ResponseEntity<MatchResultDto> getMatchResult(@PathVariable Long matchId) {
        return ResponseEntity.ok().build();
    }
}