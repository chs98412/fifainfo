package com.fifainfo.fifainfo.repository;

import com.fifainfo.fifainfo.entity.PlayerList;
import com.fifainfo.fifainfo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerListRepository extends JpaRepository<PlayerList, Long> {
    Optional<PlayerList> findByUser(User user);

    Optional<PlayerList>
    findByNickname(String nickname);
}
