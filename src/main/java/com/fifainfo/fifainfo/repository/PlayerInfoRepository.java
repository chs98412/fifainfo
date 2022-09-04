package com.fifainfo.fifainfo.repository;

import com.fifainfo.fifainfo.entity.PlayerInfo;
import com.fifainfo.fifainfo.entity.PlayerList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, Long> {
    Long countByName(String name);

    Optional<PlayerInfo> findByName(String name);

    Optional<PlayerInfo> findByNameAndNickname(String name, String nickname);

    List<PlayerInfo> findAllByNickname(String nickname);

    Optional<PlayerInfo> findByPlayerListAndName(PlayerList playerList, String playername);

    List<PlayerInfo> findAllByPlayerList(PlayerList playerList);
}
