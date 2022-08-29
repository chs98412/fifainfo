package com.fifainfo.fifainfo.repository;

import com.fifainfo.fifainfo.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findBySpId(Long id);
}
