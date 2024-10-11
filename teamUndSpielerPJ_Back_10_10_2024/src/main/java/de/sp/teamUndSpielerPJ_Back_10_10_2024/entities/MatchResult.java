package de.sp.teamUndSpielerPJ_Back_10_10_2024.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "match_results")
public class MatchResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_a_id")
    private Team teamA;

    @ManyToOne
    @JoinColumn(name = "team_b_id")
    private Team teamB;

    @ManyToOne(cascade = CascadeType.ALL) // 或使用 CascadeType.PERSIST
    @JoinColumn(name = "gewinn_team_id")
    private Team gewinnTeam;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    public Team getGewinnTeam() {
        return gewinnTeam;
    }

    public void setGewinnTeam(Team gewinnTeam) {
        this.gewinnTeam = gewinnTeam;
    }
}
