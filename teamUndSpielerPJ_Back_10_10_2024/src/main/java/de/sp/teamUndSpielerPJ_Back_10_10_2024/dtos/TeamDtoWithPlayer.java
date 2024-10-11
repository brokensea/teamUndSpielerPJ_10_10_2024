package de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Spieler;

import java.util.List;

public record TeamDtoWithPlayer(Long id, String name, Long userId, List<SpielerDto> spielers) {
}
