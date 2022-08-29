package com.fifainfo.fifainfo.service;

import com.fifainfo.fifainfo.entity.Player;
import com.fifainfo.fifainfo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player create(Long spId, String name) {
        Optional<Player> player = playerRepository.findBySpId(spId);

        if (player.isPresent()) {
            return player.get();
        } else {
            Player pl = Player.builder()
                    .spId(spId)
                    .name(name)
                    .build();
            return playerRepository.save(pl);
        }
    }

}
