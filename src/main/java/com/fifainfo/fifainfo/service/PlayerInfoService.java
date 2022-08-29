package com.fifainfo.fifainfo.service;


import com.fifainfo.fifainfo.entity.Player;
import com.fifainfo.fifainfo.entity.PlayerInfo;
import com.fifainfo.fifainfo.repository.PlayerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerInfoService {

    @Autowired
    private PlayerInfoRepository playerInfoRepository;

    public boolean isPresent(String name) {
        Long temp = playerInfoRepository.countByName(name);
        if (temp > 0) {
            return true;
        } else {
            return false;
        }
    }


    public void createPlayerInfo(String name) {
        PlayerInfo playerInfo=PlayerInfo.builder()
                .name(name)
                .assist(0l)
                .effectiveShoot(0l)
                .goal(0l)
                .shoot(0l)
                .game(0)
                .build();
        playerInfoRepository.save(playerInfo);
    }

    public void updateInfo(String playername, int i, Long shoot, Long effectiveShoot, Long assist, Long goal) {

        Optional<PlayerInfo> playerInfo = playerInfoRepository.findByName(playername);
        if (playerInfo.isPresent()) {
            PlayerInfo pl = playerInfo.get();

            pl.setGame(pl.getGame()+1);
            pl.setShoot(pl.getShoot()+shoot);
            pl.setEffectiveShoot(pl.getEffectiveShoot()+effectiveShoot);
            pl.setAssist(pl.getAssist()+assist);
            pl.setGoal(pl.getGoal()+goal);
            playerInfoRepository.save(pl);
        }
    }

    public List<PlayerInfo> all() {
        return playerInfoRepository.findAll();
    }
}
