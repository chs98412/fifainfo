package com.fifainfo.fifainfo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "USER_EMAIL", nullable = false, length = 100, unique = true)
    private String userEmail;

    @Column(name = "USER_NICKNAME", length = 15)
    private String nickname;

    @Column(length = 100, nullable = false)
    private String password;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "playerlist_id")
//    private PlayerList playerList;

}
