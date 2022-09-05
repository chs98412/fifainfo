package com.fifainfo.fifainfo.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerInfo {


    @Id
    @GeneratedValue
    @Column(name="playerinfo_id")
    private Long playerInfoId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="playerlist_id")
    private PlayerList playerList;


    private Long spId;
    private String nickname;

    private String name;

    private int game;

    private Long shoot;
    private Long effectiveShoot;
    private Long assist;
    private Long goal;

    private Long AttackPoints;

//    private int dribble;
//    private int intercept;
//    private int defending;
//    private int passTry;
//    private int passSuccess;
//    private int dribbleTry;
//    private int dribbleSuccess;
//    private int ballPossesionTry;
//    private int ballPossesionSuccess;
//    private int aerialTry;
//    private int aerialSuccess;
//    private int blockTry;
//    private int block;
//    private int tackleTry;
//    private int tackle;
//    private int yellowCards;
//    private int redCards;
//    private float spRating;
}
