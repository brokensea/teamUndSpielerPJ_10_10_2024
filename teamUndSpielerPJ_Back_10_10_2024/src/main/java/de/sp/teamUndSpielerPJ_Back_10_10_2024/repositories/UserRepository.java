package de.sp.teamUndSpielerPJ_Back_10_10_2024.repositories;

import de.sp.teamUndSpielerPJ_Back_10_10_2024.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}