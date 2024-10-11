package de.sp.teamUndSpielerPJ_Back_10_10_2024.services;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.MatchDto;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.dtos.MatchResultDto;

public interface MatchService {
    MatchResultDto createMatch(MatchDto matchDto);
}
