package de.sp.teamUndSpielerPJ_Back_10_10_2024.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Spieler> spielers = new ArrayList<>();

    public Team() {
        // Init 5 Spieler，Nach der Anforderung
        spielers.add(createSpieler(PlayerType.ROOKIE));   // 1 Rookie
        spielers.add(createSpieler(PlayerType.NORMAL));   // 2 Normal
        spielers.add(createSpieler(PlayerType.NORMAL));   // 2 Normal
        spielers.add(createSpieler(PlayerType.VETERAN));  // 1 Veteran
        spielers.add(createSpieler(PlayerType.LEGEND));    // 1 Legendär
    }

    // Nach PlayerType erzeugt Spieler
    private Spieler createSpieler(PlayerType playerType) {
        Random random = new Random();
        int powerLevel = generatePowerLevel(playerType);
        Spieler spieler = new Spieler();
        spieler.setPowerLevel(powerLevel);
        spieler.setPlayerType(playerType);
        return spieler;
    }

    // Nach PlayerType erzeugt power level
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

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Spieler> getSpielers() {
        return spielers;
    }

    public void setSpielers(List<Spieler> spielers) {
        this.spielers = spielers;
    }
}