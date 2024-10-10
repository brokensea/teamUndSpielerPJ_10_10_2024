package de.sp.teamUndSpielerPJ_Back_10_10_2024.repositories;
import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
