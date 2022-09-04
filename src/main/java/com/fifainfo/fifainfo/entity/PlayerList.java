package com.fifainfo.fifainfo.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerList {

    @Id
    @GeneratedValue
    @Column(name="playerlist_id")
    private Long playerListId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String nickname;


//
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "playerinfo_id")
//    private List<PlayerInfo> playerInfos = new ArrayList<>();

    public static PlayerList createPlayerList(String nickname) {
        PlayerList playerList = new PlayerList();
        playerList.setNickname(nickname);
        return playerList;
    }
}
