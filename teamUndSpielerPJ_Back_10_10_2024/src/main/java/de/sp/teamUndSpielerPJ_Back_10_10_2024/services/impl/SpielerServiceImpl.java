package de.sp.teamUndSpielerPJ_Back_10_10_2024.services.impl;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.SpielerDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Spieler;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.mapper.SpielerMapper;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.repositories.SpielerRepository;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.services.SpielerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpielerServiceImpl implements SpielerService {

    @Autowired
    private SpielerRepository spielerRepository;

    @Override
    public SpielerDto createPlayer(SpielerDto spielerDto) {
        Spieler spieler = SpielerMapper.toEntity(spielerDto);
        return SpielerMapper.toDto(spielerRepository.save(spieler));
    }

    @Override
    public List<SpielerDto> getPlayersByTeamId(Long teamId) {
        return spielerRepository.findById(teamId).stream()
                .map(SpielerMapper::toDto)
                .collect(Collectors.toList());
    }
}