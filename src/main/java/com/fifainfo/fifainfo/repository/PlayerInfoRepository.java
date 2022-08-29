package com.fifainfo.fifainfo.repository;

import com.fifainfo.fifainfo.entity.PlayerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, Long> {
    Long countByName(String name);

    Optional<PlayerInfo> findByName(String name);
}
