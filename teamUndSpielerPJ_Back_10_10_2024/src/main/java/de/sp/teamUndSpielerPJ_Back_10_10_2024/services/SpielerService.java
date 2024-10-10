package de.sp.teamUndSpielerPJ_Back_10_10_2024.services;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.SpielerDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Spieler;

import java.util.List;

public interface SpielerService {
    SpielerDto createPlayer(SpielerDto player);
    List<SpielerDto> getPlayersByTeamId(Long teamId);
}
