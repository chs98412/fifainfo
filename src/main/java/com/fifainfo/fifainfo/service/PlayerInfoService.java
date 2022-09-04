package com.fifainfo.fifainfo.service;


import com.fifainfo.fifainfo.entity.Player;
import com.fifainfo.fifainfo.entity.PlayerInfo;
import com.fifainfo.fifainfo.entity.PlayerList;
import com.fifainfo.fifainfo.entity.User;
import com.fifainfo.fifainfo.repository.PlayerInfoRepository;
import com.fifainfo.fifainfo.repository.PlayerListRepository;
import com.fifainfo.fifainfo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerInfoService {

    @Autowired
    private PlayerInfoRepository playerInfoRepository;
    @Autowired
    private PlayerListRepository playerListRepository;
    @Autowired
    private UserRepository userRepository;


    public boolean isPresent(String name,String nickname) {
        Optional<PlayerInfo> playerInfo= playerInfoRepository.findByNameAndNickname(name,nickname);

        if (playerInfo.isPresent()) {
            return true;
        } else {
            return false;
        }
    }


    public void createPlayerInfo(String name,String nickname) {
        PlayerInfo playerInfo=PlayerInfo.builder()
                .name(name)
                .assist(0l)
                .effectiveShoot(0l)
                .goal(0l)
                .shoot(0l)
                .game(0)
                .AttackPoints(0l)
                .build();

        Optional<PlayerList> playerList=playerListRepository.findByNickname(nickname);
        PlayerList pl;
        if ( ! playerList.isPresent() ) {

              pl= PlayerList.createPlayerList(nickname);
              playerListRepository.save(pl);
        }
        else {
             pl = playerList.get();
        }
        playerInfo.setPlayerList(pl);

        Optional<PlayerInfo> pi=playerInfoRepository.findByPlayerListAndName(pl, name);
        if(! pi.isPresent()){
            playerInfoRepository.save(playerInfo);
        }

       // playerInfoRepository.save(playerInfo);
    }

    public void updateInfo(String nickname,String playername, int i, Long shoot, Long effectiveShoot, Long assist, Long goal) {

        PlayerList playerList = playerListRepository.findByNickname(nickname).get();

        Optional<PlayerInfo> playerInfo = playerInfoRepository.findByPlayerListAndName(playerList,playername);
        if (playerInfo.isPresent()) {
            PlayerInfo pl = playerInfo.get();

            pl.setGame(pl.getGame()+1);
            pl.setShoot(pl.getShoot()+shoot);
            pl.setEffectiveShoot(pl.getEffectiveShoot()+effectiveShoot);
            pl.setAssist(pl.getAssist()+assist);
            pl.setGoal(pl.getGoal()+goal);
            pl.setAttackPoints(pl.getAttackPoints() + goal + assist);

            playerInfoRepository.save(pl);
        }
    }

    public List<PlayerInfo> all(String nickname) {
        System.out.println(nickname);
        PlayerList playerList = playerListRepository.findByNickname(nickname).get();

        return playerInfoRepository.findAllByPlayerList(playerList);
    }


    public PlayerInfo findInfo(PlayerList playerList, String player) {
        return playerInfoRepository.findByPlayerListAndName(playerList,player).get();
    }
}
