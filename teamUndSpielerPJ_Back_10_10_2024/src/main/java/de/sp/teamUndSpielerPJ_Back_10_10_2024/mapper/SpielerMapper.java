package de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.SpielerDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Spieler;

public class SpielerMapper {
    public static SpielerDto toDto(Spieler spieler) {
        return new SpielerDto(spieler.getId(), spieler.getPowerLevel(), spieler.getTeam().getId());
    }

    public static Spieler toEntity(SpielerDto spielerDto) {
        Spieler spieler = new Spieler();
        spieler.setId(spielerDto.id());
        spieler.setPowerLevel(spielerDto.powerLevel());
        return spieler;
    }
}